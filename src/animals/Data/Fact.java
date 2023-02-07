package animals.Data;

import animals.Utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Fact {
    public String fact;

    public Fact() {
    }

    public Fact(String fact) {
        if (fact == null || fact.isEmpty()) {
            throw new IllegalArgumentException("Fact must have a value.");
        }
        this.fact = fact;
    }

    // Get statement for fact.
    @JsonIgnore
    public String getStatement(boolean positive) {
        String statement;

        if (positive) {
            statement = fact;
        } else {
            statement = Utils.applyPatternReplace("negative", fact);
        }

        return Utils.capitalize(statement);
    }

    // Get question for fact.
    @JsonIgnore
    public String getQuestion() {
        String question = Utils.applyPatternReplace("question", fact);
        return Utils.capitalize(question);
    }
}
