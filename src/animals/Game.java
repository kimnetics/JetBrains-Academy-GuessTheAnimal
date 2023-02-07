package animals;

import animals.Data.Animal;
import animals.Data.Fact;
import animals.Data.Node;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    // Play game.
    public static void playGame(Scanner scanner, Node node) {
        boolean playAgain;
        Node rootNode = node;
        do {
            // Display welcome.
            System.out.println(Language.getMessage("game.think"));
            System.out.println(Language.getMessage("game.enter"));
            scanner.nextLine();

            while (true) {
                // Are we on a leaf node (animal)?
                if (node.animal != null) {
                    // Handle leaf node (animal).
                    handleLeafNode(scanner, node);

                    // Enter if user wants to play again.
                    playAgain = Utils.enterYesNoResponse(scanner, Language.getRandomMessage("game.again"));

                    // Exit loop.
                    break;
                } else {
                    // Ask node question.
                    boolean response = Utils.enterYesNoResponse(scanner, node.fact.getQuestion());

                    // Move to new node based on response.
                    if (response) {
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                }
            }
            node = rootNode;
        } while (playAgain);
    }

    // Handle leaf node (animal).
    private static void handleLeafNode(Scanner scanner, Node node) {
        Animal nodeAnimal = node.animal;

        // Take a guess using animal.
        boolean response = Utils.enterYesNoResponse(scanner, nodeAnimal.getQuestion());

        // Did we get it right?
        if (response) {
            // We win!
            System.out.println(Language.getMessage("game.win"));
        } else {
            // Enter new animal.
            Animal newAnimal = enterAnimal(scanner, Language.getMessage("game.giveUp"));

            // Enter distinguishing fact.
            Fact fact = enterDistinguishingFact(scanner, nodeAnimal, newAnimal);

            // Enter if question is correct for new animal and move animals below current node based on response.
            if (Utils.enterYesNoResponse(scanner, MessageFormat.format(Language.getMessage("game.isCorrect"), newAnimal.getNameWithArticle()))) {
                node.left = new Node(nodeAnimal);
                node.right = new Node(newAnimal);
                System.out.println(nodeAnimal.getStatement(fact, false));
                System.out.println(newAnimal.getStatement(fact, true));
            } else {
                node.left = new Node(newAnimal);
                node.right = new Node(nodeAnimal);
                System.out.println(newAnimal.getStatement(fact, false));
                System.out.println(nodeAnimal.getStatement(fact, true));
            }
            node.fact = fact;
            node.animal = null;

            // Print learnings.
            System.out.println(Language.getRandomMessage("animal.nice") + Language.getMessage("animal.learnedMuch"));
        }
    }

    // Enter animal.
    public static Animal enterAnimal(Scanner scanner, String prompt) {
        final Pattern animalIsCorrectPattern = Pattern.compile(Language.getPattern("animal.isCorrect"));

        Animal animal;

        // Enter animal with optional leading article.
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine().trim().toLowerCase();
            if (animalIsCorrectPattern.matcher(input).find()) {
                break;
            }
            System.out.println(Language.getMessage("animal.error"));
        }

        // Determine indefinite article and animal.
        String animalWithArticle = Utils.applyPatternReplace("animal", input);

        // Separate indefinite article and animal.
        String article;
        String animalName;
        String[] animalAndArticle = animalWithArticle.split(" ");
        if (animalAndArticle.length == 1) {
            article = "";
            animalName = animalAndArticle[0];
        } else {
            article = animalAndArticle[0];
            animalName = animalAndArticle[1];
        }

        // Build animal.
        animal = new Animal(animalName, article);

        return animal;
    }

    // Enter distinguishing fact.
    private static Fact enterDistinguishingFact(Scanner scanner, Animal nodeAnimal, Animal newAnimal) {
        Fact fact;

        String prompt = MessageFormat.format(Language.getMessage("statement.prompt"), nodeAnimal.getNameWithArticle(), newAnimal.getNameWithArticle());
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            // Validate input fact.
            fact = validateInputFact(input);
            if (fact != null) {
                break;
            }

            // Print error message.
            System.out.println(Language.getMessage("statement.error"));
        }

        return fact;
    }

    // Validate input fact.
    private static Fact validateInputFact(String input) {
        final Pattern statementIsCorrectPattern = Pattern.compile(Language.getPattern("statement.isCorrect"), Pattern.CASE_INSENSITIVE);

        Fact fact = null;

        // Is statement format correct?
        if (statementIsCorrectPattern.matcher(input).find()) {
            // Standardize fact format.
            String standardizedFact = Utils.applyPatternReplace("statement", input);
            fact = new Fact(standardizedFact);
        }

        return fact;
    }
}
