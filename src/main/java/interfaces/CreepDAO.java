package interfaces;

import models.Mentor;

public interface CreepDAO {
    void addMentor(Mentor mentor);
    void editMentor(Mentor mentor);
    void deleteMentor(int id);

}
