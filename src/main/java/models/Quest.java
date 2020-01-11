package models;

public class Quest {
    private int id;
    private String name;
    private String description;
    private int reward;
    private boolean isActive;

    public Quest(int id, String name, String description, int reward, boolean isActive){

        this.id = id;
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.isActive = isActive;
    }
    public Quest(String name, String description, int reward, boolean isActive){
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getReward() {
        return reward;
    }

    public boolean isActive() {
        return isActive;
    }
}
