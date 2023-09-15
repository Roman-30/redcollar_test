package shevt.game.service;

import java.util.Scanner;

public class ConsoleService {
    public static String readConsole(String line) {
        Scanner scn = new Scanner(System.in);
        if (line != null)
            System.out.print(line);
        return scn.nextLine().toLowerCase().trim();
    }

    public static Boolean getAnswerFromConsole(String text) {
        boolean flag;
        Boolean answerEnum = null;
        String line = readConsole(text);
        do {
            flag = false;
            if (line.equals("да")) {
                answerEnum = true;
            } else if (line.equals("нет")) {
                answerEnum = false;
            } else {
                System.out.println("Извините, не понял вашего ответа");
                System.out.print("Повторите ваш ответ (да/нет) \n> ");
                line = readConsole(null);
                flag = true;
            }
        } while (flag);
        return answerEnum;
    }
}
