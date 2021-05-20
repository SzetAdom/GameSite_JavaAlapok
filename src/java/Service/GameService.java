package Service;

import Model.Game;
import Repository.GameRepo;
import java.util.List;

/**
 *
 * @author adams
 */
public class GameService {

    public static Boolean addGame(Game game) {
        System.out.println("------------------------");
        System.out.println("addGame");
        if (game.getGameId() > 0
                && game.getName().length() < 50
                && game.getCategory().length() < 30
                && game.getDescription().length() < 255) {
            return GameRepo.addGame(game);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

//    public static Game getGame(Integer id) {
//        System.out.println("------------------------");
//        System.out.println("getGame");
//        if (id > 0) {
//            return GameRepo.getGame(id);
//        } else {
//            System.out.println("Hibás értékek");
//            return null;
//        }
//
//    }
    public static List<Game> getAllActiveGames() {
        System.out.println("------------------------");
        System.out.println("getAllActiveGames");
        return GameRepo.getAllActiveGames();
    }

    public static Boolean updateGame(Game game) {
        System.out.println("------------------------");
        System.out.println("updateGame");
        if (game.getGameId() > 0
                && game.getName().length() < 50
                && game.getCategory().length() < 30
                && game.getDescription().length() < 255) {
            return GameRepo.updateGame(game);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Boolean deleteGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("deleteGame");
        if (id > 0) {
            return GameRepo.deleteGame(id);
        } else {
            System.out.println("Hibás értékek");
            return false;
        }
    }

    public static Boolean likeGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("likeGame");
        if (id > 0) {
            return GameRepo.likeGame(id);
        } else {
            System.out.println("Hibás értékek");
            return false;
        }
    }

    public static Boolean dislikeGame(Integer id) {
        System.out.println("------------------------");
        System.out.println("dislikeGame");
        if (id > 0) {
            return GameRepo.dislikeGame(id);
        } else {
            System.out.println("Hibás értékek");
            return false;
        }
    }
}
