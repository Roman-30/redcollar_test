package shevt.game.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import shevt.game.model.Graph;

import java.io.*;

@Component
public class SaveService {

    public static final String FILE_NAME = "config/state.ser";

    public void saveGame(Graph dataBase, String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        File file = resource.getFile();

        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(dataBase);
        objectOutputStream.close();
    }

    public Graph loadGame(String fileName) throws IOException, ClassNotFoundException {
        Resource resource = new ClassPathResource(fileName);
        File file = resource.getFile();

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Graph) objectInputStream.readObject();
    }
}
