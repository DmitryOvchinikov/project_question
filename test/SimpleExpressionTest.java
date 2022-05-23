import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleExpressionTest {

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
    Stream<DynamicNode> testSimpleExpression() {
        return Stream.of(
                new TestCase(-1, null, "testNull"),
                new TestCase(-1, "", "testEmpty"),
                new TestCase(-1, "*", "testSingleOperator"),
                new TestCase(-1, "5-4", "testUnknownOperator"),
                new TestCase(-1, "123", "testSingleNumber"),
                new TestCase(12, "24/2", "testDivision"),
                new TestCase(144, "6*24", "testMultiplication"),
                new TestCase(30, "6+24", "testAddition"),
                new TestCase(2, "24/9", "testUnequalDivision"),
                new TestCase(0, "8/9", "testFractionDivision"),
                new TestCase(-3, "2147483647+1", "testOverflow"),
                new TestCase(-3, "2/0", "testZeroDivision"),
                new TestCase(-3, "0/0", "testZeroByZeroDivision"),
                new TestCase(0, "0/5", "testZeroDivides"),
                new TestCase(35, "   25     +     10   ", "testWhiteSpaces"),
                new TestCase(-1, "1+2*3", "testBiggerExpression"),
                new TestCase(-1, "5*b", "testWithWrongParam"),
                new TestCase(-1, "a*b", "testWithWrongParams")
        ).map(testCase -> DynamicTest.dynamicTest(
                testCase.name,
                () -> {
                    SimpleExpression expression = new SimpleExpression();
                    int result = expression.solve(testCase.expression);
                    assertEquals(testCase.expected, result);
                })
        );
    }
}
