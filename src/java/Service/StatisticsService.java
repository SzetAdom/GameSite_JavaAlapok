package Service;

import Model.Statistics;
import Repository.StatisticsRepo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author adams
 */
public class StatisticsService {

    public static Boolean addStatistics(Statistics statistics) {
        System.out.println("------------------------");
        System.out.println("addStatistics");
        if (statistics.getPlayedMinutes() >= 0
                && new Date().after(statistics.getFirstPlayed())
                && (statistics.getLastPlayed().after(statistics.getFirstPlayed())
                || statistics.getLastPlayed().equals(statistics.getFirstPlayed()))) {
            return StatisticsRepo.addStatistics(statistics);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Statistics getStatisticsById(Integer id) {
        System.out.println("------------------------");
        System.out.println("getStatistics");
        if (id > 0) {
            return StatisticsRepo.getStatisticsById(id);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }

    }

    public static List<Statistics> getAllActiveStatistics() {
        System.out.println("------------------------");
        System.out.println("getAllActiveStatistics");
        return StatisticsRepo.getAllActiveStatistics();
    }

    public static Boolean updateStatistics(Statistics statistics) {
        System.out.println("------------------------");
        System.out.println("updateStatistics");
        if (statistics.getPlayedMinutes() >= 0
                && new Date().after(statistics.getFirstPlayed())
                && (statistics.getLastPlayed().after(statistics.getFirstPlayed())
                || statistics.getLastPlayed().equals(statistics.getFirstPlayed()))) {
            return StatisticsRepo.updateStatistics(statistics);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Boolean deleteStatistics(Integer id) {
        System.out.println("------------------------");
        System.out.println("deleteStatistics");
        if (id > 0) {
            return StatisticsRepo.deleteStatistics(id);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Integer getTotalPlayedMinutes() {
        System.out.println("------------------------");
        System.out.println("getTotalPlayedMinutes");
        return StatisticsRepo.getTotalPlayedMinutes();
    }

    public static List<Integer> getMostActivePlayer() {
        System.out.println("------------------------");
        System.out.println("getMostActivePlayer");
        return StatisticsRepo.getMostActivePlayer();
    }

}
