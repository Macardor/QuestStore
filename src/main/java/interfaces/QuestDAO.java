package interfaces;

public interface QuestDAO {
    void addQuest(String name, String description, int reward, boolean isActive);
    void deleteQuest(int id);
    void editQuest(int id);
    void markQuest(int id);
}
