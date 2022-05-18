import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
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

    public Integer solve2(String exp) {
        if (exp == null || exp.isEmpty() || !Character.isDigit(exp.charAt(0))) {
            return -1;
        }
        exp = exp.trim();

        String mul_regex = "(\\d+)(\\*)(\\d+)";
        String div_regex = "(\\d+)(/)(\\d+)";
        String add_regex = "(\\d+)(\\+)(\\d+)";
        String mul_op = "\\*";
        String div_op = "/";
        String add_op = "\\+";

        exp = checkRegex(exp, mul_regex, mul_op);
        exp = checkRegex(exp, div_regex, div_op);
        exp = checkRegex(exp, add_regex, add_op);

        Integer result = Integer.parseInt(exp);
        return result >= 0 ? result : -3;
    }

    private String checkRegex(String str, String regex, String operator) {
        String regex_result;
        while ((regex_result = regexCalc(str, regex)) != null) {
            String[] split = regex_result.split(operator);
            Integer temp = 0;
            temp = calc(operator, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            str = str.replace(regex_result, temp.toString());
        }
        return str;
    }

    public void check(ArrayList<String> op_list, ArrayList<String> num_list, String operator) {
        while (op_list.contains(operator)) {
            Integer temp = 0;
            int index = op_list.indexOf(operator);

            temp = calc(operator, Integer.parseInt(num_list.get(index-1)), Integer.parseInt(num_list.get(index)));

            op_list.remove(index);
            num_list.set(index-1, String.valueOf(temp));
            num_list.remove(index);

            System.out.println(num_list);
            System.out.println(op_list);
        }
    }

    // Takes in a string and a regex.
    // Matches against the regex and returns the matched string.
    public String regexCalc(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private Integer calc(String str, int a, int b) {
        return switch (str) {
            case "\\*", "*" -> a * b;
            case "\\+", "+" -> a + b;
            case "/" -> a / b;
            default -> -1;
        };
    }
}
