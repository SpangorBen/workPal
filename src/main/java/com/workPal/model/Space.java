package main.java.com.workPal.model;

public class Space implements Entity<Integer> {
    private Integer id;
    private SpaceType spaceType;
    private Manager manager;
    private int capacity;
    private boolean isAvailable;
    private String description;
    private double price;

    public Space() {
    }

    public Space(SpaceType spaceType, Manager manager, int capacity, boolean isAvailable, String description, double price) {
        this.spaceType = spaceType;
        this.manager = manager;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.description = description;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public SpaceType getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(SpaceType spaceType) {
        this.spaceType = spaceType;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", spaceType=" + spaceType +
                ", manager=" + manager +
                ", capacity=" + capacity +
                ", isAvailable=" + isAvailable +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
