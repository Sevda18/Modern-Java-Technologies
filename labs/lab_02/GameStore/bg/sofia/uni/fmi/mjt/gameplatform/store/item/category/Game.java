package bg.sofia.uni.fmi.mjt.gameplatform.store.item.category;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Game implements StoreItem {
    private String title;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private String genre;
    private double rating;
    private int ratingCount;

    public String getTitle(){
        return title;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public LocalDateTime getReleaseDate(){
        return releaseDate;
    }

    public double getRating() {
        return rating/ratingCount;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPrice(BigDecimal price){
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setReleaseDate(LocalDateTime releaseDate){
        this.releaseDate = releaseDate;
    }

    public void rate(double rating){
        ratingCount++;
        this.rating = ((this.rating + rating)*1.0) / 2;
    }

    public Game(String title, BigDecimal price, LocalDateTime releaseDate, String genre) {
        setTitle(title);
        setPrice(price);
        setReleaseDate(releaseDate);
        this.genre = genre;
        rating = 0;
        ratingCount = 0;
    }
}