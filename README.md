# Weather Analyzer application

The application receives information about the weather in Minsk from WeatherAPI(https://www.weatherapi.com),
according to a schedule, and saves it in the database. Also finds information about the current weather and average
weather over a period of time

## Author: [Grigoryev Pavel](https://pavelgrigoryev.github.io/GrigoryevPavel/)

### Technologies that I used on the project:

* Java 17
* Gradle 7.6.1
* Spring-boot 3.0.4
* Spring-boot-starter-data-r2dbc
* Spring-boot-starter-webflux (Netty)
* Spring-boot-starter-test
* Reactor-test
* Lombok 1.18.22
* Mapstruct 1.5.3.Final
* Postgresql 14.5

### Instructions to run application locally:

1. You must have [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html),
   [Intellij IDEA](https://www.jetbrains.com/idea/download/) installed
2. In Postgresql you have to create a database with name "weather_analyzer". DDL: "CREATE DATABASE weather_analyzer"
3. In [application.properties](src/main/resources/application.properties) enter your username and password from your
   local postgresql in line №1, №2 and №7, №8. And enter your api key in line №12 if you are registered on
   WeatherAPI(https://www.weatherapi.com), if not, register and get your api key
4. The application will automatically connect to the weather api according to the schedule and will save the received
   data to the database every 10 minutes
5. If you want to change schedule rate, go to
   [WeatherScheduler.java](src/main/java/com/senla/weatheranalyzer/job/WeatherScheduler.java) and change it in line №31.
   Rate is set in milliseconds
6. Run [WeatherAnalyzerApplication.java](src/main/java/com/senla/weatheranalyzer/WeatherAnalyzerApplication.java).
   Liquibase will create the required tables. Script to create database
   schema located: [create-tables.yaml](src/main/resources/db/changelog/create-tables.yaml)
7. Application is ready to work

### Unit tests

You can run the unit tests for this project, by at the root of the project
executing:

```
./gradlew test
```

## Functionalities

In summary the application can:
***
***WeatherController [weather.http](src/main/resources/weather.http)***
***

* **GET findCurrentWeather | Finds information about the current weather**
    * http://localhost:8080/weather
    * Returns current weather data in JSON format
    * Response example:
  ````
  {
    "temperature": 7.0,
    "wind_speed": 9.4,
    "pressure": 1022.0,
    "humidity": 66,
    "condition": "Солнечно",
    "location": "Minsk"
  }
  ````

* **POST findAverageWeather | Finds the average weather over a period of time**
    * http://localhost:8080/weather
    * Accepts JSON body with time period from -> to
    * Request example:
  ````
  {
    "from": "2023-03-18",
    "to":"2023-03-20"
  }
  ````
    * Returns average weather over a period of time in JSON format
    * Response example:
  ````
  {
    "average_temp": 4.0,
    "average_wind_speed": 3.04,
    "average_pressure": 1019.0,
    "average_humidity": 75.0,
    "location": "Minsk"
  }
  ````
