import java.util.ArrayList;
import java.util.Arrays;

public class OldExpression {
    public Integer solve(String exp) {
        if (exp == null || exp.isEmpty()) {
            return -1;
        }
        exp = exp.trim();

        String[] numbers = exp.split("\\D+");
        String[] operators = exp.split("\\d+");
        ArrayList<String> numbers_list = new ArrayList<>(Arrays.asList(numbers));
        ArrayList<String> operators_list = new ArrayList<>(Arrays.asList(operators));

        if (numbers_list.isEmpty()) {
            return -2;
        }

        System.out.println(numbers_list);
        System.out.println(operators_list);

        check(operators_list, numbers_list, "*");
        check(operators_list, numbers_list, "/");
        check(operators_list, numbers_list, "+");

        // Overflow values will return -3.
        Integer result = Integer.parseInt(numbers_list.get(0));
        return result >= 0 ? result : -3;
    }

    public void check(ArrayList<String> op_list, ArrayList<String> num_list, String operator) {
        while (op_list.contains(operator)) {
            Integer temp = 0;
            int index = op_list.indexOf(operator);

            temp = ProgUtils.calc(operator, Integer.parseInt(num_list.get(index-1)), Integer.parseInt(num_list.get(index)));

            op_list.remove(index);
            num_list.set(index-1, String.valueOf(temp));
            num_list.remove(index);

            System.out.println(num_list);
            System.out.println(op_list);
        }
    }
}
