package shevt.game.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shevt.game.model.AnimalNode;
import shevt.game.model.FactNode;
import shevt.game.model.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static shevt.game.service.ConsoleService.*;

@Component
@RequiredArgsConstructor
public class GameService {

    private final ConsoleService cs;
    private final GraphService gs;

    public Graph createGame() {
        return gs.createDataBase();
    }
    public void runGame(Graph graph) {
        do {
            FactNode factNode = graph.getFactNodes().get(0);
            cs.printInformationForPlayer(START_WORDS);
            Boolean answer = cs.getAnswerForFact(factNode);

            List<FactNode> properties = factNode.getAnswerToFactsMap().get(answer);
            Stack<FactNode> animals = new Stack<>();
            if (properties != null) {
                animals.addAll(properties);
            }

            if (animals.isEmpty()) {
                finalCondition(answer, factNode);
            } else {
                if (doSteps(animals)) {
                    finalCondition(answer, factNode);
                }
            }
        } while (cs.getAnswerForContinueGame());
    }

    private Boolean doSteps(Stack<FactNode> factNodes) {
        if (factNodes.isEmpty()) {
            return false;
        }
        FactNode currentFactNode = factNodes.pop();
        Boolean answer = cs.getAnswerForFact(currentFactNode);
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

    private void addDataToDB(FactNode factNode, Boolean answer) {
        String animalName = cs.readAnimalNameFromConsole();
        String characteristic = cs.readFactFromConsole(animalName, factNode.getAnswerToAnimalMap().get(answer));

        FactNode newFactNode = new FactNode(characteristic);
        AnimalNode animalNode = new AnimalNode(animalName);

        List<FactNode> factNodes = new ArrayList<>();
        factNodes.add(newFactNode);

        if (!factNode.getLabel().equals(KEY_QUESTION)) {
            answer = true;
        }

        if (factNode.getAnswerToFactsMap().get(answer) != null) {
            factNodes.addAll(factNode.getAnswerToFactsMap().get(answer));
        }
        factNode.getAnswerToFactsMap().put(answer, factNodes);

        newFactNode.getAnswerToAnimalMap().put(true, animalNode);
    }

    private void finalCondition(Boolean oldAnswer, FactNode factNode) {
        Boolean answer = cs.getAnswerForAnimal(factNode.getAnswerToAnimalMap().get(oldAnswer));
        if (answer) {
            cs.printInformationForPlayer(WIN_WORD);
        } else {
            addDataToDB(factNode, oldAnswer);
        }
    }
}
