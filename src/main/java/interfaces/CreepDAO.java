package interfaces;

import models.users.Mentor;
import models.users.User;

public interface CreepDAO {
    void addMentor(Mentor mentor);
    void editMentor(Mentor mentor);
    void deleteMentor(int id);

}
