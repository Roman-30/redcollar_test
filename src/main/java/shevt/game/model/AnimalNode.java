package shevt.game.model;

import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

public class AnimalNode extends Node implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    public AnimalNode(String label) {
        super(label);
    }
    @Override
    public String toString() {
        return this.getLabel();
    }
}
