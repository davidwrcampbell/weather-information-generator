package weather_information_service_generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherInformationServiceGenerator {

    public static void main(String[] args) throws IOException {
        //example call generating a random document, outputs as RandomisedWeatherDoc.wis
        //I was unclear on exactly how many data points should be printed for each hour so made it an option of 'number of locations'
        createRandomWeatherInformationServiceDocument(5);

        //part for generations a specific document
        double maxLongitude = WeatherInformationDataPoint.getDefaultMaxLongitude(); //users can use default max/min for range of values
        double minLongitude = 0; //or specify values
        double maxLatitude = 20;
        double minLatitude = WeatherInformationDataPoint.getDefaultMinLatitude();
        double maxTemp = 100;
        double minTemp = -10;
        double maxWindSpeed = 200;
        double minWindSpeed = 100;
        int maxPrecipitation = WeatherInformationDataPoint.getDefaultMaxPrecipitation();
        int minPrecipitation = 50;
        List<WindDirection> windDirections = createWindDirectionList(WindDirection.EAST, WindDirection.NORTH); //users can include selections from enum
        List<TemperatureUnit> temperatureUnits = createTempUnitsList(TemperatureUnit.values()); //or include all values
        //outputs as SpecificWeatherDoc.wis
        createSpecificWeatherInformationServiceDocument(6,
                maxLongitude, minLongitude, maxLatitude, minLatitude, maxTemp, minTemp,
                maxWindSpeed, minWindSpeed, maxPrecipitation, minPrecipitation,
                windDirections, temperatureUnits);
    }

    private static ArrayList<WindDirection> createWindDirectionList(WindDirection... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    private static ArrayList<TemperatureUnit> createTempUnitsList(TemperatureUnit... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    private static void createSpecificWeatherInformationServiceDocument(int numberOfLocations, double maxLongitude, double minLongitude, double maxLatitude, double minLatitude, double maxTemp, double minTemp, double maxWindSpeed, double minWindSpeed, int maxPrecipitation, int minPrecipitation, List<WindDirection> windDirections, List<TemperatureUnit> temperatureUnits) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/SpecificWeatherDoc.wis"));

        LocalDateTime currentDate = LocalDateTime.now(Clock.systemUTC()).withMinute(0);
        LocalDateTime endTime = LocalDateTime.now(Clock.systemUTC()).withMinute(0).plusDays(7);

        for (LocalDateTime start = currentDate; !start.isAfter(endTime); start = start.plusHours(1)){
            writer.append(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'UTC'"))).append("\n");
            for (int i = 0; i < numberOfLocations; i++){
                writer.append(new WeatherInformationDataPoint(maxLongitude, minLongitude, maxLatitude, minLatitude, maxTemp, minTemp, maxWindSpeed, minWindSpeed, maxPrecipitation, minPrecipitation, windDirections, temperatureUnits).toString()).append("\n");
            }
        }
        writer.close();
    }

    private static void createRandomWeatherInformationServiceDocument(int numberOfLocations) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/RandomisedWeatherDoc.wis"));

        LocalDateTime currentDate = LocalDateTime.now(Clock.systemUTC()).withMinute(0);
        LocalDateTime endTime = LocalDateTime.now(Clock.systemUTC()).withMinute(0).plusDays(7);

        for (LocalDateTime start = currentDate; !start.isAfter(endTime); start = start.plusHours(1)){
            writer.append(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'UTC'"))).append("\n");
            for (int i = 0; i < numberOfLocations; i++){
                writer.append(new WeatherInformationDataPoint().toString()).append("\n");
            }
        }
        writer.close();
    }
}