package shevt.game.service;

import org.springframework.stereotype.Component;
import shevt.game.model.AnimalNode;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

@Component
public class GraphService {
    public Graph createDataBase() {
        Graph graph = new Graph();

        AnimalNode cat = new AnimalNode("Кот");
        AnimalNode whale = new AnimalNode("Кит");
        FactNode factNode = new FactNode("живет на суше");

        factNode.getAnswerToAnimalMap().put(true, cat);
        factNode.getAnswerToAnimalMap().put(false, whale);

        graph.getFactNodes().add(factNode);
        return graph;
    }
}
