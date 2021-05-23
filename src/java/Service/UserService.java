package Service;

import Model.User;
import Repository.UserRepo;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class UserService {

    public static Boolean addUser(User user) {
        System.out.println("------------------------");
        System.out.println("addUser");

        Boolean emailCheck = Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", user.getEmail());
        Boolean passwordCheck = Pattern.matches("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$", user.getPassword());

        if (new Date().after(user.getBirthDate())
                && emailCheck
                && passwordCheck) {
            return UserRepo.addUser(user);
        } else {
            System.out.println("Hibás értékek");
            System.out.println("Email: " + emailCheck);
            System.out.println("Password: " + passwordCheck);
            return null;
        }
    }

    public static User getUserById(Integer id) {
        System.out.println("------------------------");
        System.out.println("getUser");
        if (id > 0) {
            return UserRepo.getUserById(id);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }

    }

    public static List<User> getAllActiveUser() {
        System.out.println("------------------------");
        System.out.println("getAllActiveUser");
        return UserRepo.getAllActiveUser();
    }

    public static Boolean updateUser(User user) {
        System.out.println("------------------------");
        System.out.println("updateUser");

        Boolean emailCheck = Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", user.getEmail());
        if (user.getUserId() > 0
                && new Date().after(user.getBirthDate())
                && emailCheck) {
            return UserRepo.updateUser(user);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Boolean deleteUser(Integer id) {
        System.out.println("------------------------");
        System.out.println("deleteUser");
        if (id > 0) {
            return UserRepo.deleteUser(id);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Boolean updatePassword(Integer id, String old, String password) {
        System.out.println("------------------------");
        System.out.println("updatePassword");

        Boolean passwordCheck = Pattern.matches("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$", password);
        if (id > 0 && passwordCheck) {
            return UserRepo.updatePassword(id, old, password);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Boolean login(String name, String password) {
        System.out.println("------------------------");
        System.out.println("login");

        return UserRepo.login(name, password);
    }
}
