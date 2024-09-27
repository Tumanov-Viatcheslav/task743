import java.util.*;

public class AlchemyElement {
    String name;
    private int complexity;
    private boolean transmutated;
    private HashMap<Integer, AlchemyElement> possibleTransmutations;

    public AlchemyElement() {
    }

    public AlchemyElement(String name) {
        this.name = name;
        possibleTransmutations = new HashMap<>();
    }

    public void addTransmutation(AlchemyElement el) {
        possibleTransmutations.put(possibleTransmutations.size(), el);
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public Collection<AlchemyElement> getPossibleTransmutations() {
        return possibleTransmutations.values();
    }

    public boolean hasTransmutated() {
        return transmutated;
    }

    public void setHasTransmutated() {
        this.transmutated = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlchemyElement that)) return false;
        return Objects.equals(name, that.name);
    }
}
