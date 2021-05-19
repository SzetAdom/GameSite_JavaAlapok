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
}
