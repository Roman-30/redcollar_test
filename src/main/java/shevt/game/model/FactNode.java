package shevt.game.model;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
public class FactNode extends Node implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private final Map<Boolean, AnimalNode> answerToAnimalMap;
    private final Map<Boolean, List<FactNode>> answerToFactsMap;

    public FactNode(String label) {
        super(label);
        answerToAnimalMap = new HashMap<>();
        answerToFactsMap = new HashMap<>();
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
