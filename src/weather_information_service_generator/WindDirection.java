package weather_information_service_generator;

import java.util.List;
import java.util.Random;

public enum WindDirection {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    NORTH_EAST("NE"),
    NORTH_WEST("NW"),
    SOUTH_EAST("SE"),
    SOUTH_WEST("SW");

    public final String label;
    private static final Random Random = new Random();

    WindDirection(String label){
        this.label= label;
    }

    public static WindDirection randomWindDirection(){
        return WindDirection.values()[Random.nextInt(WindDirection.values().length)];
    }

    public static WindDirection randomWindDirectionFromList(List<WindDirection> chosenEnums){
        while (true){
            WindDirection randomDirection = randomWindDirection();
            if (chosenEnums.contains(randomDirection)){
                return randomDirection;
            }
        }
    }
}