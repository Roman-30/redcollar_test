package shevt.game.model;

import lombok.ToString;

public class AnimalNode extends Node {

    public AnimalNode(String label) {
        super(label);
    }
    @Override
    public String toString() {
        return this.getLabel();
    }
}
