package weather_information_service_generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class WeatherInformationDataPoint {

    private final BigDecimal longitude;
    private final BigDecimal latitude;
    private final BigDecimal temperature;
    private final TemperatureUnit temperatureUnit;
    private final BigDecimal windSpeed;
    private final WindDirection windDirection;
    private final int precipitationChange;
    Random random = new Random();

    protected static final double DEFAULT_MAX_LONGITUDE = 180;
    protected static final double DEFAULT_MIN_LONGITUDE = -180;
    protected static final double DEFAULT_MAX_LATITUDE = 90;
    protected static final double DEFAULT_MIN_LATITUDE = -90;
    protected static final double DEFAULT_MAX_TEMP = 120;
    protected static final double DEFAULT_MIN_TEMP = -120;
    protected static final double DEFAULT_MAX_WIND_SPEED = 130;
    protected static final double DEFAULT_MIN_WIND_SPEED = 0;
    protected static final int DEFAULT_MAX_PRECIPITATION = 100;
    protected static final int DEFAULT_MIN_PRECIPITATION = 0;

    protected WeatherInformationDataPoint() {
        this.longitude = scaledBigDecimalWithinRandomRange(DEFAULT_MAX_LONGITUDE, DEFAULT_MIN_LONGITUDE, 6);
        this.latitude = scaledBigDecimalWithinRandomRange(DEFAULT_MAX_LATITUDE, DEFAULT_MIN_LATITUDE, 6);
        this.temperature = scaledBigDecimalWithinRandomRange(DEFAULT_MAX_TEMP, DEFAULT_MIN_TEMP, 1);
        this.temperatureUnit = TemperatureUnit.randomTemperatureUnit();
        this.windSpeed = scaledBigDecimalWithinRandomRange(DEFAULT_MAX_WIND_SPEED, DEFAULT_MIN_WIND_SPEED, 1);
        this.windDirection = WindDirection.randomWindDirection();
        this.precipitationChange = randomIntWithinRange(DEFAULT_MAX_PRECIPITATION, DEFAULT_MIN_PRECIPITATION);
    }

    protected WeatherInformationDataPoint(double maxLongitude, double minLongitude, double maxLatitude, double minLatitude, double maxTemp, double minTemp, double maxWindSpeed, double minWindSpeed, int maxPrecipitation, int minPrecipitation, List<WindDirection> chosenWindDirections, List<TemperatureUnit> chosenTemperatureUnits) {
        this.longitude = scaledBigDecimalWithinRandomRange(maxLongitude, minLongitude, 6);
        this.latitude = scaledBigDecimalWithinRandomRange(maxLatitude, minLatitude, 6);
        this.temperature = scaledBigDecimalWithinRandomRange(maxTemp, minTemp, 1);
        this.temperatureUnit = TemperatureUnit.randomTemperatureUnitFromList(chosenTemperatureUnits);
        this.windSpeed = scaledBigDecimalWithinRandomRange(maxWindSpeed, minWindSpeed, 1);
        this.windDirection = WindDirection.randomWindDirectionFromList(chosenWindDirections);
        this.precipitationChange = randomIntWithinRange(maxPrecipitation, minPrecipitation);
    }

    private BigDecimal scaledBigDecimalWithinRandomRange(double maxValue, double minValue, int scale){
        return BigDecimal.valueOf(randomDoubleWithinRange(maxValue, minValue)).setScale(scale, RoundingMode.UP);
    }

    private double randomDoubleWithinRange(double maxValue, double minValue){
        return random.nextDouble() * (maxValue - minValue) + minValue;
    }

    private int randomIntWithinRange(int maxValue, int minValue){
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }

    public String toString(){
        String windSpeedValue = "km/h";
        String tab = "\t";
        return longitude.toString() + tab +
                latitude.toString() + tab +
                temperature.toString() + tab +
                temperatureUnit.label + tab +
                windSpeed.toString() + tab +
                windSpeedValue + tab +
                windDirection.label + tab +
                precipitationChange;
    }

    public static double getDefaultMaxLongitude() {
        return DEFAULT_MAX_LONGITUDE;
    }

    public static double getDefaultMinLongitude() {
        return DEFAULT_MIN_LONGITUDE;
    }

    public static double getDefaultMaxLatitude() {
        return DEFAULT_MAX_LATITUDE;
    }

    public static double getDefaultMinLatitude() {
        return DEFAULT_MIN_LATITUDE;
    }

    public static double getDefaultMaxTemp() {
        return DEFAULT_MAX_TEMP;
    }

    public static double getDefaultMinTemp() {
        return DEFAULT_MIN_TEMP;
    }

    public static double getDefaultMaxWindSpeed() {
        return DEFAULT_MAX_WIND_SPEED;
    }

    public static double getDefaultMinWindSpeed() {
        return DEFAULT_MIN_WIND_SPEED;
    }

    public static int getDefaultMaxPrecipitation() {
        return DEFAULT_MAX_PRECIPITATION;
    }

    public static int getDefaultMinPrecipitation() {
        return DEFAULT_MIN_PRECIPITATION;
    }
}