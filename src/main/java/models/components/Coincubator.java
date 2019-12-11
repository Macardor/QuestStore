package models.components;

import models.users.User;

import java.util.List;

public class Coincubator {
    private int id;
    private String name;
    private String description;
    private int currentDonation;
    private int targetDonation;
    private boolean isActive;
    private List<User> donators;

    public Coincubator(int id, String name, String description, int currentDonation, int targetDonation, boolean isActive, List<User> donators){
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentDonation = currentDonation;
        this.targetDonation = targetDonation;
        this.isActive = isActive;
        this.donators = donators;
    }
}
