package com.cronparser.demo.validator;

import com.cronparser.demo.model.ExpressionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RangeValidatorTests {
    @Test
    public void testRangeWithValidValue(){
        assertTrue(RangeValidator.validateRange(5, ExpressionType.HOUR));
    }

    @Test
    public void testRangeWithInvalidValue(){
        assertFalse(RangeValidator.validateRange(10, ExpressionType.DAY_OF_WEEK));
    }
}
