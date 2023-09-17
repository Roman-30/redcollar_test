package shevt.game.model;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Graph implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<FactNode> factNodes;

    public Graph(List<FactNode> factNodes) {
        this.factNodes = factNodes;
    }

    public Graph() {
        factNodes = new ArrayList<>();
    }

}
