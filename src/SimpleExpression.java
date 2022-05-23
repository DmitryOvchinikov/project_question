import org.junit.platform.commons.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.ProgUtils.calc;

public class SimpleExpression {

    public Integer solve(String exp) {
        exp = StringUtils.replaceWhitespaceCharacters(exp, "");
        if (exp == null || exp.isEmpty()) {
            return -1;
        }

        String mul_regex = "(\\d+)(\\*)(\\d+)";
        String div_regex = "(\\d+)(/)(\\d+)";
        String add_regex = "(\\d+)(\\+)(\\d+)";
        String mul_op = "\\*";
        String div_op = "/";
        String add_op = "\\+";
        Integer result;

        if ((result = checkExpression(exp, div_regex, div_op)) == null) {
            if ((result = checkExpression(exp, mul_regex, mul_op)) == null) {
                if ((result = checkExpression(exp, add_regex, add_op)) == null) {
                    return -1;
                }
            }
        }

        return result >= 0 ? result : -3;
    }

    private Integer checkExpression(String str, String regex, String operator) {
        Integer result = null;
        String matched;
        if ((matched = isExpValid(str, regex)) != null) {
            if (matched.equals(str)) {
                String[] split = str.split(operator);
                result = calc(operator, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
        }
        return result;
    }

    public String isExpValid(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
