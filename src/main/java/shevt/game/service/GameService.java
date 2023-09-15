package shevt.game.service;


import shevt.game.model.Animal;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static shevt.game.service.ConsoleService.getAnswerFromConsole;
import static shevt.game.service.ConsoleService.readConsole;

public class GameService {

    public GameService() {
    }

    public Graph createGame() {
        Graph graph = new Graph();

        Animal cat = new Animal("Кот");
        Animal kit = new Animal("Кит");
        FactNode factNode = new FactNode("Живет на суше");

        factNode.getAnswerToAnimalMap().put(true, cat);
        factNode.getAnswerToAnimalMap().put(false, kit);

        graph.getFactNodes().add(factNode);
        return graph;
    }

    public void runGame(Graph graph) {
        do {
            FactNode factNode;
            if (graph.getFactNodes().size() == 1) {
                factNode = graph.getFactNodes().get(0);
            } else {
                factNode = findPopularProps(graph.getFactNodes());
            }
            // TODO: 15.09.2023 аптимизировать 34-39
            System.out.println("Загадай животное, а я попробую угадать...");
            Boolean answerEnum = getAnswerFromConsole(String.format("%s (да/нет) \n> ", factNode));

            List<FactNode> properties = factNode.getProperties().get(answerEnum);
            Stack<FactNode> animals = new Stack<>();
            if (properties != null) animals.addAll(properties);
            // TODO: 15.09.2023 аптимизировать 43 - 49
            if (animals.isEmpty()) {
                finalCondition(graph, answerEnum, factNode);
            } else {
                var res = doSteps(graph, animals, answerEnum);
                if (res == null) {
                    finalCondition(graph, answerEnum, factNode);
                }
            }

            // TODO: 15.09.2023 Apache и Guava для консоли

        } while (getAnswerFromConsole("Желаете продолжить? (да/нет) \n> "));
    }


    private FactNode doSteps(Graph graph, Stack<FactNode> properties, Boolean firstAnswerEnum) {
        if (properties.isEmpty()) {
            return new FactNode(null);
        }
        FactNode currentFactNode = properties.pop();
        Boolean answerEnum1 = getAnswerFromConsole(String.format("%s (да/нет) \n> ", currentFactNode.getLabel()));
        if (answerEnum1) {
            List<FactNode> sd = currentFactNode.getProperties().get(answerEnum1); // TODO: 15.09.2023 sd
            if (sd != null) {
                Stack<FactNode> factNodeStack = new Stack<>();
                factNodeStack.addAll(sd);
                doSteps(graph, factNodeStack, firstAnswerEnum);
            } else {
                if (currentFactNode.getLabel().equals("Живет на суше")) {
                    System.out.printf("Это %s, (да/нет)?\n> ", currentFactNode.getAnswerToAnimalMap().get(firstAnswerEnum));
                } else {
                    System.out.printf("Это %s, (да/нет)?\n> ", currentFactNode.getAnswerToAnimalMap().get(firstAnswerEnum));
                }
                Boolean answerEnum = getAnswerFromConsole(null);
                if (answerEnum) {
                    System.out.println("Угадал!\n");
                } else {
                    addDataToDB(graph, currentFactNode, true); // TODO: 15.09.2023 naming
                }
                return currentFactNode;
            }
        } else {
            doSteps(graph, properties, firstAnswerEnum);
        }
        return answerEnum1 ? new FactNode(null) : null;
    }

    private void addDataToDB(Graph graph, FactNode factNode, Boolean answerEnum) {
        String animalName = readConsole("Какое животное ты загадал? \n> ");
        // TODO: 15.09.2023 read console переделать
        String characteristic = readConsole(String.format("Чем “%s” отличается от “%s”?\n> ",
                animalName, factNode.getAnswerToAnimalMap().get(answerEnum)));

        FactNode newFactNode = new FactNode(characteristic);
        Animal animal = new Animal(animalName);

        if (factNode.getLabel().equals("Живет на суше")) {
            // List<Property> properties = property.getProperties().get(answer);
            List<FactNode> properties;
            if (factNode.getProperties().get(answerEnum) != null) {
                properties = new ArrayList<>(factNode.getProperties().get(answerEnum));
                properties.add(newFactNode);
            } else {
                properties = new ArrayList<>();
                properties.add(newFactNode);
            }
            factNode.getProperties().put(answerEnum, properties);
        } else {
            List<FactNode> properties;
            if (factNode.getProperties().get(true) != null) {
                properties = new ArrayList<>(factNode.getProperties().get(true));
                properties.add(newFactNode);
            } else {
                properties = new ArrayList<>();
                properties.add(newFactNode);
            }
            factNode.getProperties().put(true, properties);
        }
        newFactNode.getAnswerToAnimalMap().put(answerEnum, animal);
        graph.getFactNodes().add(newFactNode);
    }

    private void finalCondition(Graph graph, Boolean answerEnum, FactNode factNode) {
        System.out.printf("Это %s, (да/нет)?\n> ", factNode.getAnswerToAnimalMap().get(answerEnum));
        Boolean answerEnum1 = getAnswerFromConsole(null);
        if (answerEnum1) {
            System.out.println("Угадал!\n");
        } else {
            addDataToDB(graph, factNode, answerEnum);
        }
    }

    private FactNode findPopularProps(List<FactNode> properties) { // TODO: 15.09.2023 изменить
        int maxAnimal = 0;
        FactNode out = null;
        for (FactNode factNode : properties) {
            int currentSum = 0;
            for (Map.Entry<Boolean, List<FactNode>> propertyEntry : factNode.getProperties().entrySet()) {
                currentSum += propertyEntry.getValue().size();
            }
            if (currentSum > maxAnimal) {
                maxAnimal = currentSum;
                out = factNode;
            }
        }
        return out;
    }
}
