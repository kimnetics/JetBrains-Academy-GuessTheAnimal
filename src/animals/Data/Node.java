package animals.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node {
    public Animal animal;
    public Fact fact;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(Animal animal) {
        this.animal = animal;
        this.fact = null;
        this.left = null;
        this.right = null;
    }

    @JsonIgnore
    public String getDescription() {
        if (animal != null) {
            return animal.getNameWithArticle();
        } else if (fact != null) {
            return fact.getQuestion();
        } else {
            throw new IllegalStateException("Node must have an animal or question.");
        }
    }

    @JsonIgnore
    public void traverse(TreeInfo treeInfo, int currentDepth, StringBuilder animalFacts, String prefix, String childPrefix) {
        // Increment depth.
        currentDepth++;

        // Update tree information
        treeInfo.nodeCount++;

        if (animal != null) {
            treeInfo.animalName.add(animal.name);
            treeInfo.animalFacts.put(animal.name, animalFacts.toString());
            treeInfo.animalDepth.add(currentDepth);
        } else if (fact != null) {
            treeInfo.factCount++;
        } else {
            throw new IllegalStateException("Node must have an animal or question.");
        }

        if (currentDepth > treeInfo.treeHeight) {
            treeInfo.treeHeight = currentDepth;
        }

        // Build knowledge tree diagram.
        treeInfo.knowledgeTree.append(prefix);
        treeInfo.knowledgeTree.append(getDescription());
        treeInfo.knowledgeTree.append('\n');

        // Count child nodes.
        int childNodeCount = 0;
        if (right != null) {
            childNodeCount++;
        }
        if (left != null) {
            childNodeCount++;
        }

        // Traverse child nodes.
        if (right != null) {
            right.traverse(treeInfo, currentDepth, addAnimalFact(animalFacts, true), childPrefix + ((childNodeCount > 1) ? "├ " : "└ "), childPrefix + ((childNodeCount > 1) ? "│ " : "  "));
        }
        if (left != null) {
            left.traverse(treeInfo, currentDepth, addAnimalFact(animalFacts, false), childPrefix + "└ ", childPrefix + "  ");
        }
    }

    // Add animal fact if on fact.
    private StringBuilder addAnimalFact(StringBuilder animalFacts, boolean positive) {
        if (fact != null) {
            StringBuilder newAnimalFacts = new StringBuilder(animalFacts);
            newAnimalFacts.append(" - ");
            newAnimalFacts.append(fact.getStatement(positive));
            newAnimalFacts.append('\n');
            return newAnimalFacts;
        } else {
            return animalFacts;
        }
    }
}
