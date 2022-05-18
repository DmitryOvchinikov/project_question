import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    Expression expression;

    @BeforeEach
    void setExpression() {
        expression = new Expression();
    }

    @Test
    @DisplayName("testNull")
    void testNull() {
        assertEquals(-1, expression.solve(null));
    }

    @Test
    @DisplayName("testEmpty")
    void testEmpty() {
        assertEquals(-1, expression.solve(""));
    }

    @Test
    @DisplayName("testSingleNumber")
    void testSingleNumber() {
        assertEquals(123, expression.solve("123"));
    }
    @Test
    @DisplayName("testSingleOperator")
    void testSingleOperator() {
        assertEquals(-2, expression.solve("*"));
    }

    @Test
    @DisplayName("testOrderOfOperations")
    void testOrderOfOperations() {
        assertEquals(26, expression.solve("24+5*2/5*1"));
    }

    @Test
    @DisplayName("testDivision")
    void testDivision() {
        assertEquals(1, expression.solve("24/6/2/2"));
    }

    @Test
    @DisplayName("testMultiplication")
    void testMultiplication() {
        assertEquals(576, expression.solve("2*2*6*24"));
    }

    @Test
    @DisplayName("testAddition")
    void testAddition() {
        assertEquals(34, expression.solve("2+2+6+24"));
    }

    @Test
    @DisplayName("testUnequalDivision")
    void testUnequalDivision() {
        assertEquals(2, expression.solve("24/9"));
    }

    @Test
    @DisplayName("testOverflow")
    void testOverflow() {
        assertEquals(-3, expression.solve("2147483647+1"));
    }

}
