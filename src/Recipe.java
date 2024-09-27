import java.util.*;

public class Recipe {

    HashMap<String, AlchemyElement> elements;

    public Recipe() {
        elements = new HashMap<>();
    }

    public Recipe(StringPair[] transmutations) {
        elements = new HashMap<>();
        for (StringPair transmutation : transmutations) {
            this.addTransmutation(transmutation.s1, transmutation.s2);
        }
    }

    private void addTransmutation(String name1, String name2) {
        elements.putIfAbsent(name1, new AlchemyElement(name1));
        elements.putIfAbsent(name2, new AlchemyElement(name2));
        elements.get(name1).addTransmutation(elements.get(name2));
    }

    public int tryRecipe(String material, String product) {
        AlchemyElement elCurrent;
        if (!elements.containsKey(material) || !elements.containsKey(product))
            return -1;

        for (AlchemyElement el: elements.values())
            el.setComplexity(elements.size());
        Stack<AlchemyElement> stack = new Stack<>();
        elCurrent = elements.get(material);
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
        int elProductComplexity = elements.get(product).getComplexity();
        return elProductComplexity == elements.size() ? -1 : elProductComplexity;
    }

    public int tryRecipeBFS(String material, String product) {
        AlchemyElement elCurrent;
        if (!elements.containsKey(material) || !elements.containsKey(product))
            return -1;

        for (AlchemyElement el: elements.values())
            el.setComplexity(elements.size());
        Queue<AlchemyElement> queue = new LinkedList<>();
        elCurrent = elements.get(material);
        elCurrent.setComplexity(0);
        elCurrent.setHasTransmutated();
        for (AlchemyElement el: elCurrent.getPossibleTransmutations()) {
            el.setComplexity(elCurrent.getComplexity() + 1);
            el.setHasTransmutated();
            queue.add(el);
        }
        while (!queue.isEmpty()) {
            elCurrent = queue.poll();
            for (AlchemyElement el: elCurrent.getPossibleTransmutations()) {
                if (!el.hasTransmutated()) {
                    el.setComplexity(elCurrent.getComplexity() + 1);
                    el.setHasTransmutated();
                    queue.add(el);
                }
            }
        }
        return elements.get(product).getComplexity();
    }

    public int tryRecipeDijkstra(String material, String product) {
        AlchemyElement elCurrent;
        if (!elements.containsKey(material) || !elements.containsKey(product))
            return -1;

        for (AlchemyElement el: elements.values())
            el.setComplexity(elements.size());
        Queue<AlchemyElement> queue = new PriorityQueue<>((el1, el2) -> {
            if (el1.getComplexity() == el2.getComplexity())
                return 0;
            else return el1.getComplexity() < el2.getComplexity() ? -1 : 1;
        });
        elCurrent = elements.get(material);
        elCurrent.setComplexity(0);
        elCurrent.setHasTransmutated();
        for (AlchemyElement el: elCurrent.getPossibleTransmutations()) {
            el.setComplexity(elCurrent.getComplexity() + 1);
            el.setHasTransmutated();
            queue.add(el);
        }
        while (!queue.isEmpty()) {
            elCurrent = queue.poll();
            for (AlchemyElement el: elCurrent.getPossibleTransmutations()) {
                if (!el.hasTransmutated()) {
                    el.setComplexity(elCurrent.getComplexity() + 1);
                    el.setHasTransmutated();
                    queue.add(el);
                }
            }
        }
        return elements.get(product).getComplexity();
    }
}


