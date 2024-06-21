

# EarthquakeApp

Java tabanlı bir konsol uygulamasıdır. Belirli bir ülkenin son {x} günündeki kaydedilmiş depremleri listeler.

---

## Kullanım

Uygulama, belirli bir ülkenin son günlerdeki kaydedilmiş depremlerini listelemek için kullanılır. Veriler, USGS Earthquake API'si üzerinden çekilir.

### Gereksinimler

- Java 8 veya üzeri
- Maven (bağımlılıkları yönetmek için)

### Kurulum

1. Projenin klonlanması:
   ```bash
   git clone https://github.com/AhmetBeskazalioglu/EarthQuake_App.git
   cd EarthQuake_App
   ```

2. Uygulamanın derlenmesi ve çalıştırılması:
   ```bash
   mvn clean package
   java -jar target/EarthquakeApp-1.0-SNAPSHOT.jar
   ```

### API Kullanımı

- Uygulama, USGS Earthquake API'sini kullanarak deprem verilerini çeker.
- API URL: `https://earthquake.usgs.gov/fdsnws/event/1/query`

### Parametreler

- `country`: Ülke adı (örn. Turkey, Japan)
- `days`: Geriye gidilecek gün sayısı

### Örnek Kullanım

```bash
curl -X GET "http://localhost:8080/api/earthquakes?country=Turkey&days=7"
```

---

## Proje Yapısı

- `src/main/java/com/anke/EarthQuake`: Java kodları
  - `controller/EarthquakeController.java`: REST API kontrolcüsü
  - `entity/Earthquake.java`: Deprem nesnesi
  - `exception/NoEarthquakesFoundException.java`: Özel exception sınıfı
  - `response/EarthquakeResponse.java`, `Feature.java`, `Properties.java`: API yanıtı için nesneler
  - `service/EarthquakeService.java`: Uygulama mantığı ve API çağrıları

- `src/main/resources/templates/noEarthquakes.html`: Hata mesajı şablonu

---

## Ekran Görüntüsü

![image](https://github.com/AhmetBeskazalioglu/EarthQuake_App/assets/146031280/521c1d42-ecd4-4078-846d-2d0c4302793b),![image](https://github.com/AhmetBeskazalioglu/EarthQuake_App/assets/146031280/c1fc1661-c614-4352-b9ac-95e42e61d7fc)



---

## Lisans

Bu proje MIT Lisansı ile lisanslanmıştır. Daha fazla bilgi için [MIT-LICENSE](https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt) dosyasına bakınız.
