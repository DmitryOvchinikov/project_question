import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.ProgUtils.oldCalc;

public class OldExpression {
    public Integer solve(String exp) {
        if (exp == null || exp.isEmpty() || !Character.isDigit(exp.charAt(0))) {
            return -1;
        }
        exp = StringUtils.replaceWhitespaceCharacters(exp, "");

        String mul_regex = "(\\d+)(\\*)(\\d+)";
        String div_regex = "(\\d+)(/)(\\d+)";
        String add_regex = "(\\d+)(\\+)(\\d+)";
        String mul_op = "\\*";
        String div_op = "/";
        String add_op = "\\+";

        try {
            exp = checkRegex(exp, mul_regex, mul_op);
            exp = checkRegex(exp, div_regex, div_op);
            exp = checkRegex(exp, add_regex, add_op);
        } catch (ArithmeticException e) {
            System.out.println(e);
            return -3;
        }

        Integer result = Integer.parseInt(exp);
        return result >= 0 ? result : -3;
    }

    private String checkRegex(String str, String regex, String operator) {
        String regex_result;
        while ((regex_result = regexCalc(str, regex)) != null) {
            String[] split = regex_result.split(operator);
            Integer temp = oldCalc(operator, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            str = str.replace(regex_result, temp.toString());
        }
        return str;
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

}
