package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;

import java.time.Duration;
import java.time.LocalDateTime;

public class Car extends Vehicle{
    private FuelType fuelType;
    private int numberOfSeats;
    private double pricePerWeek;
    private double pricePerDay;
    private double pricePerHour;
    private double pricePerSeat = 5;

    public Car(String id, String model, FuelType fuelType, int numberOfSeats, double pricePerWeek, double pricePerDay, double pricePerHour) {
        super(id, model);
        this.fuelType = fuelType;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.pricePerWeek = pricePerWeek;
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException {
        Duration duration =  Duration.between(startOfRent, endOfRent);

        long period = duration.toDays();
        double taxFuel = (int)(period * fuelType.getValue());
        int weeks = (int)(period / 7);
        int remainigDays = (int)(period%7);
        duration = duration.minusDays(period);
        double price = weeks * pricePerWeek + remainigDays*pricePerDay +
                duration.toHours() * pricePerHour + taxFuel*(weeks*7+remainigDays) + numberOfSeats*pricePerSeat
                + getDriver().getAgeGroup().getValue();

        return price;
    }
}
