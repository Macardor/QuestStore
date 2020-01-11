package view;

public class StaticUi {
    // tutaj dodajemy souty i inputy do poglądowej wersji

    public void showLoginMenu(){
        String loginMenu = "Login menu\n" +
                "1) Login.";
        System.out.println(loginMenu);

    }

    public void errorMassage(){
        System.out.println("You can chose only from menu options !!!");
    }
}
