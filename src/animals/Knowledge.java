package animals;

import animals.Data.Node;
import animals.Data.TreeInfo;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Scanner;

public class Knowledge {
    // Print list of all animals.
    public static void printListOfAnimals(Node rootNode) {
        // Traverse knowledge tree and gather tree information.
        TreeInfo treeInfo = getKnowledgeTreeInformation(rootNode);

        // Sort animals.
        Collections.sort(treeInfo.animalName);

        // Print list of all animals.
        System.out.println(Language.getMessage("tree.list.animals"));
        for (String name : treeInfo.animalName) {
            System.out.printf(" - %s\n", name);
        }
        System.out.println();
    }

    // Search for animal.
    public static void searchForAnimal(Node rootNode, Scanner scanner) {
        // Traverse knowledge tree and gather tree information.
        TreeInfo treeInfo = getKnowledgeTreeInformation(rootNode);

        // Enter animal name.
        System.out.println(Language.getMessage("animal.prompt"));
        String animalName = scanner.nextLine().trim().toLowerCase();

        // Print animal facts if found.
        if (treeInfo.animalFacts.containsKey(animalName)) {
            System.out.println(MessageFormat.format(Language.getMessage("tree.search.facts"), animalName));
            System.out.println(treeInfo.animalFacts.get(animalName));
        } else {
            System.out.println(MessageFormat.format(Language.getMessage("tree.search.noFacts"), animalName));
        }
    }

    // Print knowledge tree stats.
    public static void printKnowledgeTreeStats(Node rootNode) {
        // Traverse knowledge tree and gather tree information.
        TreeInfo treeInfo = getKnowledgeTreeInformation(rootNode);

        // Print knowledge tree stats.
        System.out.println(Language.getMessage("tree.stats.title"));
        System.out.println();
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.root"), (rootNode.fact != null) ? rootNode.fact.getStatement(true) : ""));
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.nodes"), treeInfo.nodeCount));
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.animals"), treeInfo.animalName.size()));
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.statements"), treeInfo.factCount));
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.height"), treeInfo.treeHeight));
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.minimum"), getMinimumAnimalDepth(treeInfo)));
        System.out.println(MessageFormat.format(Language.getMessage("tree.stats.average"), getAverageAnimalDepth(treeInfo)));
        System.out.println();
    }

    // Print knowledge tree diagram.
    public static void printKnowledgeTreeDiagram(Node rootNode) {
        // Traverse knowledge tree and gather tree information.
        TreeInfo treeInfo = getKnowledgeTreeInformation(rootNode);

        // Print knowledge tree diagram.
        System.out.println(treeInfo.knowledgeTree);
    }

    // Traverse knowledge tree and gather tree information.
    private static TreeInfo getKnowledgeTreeInformation(Node rootNode) {
        TreeInfo treeInfo = new TreeInfo(rootNode);
        rootNode.traverse(treeInfo, -1, new StringBuilder(), "└ ", "  ");
        return treeInfo;
    }

    // Get minimum animal depth.
    private static int getMinimumAnimalDepth(TreeInfo treeInfo) {
        int minimumDepth = Integer.MAX_VALUE;
        for (int depth : treeInfo.animalDepth) {
            if (depth < minimumDepth) {
                minimumDepth = depth;
            }
        }
        return minimumDepth;
    }

    // Get average animal depth.
    private static double getAverageAnimalDepth(TreeInfo treeInfo) {
        double animalDepthSum = 0;
        for (int depth : treeInfo.animalDepth) {
            animalDepthSum += depth;
        }
        return animalDepthSum / treeInfo.animalDepth.size();
    }
}
