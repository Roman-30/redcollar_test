package shevt.game.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shevt.game.model.AnimalNode;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GraphServiceTest {

    @Test
    void createDataBase() {
        GraphService gs = new GraphService();
        AnimalNode cat = new AnimalNode("Кот");
        AnimalNode whale = new AnimalNode("Кит");
        FactNode factNode = new FactNode("живет на суше");

        Graph dataBase = gs.createDataBase();

        Assertions.assertEquals(cat, dataBase.getFactNodes().get(0).getAnswerToAnimalMap().get(true));
        Assertions.assertEquals(whale, dataBase.getFactNodes().get(0).getAnswerToAnimalMap().get(false));
        Assertions.assertEquals(factNode, dataBase.getFactNodes().get(0));
    }
}