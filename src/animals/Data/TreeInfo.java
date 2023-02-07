package animals.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeInfo {
    public final Node rootNode;
    public int nodeCount;
    public ArrayList<String> animalName;
    public HashMap<String, String> animalFacts;
    public ArrayList<Integer> animalDepth;
    public int factCount;
    public int treeHeight;
    public StringBuilder knowledgeTree;

    public TreeInfo(Node rootNode) {
        this.rootNode = rootNode;
        this.nodeCount = 0;
        this.animalName = new ArrayList<>();
        this.animalFacts = new HashMap<>();
        this.animalDepth = new ArrayList<>();
        this.factCount = 0;
        this.treeHeight = 0;
        this.knowledgeTree = new StringBuilder();
    }
}
