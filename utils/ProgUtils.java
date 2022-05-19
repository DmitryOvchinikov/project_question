public class ProgUtils {

    public static Integer calc(String str, int a, int b) throws ArithmeticException {
        return switch (str) {
            case "\\*", "*" -> a * b;
            case "\\+", "+" -> a + b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                    yield a / b;
            }
            default -> -1;
        };
    }
}
