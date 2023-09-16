package shevt.game;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import shevt.game.model.Graph;
import shevt.game.service.GameService;

@RequiredArgsConstructor
@SpringBootApplication
public class RedCollarTestApplication implements CommandLineRunner {
    private final GameService gs;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RedCollarTestApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        Graph game = gs.createGame();
        gs.runGame(game);
    }
}
