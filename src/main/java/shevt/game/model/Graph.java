package shevt.game.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<FactNode> factNodes;

    public Graph(List<FactNode> factNodes) {
        this.factNodes = factNodes;
    }

    public Graph() {
        factNodes = new ArrayList<>();
    }

    public List<FactNode> getFactNodes() {
        return factNodes;
    }

}
