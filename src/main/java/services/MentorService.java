package services;

import daoImplementation.MentorDAOImplementation;
import models.Mentor;
import models.User;

public class MentorService {

    MentorDAOImplementation mentorDAOImplementation = new MentorDAOImplementation();

    public int getUserDetailsId(User mentor){
        return mentorDAOImplementation.getUserDetailsId(mentor);
    }

    public void editMentor(Mentor mentorToEdit, int mentorDetailsId) {
        mentorDAOImplementation.editMentor(mentorToEdit, mentorDetailsId);
    }
}
