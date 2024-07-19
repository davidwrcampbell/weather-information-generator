package weather_information_service_generator;

import java.util.List;
import java.util.Random;

public enum TemperatureUnit {
    CELSIUS("C"),
    FAHRENHEIT("F");

    public final String label;
    private static final Random Random = new Random();

    TemperatureUnit(String label){
        this.label= label;
    }

    public static TemperatureUnit randomTemperatureUnit(){
        return TemperatureUnit.values()[Random.nextInt(TemperatureUnit.values().length)];
    }

    public static TemperatureUnit randomTemperatureUnitFromList(List<TemperatureUnit> chosenEnums){
        while (true){
            TemperatureUnit randomUnit = randomTemperatureUnit();
            if (chosenEnums.contains(randomUnit)){
                return randomUnit;
            }
        }
    }
}