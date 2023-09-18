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

    private static final String FILE_NAME = "test_file.ser";
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

        saveService.saveGame(graph, FILE_NAME);
        Graph loadState = saveService.loadGame(FILE_NAME);

        Assertions.assertEquals(cat, loadState.getFactNodes().get(0).getAnswerToAnimalMap().get(true));
        Assertions.assertEquals(whale, loadState.getFactNodes().get(0).getAnswerToAnimalMap().get(false));
        Assertions.assertEquals(factNode, loadState.getFactNodes().get(0));
    }

}