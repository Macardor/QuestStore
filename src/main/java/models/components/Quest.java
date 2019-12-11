package models.components;

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
}
