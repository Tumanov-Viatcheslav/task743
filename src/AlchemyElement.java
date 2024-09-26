import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlchemyElement {
    String name;
    private int complexity;
    private List<AlchemyElement> possibleTransmutations;

    public AlchemyElement() {
    }

    public AlchemyElement(String name) {
        this.name = name;
        possibleTransmutations = new ArrayList<>();
    }

    public void addTransmutation(AlchemyElement el) {
        possibleTransmutations.add(el);
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public List<AlchemyElement> getPossibleTransmutations() {
        return possibleTransmutations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlchemyElement that)) return false;
        return Objects.equals(name, that.name);
    }
}
