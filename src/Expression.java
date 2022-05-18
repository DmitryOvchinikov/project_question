import java.util.ArrayList;
import java.util.Arrays;

public class Expression {
    public Integer solve(String eq) {
        if (eq == null || eq.isEmpty()) {
            return -1;
        }
        eq = eq.trim();

        String[] numbers = eq.split("\\D+");
        String[] operators = eq.split("\\d+");
        ArrayList<String> numbers_list = new ArrayList<>(Arrays.asList(numbers));
        ArrayList<String> operators_list = new ArrayList<>(Arrays.asList(operators));

        if (numbers_list.isEmpty()) {
            return -2;
        }

        System.out.println(numbers_list);
        System.out.println(operators_list);

        Integer temp = 0;

        // Can most likely be changed into a function that gets +*/ as a parameter for clarity and removal of code duplication
        while (operators_list.contains("*")) {
            int index = operators_list.indexOf("*");
            temp = (Integer.parseInt(numbers_list.get(index)) * Integer.parseInt(numbers_list.get(index-1)));
            operators_list.remove(index);
            numbers_list.set(index-1, String.valueOf(temp));
            numbers_list.remove(index);

            System.out.println(numbers_list);
            System.out.println(operators_list);
        }

        while (operators_list.contains("/")) {
            int index = operators_list.indexOf("/");
            temp = (Integer.parseInt(numbers_list.get(index-1)) / Integer.parseInt(numbers_list.get(index)));
            operators_list.remove(index);
            numbers_list.set(index-1, String.valueOf(temp));
            numbers_list.remove(index);

            System.out.println(numbers_list);
            System.out.println(operators_list);
        }

        while (operators_list.contains("+")) {
            int index = operators_list.indexOf("+");
            temp = (Integer.parseInt(numbers_list.get(index)) + Integer.parseInt(numbers_list.get(index-1)));
            operators_list.remove(index);
            numbers_list.set(index-1, String.valueOf(temp));
            numbers_list.remove(index);

            System.out.println(numbers_list);
            System.out.println(operators_list);
        }

        // Overflow values will return -3.
        Integer result = Integer.parseInt(numbers_list.get(0));
        return result > 0 ? result : -3;
    }
}
