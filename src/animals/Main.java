package animals;

import animals.Data.Animal;
import animals.Data.Node;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get language code.
        String languageCode = Language.getLanguageCode();

        // Print greeting.
        Utils.printGreeting(LocalDateTime.now());

        // Get storage type from command line.
        String storageType = Storage.getStorageType(args);

        // Read animal data.
        Node rootNode = Storage.readAnimalData(storageType, languageCode);

        // Was animal data not found?
        if (rootNode == null) {
            // Enter favorite animal.
            System.out.println();
            System.out.println(Language.getMessage("animal.wantLearn"));
            Animal rootAnimal = Game.enterAnimal(scanner, Language.getMessage("animal.askFavorite"));

            // Add root node.
            rootNode = new Node(rootAnimal);
        }

        System.out.println();
        System.out.println(Language.getMessage("welcome"));
        System.out.println();

        // Do main loop.
        doMainLoop(scanner, rootNode);

        // Write animal data.
        Storage.writeAnimalData(storageType, rootNode, languageCode);

        // Print goodbye.
        System.out.println();
        System.out.println(Language.getRandomMessage("farewell"));

        scanner.close();
    }

    // Do main loop.
    private static void doMainLoop(Scanner scanner, Node rootNode) {
        boolean exitLoop = false;
        do {
            // Enter option.
            int option = enterOption(scanner);

            // Handle option.
            switch (option) {
                // Play game.
                case 1 -> Game.playGame(scanner, rootNode);
                // Print list of all animals.
                case 2 -> Knowledge.printListOfAnimals(rootNode);
                // Search for animal.
                case 3 -> Knowledge.searchForAnimal(rootNode, scanner);
                // Print knowledge tree stats.
                case 4 -> Knowledge.printKnowledgeTreeStats(rootNode);
                // Print knowledge tree diagram.
                case 5 -> Knowledge.printKnowledgeTreeDiagram(rootNode);
                // Exit.
                case 0 -> exitLoop = true;
            }
        } while (!exitLoop);
    }

    // Enter option.
    private static int enterOption(Scanner scanner) {
        System.out.println(Language.getMessage("menu.property.title"));
        System.out.println();
        System.out.printf("1. %s\n", Language.getMessage("menu.entry.play"));
        System.out.printf("2. %s\n", Language.getMessage("menu.entry.list"));
        System.out.printf("3. %s\n", Language.getMessage("menu.entry.search"));
        System.out.printf("4. %s\n", Language.getMessage("menu.entry.statistics"));
        System.out.printf("5. %s\n", Language.getMessage("menu.entry.print"));
        System.out.printf("0. %s\n", Language.getMessage("menu.property.exit"));

        // Enter option.
        Integer option = null;
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                int inputInt = Integer.parseInt(input);
                if ((inputInt >= 0) && (inputInt <= 5)) {
                    option = inputInt;
                }
            }
            if (option != null) {
                break;
            }
            System.out.println(MessageFormat.format(Language.getMessage("menu.property.error"), 5));
        }

        return option;
    }
}
