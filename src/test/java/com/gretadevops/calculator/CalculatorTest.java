package com.gretadevops.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void testSum() {
        Calculator calculator = new Calculator();
        int result = calculator.sum(5, 6);
        assertEquals(11, result, "5 + 6 should equal 11");
    }
}
