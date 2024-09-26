import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Alchemy {

    private static InputData read() {
        InputData data = new InputData();
        try (BufferedReader input = new BufferedReader(new FileReader("input.txt"))) {
            String[] divededLine;
            int recipeLength = Integer.parseInt(input.readLine());
            data.recipe = new StringPair[recipeLength];
            for (int i = 0; i < recipeLength; i++) {
                divededLine = input.readLine().split(" -> ");
                data.recipe[i] = new StringPair(divededLine[0], divededLine[1]);
            }
            data.material = input.readLine();
            data.product = input.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        InputData data = read();
        Recipe recipe = new Recipe(data.recipe);
        int result = recipe.tryRecipe(data.material, data.product);
        System.out.println(result);
    }

    static class InputData {
        StringPair[] recipe;
        String material, product;
    }
}