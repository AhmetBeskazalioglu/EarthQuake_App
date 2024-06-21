package com.anke.EarthQuake.controller;

import com.anke.EarthQuake.entity.Earthquake;
import com.anke.EarthQuake.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EarthquakeController {

    private final EarthquakeService earthquakeService;

    @Autowired
    public EarthquakeController(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    @GetMapping("/earthquakes")
    public List<Earthquake> getEarthquakes(@RequestParam String country, @RequestParam int days) {
        return earthquakeService.getEarthquakes(country, days);
    }
}
