package Service;

import Model.Game;
import Repository.GameRepo;

/**
 *
 * @author adams
 */
public class GameService {

    public static Boolean addGame(Game game) {
        System.out.println("------------------------");
        System.out.println("addGame");
        return GameRepo.addGame(game);
    }

//    public static Game getGame(int id) {
//        System.out.println("------------------------");
//        System.out.println("getGame");
//        return GameRepo.getGame(id);
//    }
//
//    public static Boolean getAllGame() {
//        System.out.println("------------------------");
//        System.out.println("getAllGame");
//        return GameRepo.getAllGame();
//    }
//
    public static Boolean updateGame(Game game) {
        System.out.println("------------------------");
        System.out.println("updateGame");
        return GameRepo.updateGame(game);
    }
//
//    public static Boolean deleteGame(int id) {
//        System.out.println("------------------------");
//        System.out.println("deleteGame");
//        return GameRepo.deleteGame(id);
//    }
//
//    public static Boolean likeGame(int id) {
//        System.out.println("------------------------");
//        System.out.println("likeGame");
//        return GameRepo.likeGame(id);
//    }
//
//    public static Boolean dislikeGame(int id) {
//        System.out.println("------------------------");
//        System.out.println("dislikeGame");
//        return GameRepo.dislikeGame(id);
//    }
}
