package Repository;

import Model.Database;
import Model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class UserRepo {

    public static Boolean addUser(User user) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("addUser");

                spq.registerStoredProcedureParameter("in_username", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_password", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_email", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_birth_date", Date.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_is_admin", Boolean.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_current_points", Integer.class, ParameterMode.IN);

                spq.setParameter("in_username", user.getUsername());
                spq.setParameter("in_password", user.getPassword());
                spq.setParameter("in_email", user.getEmail());
                spq.setParameter("in_birth_date", user.getBirthDate());
                spq.setParameter("in_is_admin", user.getIsadmin());
                spq.setParameter("in_current_points", user.getCurrentpoints());

                spq.execute();

                em.close();

                System.out.println("User sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: addUser - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static List<User> getAllActiveUser() {

        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveUser");
                List<User> result = new ArrayList<>();

                List<Object[]> users = spq.getResultList();
                for (Object[] user : users) {
                    int id = Integer.parseInt(user[0].toString());
                    User u = em.find(User.class, id);
                    result.add(u);
                }

                em.close();

                return result;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getAllActiveUsers - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static User getUserById(Integer id) {

        try {

            EntityManager em = Database.getDbConn();
            try {
                User game = em.find(User.class, id);
                em.close();
                return game;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getUserById - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean updateUser(User user) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                User uj = em.find(User.class, user.getUserId());
                uj.setUsername(user.getUsername());
                uj.setEmail(user.getEmail());
                uj.setBirthDate(user.getBirthDate());
                uj.setIsadmin(user.getIsadmin());
                uj.setCurrentpoints(user.getCurrentpoints());

                em.getTransaction().commit();

                System.out.println("User sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: updateUser - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean deleteUser(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteUser");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();

                System.out.println("User sikeresen törölve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: deleteUser - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean updatePassword(Integer id, String old, String password) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUserPassword");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_old", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_password", String.class, ParameterMode.IN);

                spq.setParameter("in_id", id);
                spq.setParameter("in_old", old);
                spq.setParameter("in_password", password);

                spq.execute();

                em.close();

                System.out.println("User jelszó sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: updatePassword - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean login(String username, String password) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("login");

                spq.registerStoredProcedureParameter("in_username", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_password", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("out_res", Integer.class, ParameterMode.OUT);

                spq.setParameter("in_username", username);
                spq.setParameter("in_password", password);

                spq.execute();

                Integer resInt = (Integer) spq.getOutputParameterValue("out_res");
                Boolean res = resInt == 1 ? true : false;

                em.close();

                System.out.println("Sikeres belépés próba!");
                return res;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: login - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }
}
