package shevt.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shevt.game.model.Graph;
import shevt.game.service.GameService;

@SpringBootApplication
public class RedCollarTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedCollarTestApplication.class, args);
        GameService gs = new GameService();
        Graph game = gs.createGame();
        gs.runGame(game);
    }

}
