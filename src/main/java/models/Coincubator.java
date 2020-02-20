package models;

import models.User;

import java.util.List;

public class Coincubator {
    private int id;
    private String name;
    private String description;
    private int currentDonation;
    private int targetDonation;
    private boolean isActive;
    private List<User> donators;

    public Coincubator(int id, String name, String description, int currentDonation, int targetDonation, boolean isActive){
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentDonation = currentDonation;
        this.targetDonation = targetDonation;
        this.isActive = isActive;
        this.donators = donators;
    }
    public Coincubator(String name, String description, int currentDonation, int targetDonation, boolean isActive){
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentDonation = currentDonation;
        this.targetDonation = targetDonation;
        this.isActive = isActive;
        this.donators = donators;
    }

    public Coincubator(int id, String name, String description, int currentDonation, int targetDonation, boolean isActive, List<User> donators){
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentDonation = currentDonation;
        this.targetDonation = targetDonation;
        this.isActive = isActive;
        this.donators = donators;
    }
    public Coincubator(String name, String description, int targetDonation){
        this.name = name;
        this.description = description;
        this.currentDonation = 0;
        this.targetDonation = targetDonation;
    }
    public Coincubator(String name, String description, int targetDonation, boolean isActive){
        this.name = name;
        this.description = description;
        this.currentDonation = 0;
        this.targetDonation = targetDonation;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentDonation() {
        return currentDonation;
    }

    public void setCurrentDonation(int currentDonation) {
        this.currentDonation = currentDonation;
    }

    public int getTargetDonation() {
        return targetDonation;
    }

    public void setTargetDonation(int targetDonation) {
        this.targetDonation = targetDonation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<User> getDonators() {
        return donators;
    }

    public void setDonators(List<User> donators) {
        this.donators = donators;
    }
}
