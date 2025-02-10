package tn.esprit.atlas.entities;

import java.util.List;

public class Forfait {
    private int packaged;
    private String name;
    private String description;
    private double price;
    private int duration;
    private List<String> destinations;
    private int availableSeats;
    private String packageImage;

    // Constructors
    public Forfait() {}

    public Forfait(int packaged, String name, String description, double price, int duration, List<String> destinations, int availableSeats, String packageImage) {
        this.packaged = packaged;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.destinations = destinations;
        this.availableSeats = availableSeats;
        this.packageImage = packageImage;
    }

    // Getters and Setters
    public int getPackaged() { return packaged; }
    public void setPackaged(int packaged) { this.packaged = packaged; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public List<String> getDestinations() { return destinations; }
    public void setDestinations(List<String> destinations) { this.destinations = destinations; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public String getPackageImage() { return packageImage; }
    public void setPackageImage(String packageImage) { this.packageImage = packageImage; }

    // Methods
    public double calculerPrix() {
        // Example logic to calculate price based on duration and destinations
        return price * duration;
    }

    public String getPackageDetails() {
        return "Forfait{" +
                "packaged=" + packaged +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", destinations=" + destinations +
                ", availableSeats=" + availableSeats +
                ", packageImage='" + packageImage + '\'' +
                '}';
    }

    public boolean checkAvailability() {
        return availableSeats > 0;
    }

    public void modifierAvailability(int seats) {
        this.availableSeats += seats;
    }

    // To String (For Debugging)
    @Override
    public String toString() {
        return getPackageDetails();
    }
}