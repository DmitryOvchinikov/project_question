import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    static class TestCase {
        private final int expected;
        private final String expression;
        private final String name;

        private TestCase(int i, String s, String name) {
            this.name = name;
            this.expected = i;
            this.expression = s;
        }
    }

    @TestFactory
    Stream<DynamicNode> testExpression() {
        return Stream.of(
                new TestCase(-1, null, "testNull"),
                new TestCase(-1, "", "testEmpty"),
                new TestCase(-1, "*", "testSingleOperator"),
                new TestCase(123, "123", "testSingleNumber"),
                new TestCase(26, "24+5*2/5*1", "testOrderOfOperations"),
                new TestCase(1, "24/6/2/2", "testDivision"),
                new TestCase(576, "2*2*6*24", "testMultiplication"),
                new TestCase(34, "2+2+6+24", "testAddition"),
                new TestCase(2, "24/9", "testUnequalDivision"),
                new TestCase(0, "8/9", "testFractionDivision"),
                new TestCase(-3, "2147483647+1", "testOverflow"),
                new TestCase(-2, "50+2/0", "testZeroDivision"),
                new TestCase(-2, "25+0/0", "testZeroByZeroDivision"),
                new TestCase(0, "0/5", "testZeroDivides"),
                new TestCase(45, "25     +     10   * 2", "testWhiteSpaces"),
                new TestCase(16, "2*4+2*4", "testSameEquation")
        ).map(testCase -> DynamicTest.dynamicTest(
                testCase.name,
                () -> {
                    Expression expression = new Expression();
                    int result = expression.solve(testCase.expression);
                    assertEquals(testCase.expected, result);
                })
        );
    }
}