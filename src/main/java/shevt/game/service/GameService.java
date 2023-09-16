package shevt.game.service;


import org.springframework.stereotype.Component;
import shevt.game.model.AnimalNode;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static shevt.game.service.ConsoleService.getAnswerFromConsole;
import static shevt.game.service.ConsoleService.readConsole;

@Component
public class GameService {

    private final ConsoleService cs;

    public GameService(ConsoleService cs) {
        this.cs = cs;
    }

    public Graph createGame() {
        Graph graph = new Graph();

        AnimalNode cat = new AnimalNode("Кот");
        AnimalNode whale = new AnimalNode("Кит");
        FactNode factNode = new FactNode("Живет на суше");

        factNode.getAnswerToAnimalMap().put(true, cat);
        factNode.getAnswerToAnimalMap().put(false, whale);

        graph.getFactNodes().add(factNode);
        return graph;
    }

    public void runGame(Graph graph) {
        do {
            FactNode factNode = graph.getFactNodes().get(0);
            System.out.println("Загадай животное, а я попробую угадать...");
            Boolean answerEnum = getAnswerFromConsole(String.format("%s (да/нет) \n> ", factNode));

            List<FactNode> properties = factNode.getAnswerToFactsMap().get(answerEnum);
            Stack<FactNode> animals = new Stack<>();
            if (properties != null) animals.addAll(properties);
            // TODO: 15.09.2023 аптимизировать 43 - 49
            if (animals.isEmpty()) {
                finalCondition(answerEnum, factNode);
            } else {
                var res = doSteps(animals);
                if (!res) {
                    finalCondition(answerEnum, factNode);
                }
            }

            // TODO: 15.09.2023 Apache и Guava для консоли

        } while (getAnswerFromConsole("Желаете продолжить? (да/нет) \n> "));
    }

    private Boolean doSteps(Stack<FactNode> factNodes) {
        if (factNodes.isEmpty()) {
            return false;
        }
        FactNode currentFactNode = factNodes.pop();
        Boolean answer = getAnswerFromConsole(String.format("%s (да/нет) \n> ", currentFactNode.getLabel()));
        if (answer) {
            List<FactNode> currentFactNodes = currentFactNode.getAnswerToFactsMap().get(true);
            if (currentFactNodes != null) {
                Stack<FactNode> factNodeStack = new Stack<>();
                factNodeStack.addAll(currentFactNodes);
                Boolean doStepsResult = doSteps(factNodeStack);
                if (!doStepsResult) {
                    finalCondition(true, currentFactNode);
                    return true;
                }
            } else {
                finalCondition(true, currentFactNode);
                return true;
            }
        } else {
            answer = doSteps(factNodes);
        }
        return answer;
    }

    private void addDataToDB(FactNode factNode, Boolean answerEnum) {
        String animalName = readConsole("Какое животное ты загадал? \n> ");
        // TODO: 15.09.2023 read console переделать
        String characteristic = readConsole(String.format("Чем “%s” отличается от “%s”?\n> ",
                animalName, factNode.getAnswerToAnimalMap().get(answerEnum)));

        FactNode newFactNode = new FactNode(characteristic);
        AnimalNode animalNode = new AnimalNode(animalName);

        if (factNode.getLabel().equals("Живет на суше")) {
            // List<Property> properties = property.getProperties().get(answer);
            List<FactNode> properties;
            if (factNode.getAnswerToFactsMap().get(answerEnum) != null) {
                properties = new ArrayList<>(factNode.getAnswerToFactsMap().get(answerEnum));
                properties.add(newFactNode);
            } else {
                properties = new ArrayList<>();
                properties.add(newFactNode);
            }
            factNode.getAnswerToFactsMap().put(answerEnum, properties);
        } else {
            List<FactNode> properties;
            if (factNode.getAnswerToFactsMap().get(true) != null) {
                properties = new ArrayList<>(factNode.getAnswerToFactsMap().get(true));
                properties.add(newFactNode);
            } else {
                properties = new ArrayList<>();
                properties.add(newFactNode);
            }
            factNode.getAnswerToFactsMap().put(true, properties);
        }
        newFactNode.getAnswerToAnimalMap().put(true, animalNode);
        // graph.getFactNodes().add(newFactNode);
    }

    private void finalCondition(Boolean answerEnum, FactNode factNode) {
        System.out.printf("Это %s, (да/нет)?\n> ", factNode.getAnswerToAnimalMap().get(answerEnum));
        Boolean answerEnum1 = getAnswerFromConsole(null);
        if (answerEnum1) {
            System.out.println("Угадал!\n");
        } else {
            addDataToDB(factNode, answerEnum);
        }
    }
}
