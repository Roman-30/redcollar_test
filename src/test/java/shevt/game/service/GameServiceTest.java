package shevt.game.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shevt.game.model.AnimalNode;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

@SpringBootTest
class GameServiceTest {

    @Test
    void addFirstLayerDataToDB() {
        GraphService gs = new GraphService();
        ConsoleService cs = new ConsoleService();
        GameService gms = new GameService(cs, gs);
        Graph db = gms.createGame();

        AnimalNode shark = new AnimalNode("Акула");
        AnimalNode dog = new AnimalNode("Собака");
        FactNode factNode1 = new FactNode("не дышит без движения");
        FactNode factNode2 = new FactNode("лает");

        gms.addDataToDB(factNode1.getLabel(), shark.getLabel(), db.getFactNodes().get(0), false);
        gms.addDataToDB(factNode2.getLabel(), dog.getLabel(), db.getFactNodes().get(0), true);

        Assertions.assertEquals(factNode1, db.getFactNodes().get(0).getAnswerToFactsMap().get(false).get(0));
        Assertions.assertEquals(factNode2, db.getFactNodes().get(0).getAnswerToFactsMap().get(true).get(0));

        Assertions.assertEquals(shark, db.getFactNodes().get(0)
                .getAnswerToFactsMap().get(false).get(0).getAnswerToAnimalMap().get(true));
        Assertions.assertEquals(dog, db.getFactNodes().get(0)
                .getAnswerToFactsMap().get(true).get(0).getAnswerToAnimalMap().get(true));
    }

    @Test
    void addAnyLayerDataToDB() {
        GraphService gs = new GraphService();
        ConsoleService cs = new ConsoleService();
        GameService gms = new GameService(cs, gs);
        Graph db = gms.createGame();

        AnimalNode monkey = new AnimalNode("обезьяна");
        AnimalNode kingCong = new AnimalNode("кинг-конг");
        FactNode factNode1 = new FactNode("ест бананы");
        FactNode factNode2 = new FactNode("фантастическое");

        gms.addDataToDB(factNode1.getLabel(), monkey.getLabel(), db.getFactNodes().get(0), true);
        gms.addDataToDB(factNode2.getLabel(), kingCong.getLabel(),
                db.getFactNodes().get(0).getAnswerToFactsMap().get(true).get(0), true);

        Assertions.assertEquals(factNode1, db.getFactNodes().get(0).getAnswerToFactsMap().get(true).get(0));
        Assertions.assertEquals(factNode2, db.getFactNodes().get(0).getAnswerToFactsMap().get(true)
                .get(0).getAnswerToFactsMap().get(true).get(0));
    }
}