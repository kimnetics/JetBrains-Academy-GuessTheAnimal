package animals.Data;

import animals.Utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Animal {
    public String name;
    public String indefiniteArticle;

    public Animal() {
    }

    public Animal(String name, String indefiniteArticle) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must have a value.");
        }
        this.name = name;
        this.indefiniteArticle = indefiniteArticle;
    }

    // Get statement for animal.
    @JsonIgnore
    public String getStatement(Fact fact, boolean positive) {
        String factStatement = fact.getStatement(positive);
        String statement = Utils.applyPatternReplace("animalFact", factStatement);
        statement = Utils.applyPatternReplace("definite", statement);
        return String.format(statement, name);
    }

    // Get question for animal.
    @JsonIgnore
    public String getQuestion() {
        String question = Utils.applyPatternReplace("guessAnimal", getNameWithArticle());
        return Utils.capitalize(question);
    }

    // Get name for animal with leading indefinite article.
    @JsonIgnore
    public String getNameWithArticle() {
        return (indefiniteArticle + " " + name).trim();
    }
}
