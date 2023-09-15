package shevt.game.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactNode extends Node {
    private final Map<Boolean, Animal> answerToAnimalMap;
    private final Map<Boolean, List<FactNode>> answerToFactsMap;

    public FactNode(String label) {
        super(label);
        answerToAnimalMap = new HashMap<>();
        answerToFactsMap = new HashMap<>();
    }

    public Map<Boolean, Animal> getAnswerToAnimalMap() {
        return answerToAnimalMap;
    }


    public Map<Boolean, List<FactNode>> getProperties() {
        return answerToFactsMap;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
