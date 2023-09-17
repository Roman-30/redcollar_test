package shevt.game.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import shevt.game.model.AnimalNode;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

import java.io.IOException;
@SpringBootTest
class SaveServiceTest {
    @Test
    void saveGame() throws IOException, ClassNotFoundException {
        SaveService saveService = new SaveService();
        Graph graph = new Graph();

        AnimalNode cat = new AnimalNode("Кот");
        AnimalNode whale = new AnimalNode("Кит");
        FactNode factNode = new FactNode("живет на суше");

        factNode.getAnswerToAnimalMap().put(true, cat);
        factNode.getAnswerToAnimalMap().put(false, whale);

        graph.getFactNodes().add(factNode);

        saveService.saveGame(graph, "src/test/java/resources/test_file.ser");
        Graph loadState = saveService.loadGame("src/test/java/resources/test_file.ser");

        Assertions.assertEquals(loadState.getFactNodes().get(0).getAnswerToAnimalMap().get(true), cat);
        Assertions.assertEquals(loadState.getFactNodes().get(0).getAnswerToAnimalMap().get(false), whale);
    }

}