package shevt.game.model;

public abstract class Node {
    private final String label;

    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
