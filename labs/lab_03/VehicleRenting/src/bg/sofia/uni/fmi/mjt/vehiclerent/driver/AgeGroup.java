package bg.sofia.uni.fmi.mjt.vehiclerent.driver;

public enum AgeGroup {
    JUNOIR(10),
    EXPERIENCED(0),
    SENIOR(16);

    private final int value;

    AgeGroup(int value)
    {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
