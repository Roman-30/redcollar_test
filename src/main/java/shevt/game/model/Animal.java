package shevt.game.model;

public class Animal extends Node {
    // TODO: 15.09.2023 Подкрутить ломбок

    /**
     *
     */


    public Animal(String label) {
        super(label);
    }

    /**
     *
     * @return
     */



    @Override
    public String toString() {
        return this.getLabel();
    }
}
