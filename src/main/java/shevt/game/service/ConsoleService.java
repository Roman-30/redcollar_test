package shevt.game.service;

import org.springframework.stereotype.Component;
import shevt.game.model.Node;

import java.util.Scanner;

@Component
public class ConsoleService {

    public static final String START_WORDS = "Загадай животное, а я попробую угадать...\n";
    public static final String WIN_WORD = "Угадал!\n";
    public static final String REPEAT_ANSWER = "Извините, не понял вашего ответа!\nПовторите ваш ответ (да/нет) \n> ";
    public static final String WHAT_IS_ANIMAL = "Какое животное ты загадал? \n> ";
    public static final String WHAT_IS_DIFFERENT = "Чем “%s” отличается от “%s”?\n> ";
    public static final String KEY_QUESTION = "живет на суше";
    public static final String REPEAT_GAME = "Желаете продолжить? (да/нет)\n> ";
    public static final String FACT_PATTERN = "Это животное %s? (да/нет) \n> ";
    public static final String ANIMAL_PATTERN = "Это %s? (да/нет) \n> ";

    private String readConsole() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine().toLowerCase().trim();
    }

    public String readAnimalNameFromConsole() {
        printInformationForPlayer(WHAT_IS_ANIMAL);
        return readConsole();
    }

    public String readFactFromConsole(String playersAnimal, Node dbAnimal) {
        printInformationForPlayer(String.format(WHAT_IS_DIFFERENT, playersAnimal, dbAnimal));
        return readConsole();
    }

    public void printInformationForPlayer(String text) {
        System.out.print(text);
    }

    public Boolean getAnswerForAnimal(Node animal) {
        printInformationForPlayer(String.format(ANIMAL_PATTERN, animal));
        return getAnswerFromConsole();
    }

    public Boolean getAnswerForFact(Node fact) {
        printInformationForPlayer(String.format(FACT_PATTERN, fact));
        return getAnswerFromConsole();

    }

    public Boolean getAnswerForContinueGame() {
        printInformationForPlayer(REPEAT_GAME);
        return getAnswerFromConsole();
    }

    private Boolean getAnswerFromConsole() {
        do {
            String line = readConsole();
            if (line.equals("да")) {
                return true;
            } else if (line.equals("нет")) {
                return false;
            } else {
                printInformationForPlayer(REPEAT_ANSWER);
            }
        } while (true);
    }
}
