package shevt.game.service;

import shevt.game.model.Graph;

import java.io.*;

public class SaveService {

    private static final String FILE_PATH = "src/main/resources/statemant/state.ser";

    public void saveGame(Graph dataBase, String path) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(dataBase);
        objectOutputStream.close();
    }

    public Graph loadGame(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Graph) objectInputStream.readObject();
    }
}
