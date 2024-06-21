package com.anke.EarthQuake.service;

import com.anke.EarthQuake.entity.Earthquake;
import com.anke.EarthQuake.exception.NoEarthquakesFoundException;
import com.anke.EarthQuake.response.EarthquakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EarthquakeService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    @Autowired
    public EarthquakeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Belirtilen ülke ve gün sayısına göre deprem bilgilerini döndürür.
     *
     * @param country Ülke adı
     * @param days    Gün sayısı
     * @return Deprem bilgileri
     */
    public List<Earthquake> getEarthquakes(String country, int days) {
        String params = "?format=geojson&starttime=" + getStartDate(days) + "&endtime=" + getEndDate();

        EarthquakeResponse response = restTemplate.getForObject(BASE_URL + params, EarthquakeResponse.class);

        if (response == null || response.getFeatures().isEmpty()) {
            throw new NoEarthquakesFoundException(days + " gün içinde kaydedilmiş bir deprem yok.", days);
        }

        List<Earthquake> earthquakes = response.getFeatures().stream()
                .filter(feature -> {
                    String place = feature.getProperties().getPlace();
                    String countryName = getCountryFromPlace(place);
                    return country.equalsIgnoreCase(countryName);
                })
                .map(feature -> {
                    double magnitude = feature.getProperties().getMag();
                    return new Earthquake(
                            country,
                            feature.getProperties().getPlace(),
                            magnitude,
                            convertEpochToDateTime(Long.parseLong(feature.getProperties().getTime()))
                    );
                })
                .collect(Collectors.toList());

        if (earthquakes.isEmpty()) {
            throw new NoEarthquakesFoundException(days + " gün içinde kaydedilmiş bir deprem yok.", days);
        }

        return earthquakes;
    }

    /**
     * Belirtilen gün sayısına göre başlangıç tarihi döndürür.
     *
     * @param days Başlangıç tarihi hesaplanacak gün sayısı
     * @return Başlangıç tarihi
     */
    private String getStartDate(int days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(LocalDate.now().minusDays(days));
    }

    /**
     * Bugünün tarihini döndürür.
     *
     * @return Bugünün tarihi
     */
    private String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(LocalDate.now());
    }

    /**
     * Epoch tarihini yyyy-MM-dd HH:mm:ss formatına çevirir.
     *
     * @param epochMillis Epoch tarih
     * @return yyyy-MM-dd HH:mm:ss formatındaki tarih
     */
    private String convertEpochToDateTime(long epochMillis) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    /**
     * Yer bilgisinden ülke adını döndürür.
     *
     * @param place Yer bilgisi
     * @return Ülke adı
     */
    private String getCountryFromPlace(String place) {
        if (place != null && place.contains(",")) {
            String[] parts = place.split(",");
            return parts[parts.length - 1].trim();
        }
        return "";
    }
}
