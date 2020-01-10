package models;

public class Item {
    private int id;
    private String name;
    private int price;
    private String description;
    private boolean isActive;

    public Item(int id, String name, int price, String description, boolean isActive){

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.isActive = isActive;
    }

    public Item(String name, int price, String description, boolean isActive){
        this.name = name;
        this.price = price;
        this.description = description;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }
}
