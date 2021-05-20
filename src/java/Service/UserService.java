
package Service;

import Model.User;
import Repository.UserRepo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class UserService {
    public static boolean isValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    public static boolean isStrongPassw(String password){
        boolean hasLetter = false;
        boolean hasDigit = false;

        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++){
                char x = password.charAt(i);
                if (Character.isLetter(x)){
                    hasLetter = true;
                }
                else if (Character.isDigit(x)){
                    hasDigit = true;
                }
                
                if(hasLetter && hasDigit){
                    break;
                }
            }
            if (hasLetter && hasDigit){
                System.out.println("erős");
                return true;
            } else {
                System.out.println("gyenge");                
            }
        } else {
            System.out.println("Kevesebb, mint 8 karakter.");
        }
        return false;
    }
    
    public static boolean isValidDate(String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = sdf.parse(date);

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        int result = date1.compareTo(date2);
        System.out.println("result: " + result);

        if (result > 0) {
            System.out.println("Date1 is after to Date2");
            return true;
        } 
        return false;
    }
    
    /*
    public static  List<User> getAllUsers(){
        return UserRepo.getAllUsers();
    }
    */
    
    public static String addNewUser(User user) throws ParseException{
        
        Boolean check = true;        
        String email = user.getEmail();
        String password = user.getPassword();
        
        Date date = user.getBirthDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        String strDate = dateFormat.format(date);
        
        if(!isValid(email)){
            check = false;
            return "Az email cím hibás.";
        }
        
        /*
        email és felhasználónév foglalt e már vagy nem
        */
        
        if(!isStrongPassw(password)){
            check = false;
        }
        
        if(!isValidDate(strDate)){
            check = false;
        }
        
        if(UserRepo.addNewUser(user) && check){
            return "Sikeres regisztráció!";
        }
        else{
            return "Sikertelen regisztráció!";
        }
    }
}
