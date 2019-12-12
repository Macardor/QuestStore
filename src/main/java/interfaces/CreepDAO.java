package interfaces;

import models.users.Mentor;
import models.users.User;

public interface CreepDAO {
    void addMentor(Mentor mentor);
    void deleteMentor(int id);
    void editMentor(int id);

}
