package services;

import DAO.MentorDAO;
import models.Mentor;
import models.User;

public class MentorService {

    MentorDAO mentorDAO = new MentorDAO();

    public int getUserDetailsId(User mentor){
        return mentorDAO.getUserDetailsId(mentor);
    }

    public void editMentor(Mentor mentorToEdit, int mentorDetailsId) {
        mentorDAO.editMentor(mentorToEdit, mentorDetailsId);
    }
}
