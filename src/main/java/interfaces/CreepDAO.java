package interfaces;

import models.Mentor;
import models.User;

import java.util.List;

public interface CreepDAO {

    List<User> showAllMentors();
    void addMentor(Mentor mentor);
    void editMentor(Mentor mentor);
    void deleteMentor(int id);

}
