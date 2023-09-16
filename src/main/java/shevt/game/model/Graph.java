package shevt.game.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Graph {
    private final List<FactNode> factNodes;

    public Graph(List<FactNode> factNodes) {
        this.factNodes = factNodes;
    }

    public Graph() {
        factNodes = new ArrayList<>();
    }

}
