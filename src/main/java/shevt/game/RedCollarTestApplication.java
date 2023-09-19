package shevt.game;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import shevt.game.model.Graph;
import shevt.game.service.GameService;
import shevt.game.service.GraphService;
import shevt.game.service.SaveService;

import java.io.File;
import java.io.IOException;

import static shevt.game.service.SaveService.FILE_NAME;

@RequiredArgsConstructor
@SpringBootApplication
public class RedCollarTestApplication implements CommandLineRunner {
    private final GameService gameService;
    private final SaveService saveService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RedCollarTestApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws IOException {
        Graph dataBase;
        Resource resource = new ClassPathResource(FILE_NAME);
        File file = resource.getFile();
        try {
            if (file.length() == 0) {
                dataBase = gameService.createGame();
            } else {
                dataBase = saveService.loadGame(FILE_NAME);
            }
            gameService.runGame(dataBase);
            saveService.saveGame(dataBase, FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
