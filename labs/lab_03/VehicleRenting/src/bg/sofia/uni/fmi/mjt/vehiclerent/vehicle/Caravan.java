package bg.sofia.uni.fmi.mjt.vehiclerent.vehicle;

import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;

import java.time.Duration;
import java.time.LocalDateTime;

public class Caravan extends Vehicle{
    private FuelType fuelType;
    private int numberOfSeats;
    private int numberOfBeds;
    private double pricePerWeek;
    private double pricePerDay;
    private double pricePerHour;
    private double pricePerSeat = 5;
    private double pricePerBed = 10;

    public Caravan(String id, String model, FuelType fuelType, int numberOfSeats, int numberOfBeds, double pricePerWeek, double pricePerDay, double pricePerHour) {
        super(id, model);
        this.fuelType = fuelType;
        this.numberOfSeats = numberOfSeats;
        this.numberOfBeds = numberOfBeds;
        this.pricePerWeek = pricePerWeek;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public double calculateRentalPrice(LocalDateTime startOfRent, LocalDateTime endOfRent) throws InvalidRentingPeriodException {
        Duration duration = Duration.between(startOfRent, endOfRent);
        if(duration.toHours() < 23)
        {
            throw new InvalidRentingPeriodException("Caravan cannot be rented for under 24 hours");
        }

        long period = duration.toDays();
        int week = (int)(period /7);
        int days = (int) period % 7;
        int hours = (int)(duration.toHours() % 24);

        double price = week*pricePerWeek + days*pricePerDay+hours*pricePerHour
                + numberOfBeds*pricePerBed + numberOfSeats*pricePerSeat +
                fuelType.getValue()*(week*7+days) + getDriver().getAgeGroup().getValue();;
    }
}
