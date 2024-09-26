import java.util.*;

public class Recipe {

    List<AlchemyElement> elements;

    public Recipe() {
        elements = new ArrayList<>();
    }

    public Recipe(StringPair[] transmutations) {
        elements = new ArrayList<>();
        for (StringPair transmutation : transmutations) {
            this.addTransmutation(transmutation.s1, transmutation.s2);
        }
    }

    private void addTransmutation(String name1, String name2) {
        AlchemyElement el1 = new AlchemyElement(name1), el2 = new AlchemyElement(name2);
        if (!elements.contains(el1))
            elements.add(el1);
        if (!elements.contains(el2))
            elements.add(el2);
        elements.get(elements.indexOf(el1)).addTransmutation(elements.get(elements.indexOf(el2)));
    }

    public int tryRecipe(String material, String product) {
        AlchemyElement elMaterial = new AlchemyElement(material), elProduct = new AlchemyElement(product), elCurrent;
        if (!elements.contains(elMaterial) || !elements.contains(elProduct))
            return -1;

        for (AlchemyElement el: elements)
            el.setComplexity(elements.size());
        Stack<AlchemyElement> stack = new Stack<>();
        elCurrent = elements.get(elements.indexOf(elMaterial));
        elCurrent.setComplexity(0);
        for (AlchemyElement el: elCurrent.getPossibleTransmutations()) {
            if (elCurrent.getComplexity() + 1 < el.getComplexity())
                el.setComplexity(elCurrent.getComplexity() + 1);
            stack.add(el);
        }
        while (!stack.isEmpty()) {
            elCurrent = stack.pop();
            for (AlchemyElement el: elCurrent.getPossibleTransmutations()) {
                if (elCurrent.getComplexity() + 1 < el.getComplexity())
                    el.setComplexity(elCurrent.getComplexity() + 1);
                stack.add(el);
            }
        }
        int elProductComplexity = elements.get(elements.indexOf(elProduct)).getComplexity();
        return elProductComplexity == elements.size() ? -1 : elProductComplexity;
    }
}
