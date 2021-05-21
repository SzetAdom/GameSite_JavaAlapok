package Repository;

import Model.Database;
import Model.Statistics;
import Model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author adams
 */
public class StatisticsRepo {

    public static Boolean addStatistics(Statistics statistics) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("addStatistics");

                spq.registerStoredProcedureParameter("in_game_id", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_user_id", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_first_played", Date.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_last_played", Date.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_played_minutes", Integer.class, ParameterMode.IN);

                spq.setParameter("in_game_id", statistics.getGameId().getGameId());
                spq.setParameter("in_user_id", statistics.getUserId().getUserId());
                spq.setParameter("in_first_played", statistics.getFirstPlayed());
                spq.setParameter("in_last_played", statistics.getLastPlayed());
                spq.setParameter("in_played_minutes", statistics.getPlayedMinutes());
                spq.execute();

                em.close();

                System.out.println("Statistics sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: addStatistics - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static List<Statistics> getAllActiveStatistics() {

        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveStatistics");
                List<Statistics> result = new ArrayList<>();

                List<Object[]> statisticsList = spq.getResultList();
                for (Object[] statistics : statisticsList) {
                    int id = Integer.parseInt(statistics[0].toString());
                    Statistics s = em.find(Statistics.class, id);
                    result.add(s);
                }

                em.close();

                return result;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getAllActiveStatistics - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Statistics getStatisticsById(Integer id) {

        try {

            EntityManager em = Database.getDbConn();
            try {
                Statistics statistics = em.find(Statistics.class, id);
                em.close();
                return statistics;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getStatisticsById - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean updateStatistics(Statistics statistics) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                Statistics uj = em.find(Statistics.class, statistics.getStatisticsId());
                uj.setGameId(statistics.getGameId());
                uj.setUserId(statistics.getUserId());
                uj.setFirstPlayed(statistics.getFirstPlayed());
                uj.setLastPlayed(statistics.getLastPlayed());
                uj.setPlayedMinutes(statistics.getPlayedMinutes());
                em.getTransaction().commit();

                System.out.println("Statistics sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: updateStatistics - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean deleteStatistics(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteStatistics");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();

                System.out.println("Statistics sikeresen törölve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: deleteStatistics - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Integer getTotalPlayedMinutes() {
        try {

            EntityManager em = Database.getDbConn();
            Integer totalPlayedMinutes = -1;
            try {

                StoredProcedureQuery spq = em.createStoredProcedureQuery("getTotalPlayedMinutes");

                spq.registerStoredProcedureParameter("out_total_played_minutes", Integer.class, ParameterMode.OUT);

                spq.setParameter("out_total_played_minutes", totalPlayedMinutes);

                spq.execute();

                em.close();

                System.out.println("TotalPlayedMinutes sikeresen lekérdezve!");
                return totalPlayedMinutes;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getTotalPlayedMinutes - " + ex.getMessage());
                return totalPlayedMinutes;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static List<User> getMostActivePlayer() {
        try {
            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("getMostActivePlayer");

                List<User> result = new ArrayList<>();

                List<Object[]> userList = spq.getResultList();
                for (Object[] user : userList) {
                    int id = Integer.parseInt(user[0].toString());
                    User u = em.find(User.class, id);
                    result.add(u);
                }
                return result;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getMostActivePlayer - " + ex.getMessage());
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

}
