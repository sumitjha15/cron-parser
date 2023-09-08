package com.cronparser.demo.parsers;

import com.cronparser.demo.CronParserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTests {
    private static final String expectedString =
            "\n=======================================\n" +
            "minute        0 5 10 15 20 25 30 35 40 45 50 55\n" +
            "hour          2\n" +
            "day of month  1 15\n" +
            "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
            "day of week   1 2 3 4 5 6 7\n" +
            "command       /USR/BIN/FIND\n" +
            "\n" +
            "=======================================";
   @Test
    public void testValidateWithValidaExpressionReturnsTrue(){
       ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("*/5 2 1,15 * * /usr/bin/find");
       Assert.isTrue(expressionEvaluator.validate(),"Failed to validate");
   }

   @Test
   public void testValidateWithInvalidExpressionThrowsIllegalArgumentException(){
       ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("2 1,15 * * /usr/bin/find");
       Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           expressionEvaluator.validate();
       });
       assertEquals("Expression is invalid !!",exception.getMessage());
   }

    @Test
    public void testValidateWithNullExpressionThrowsIllegalArgumentException(){
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expressionEvaluator.validate();
        });
        assertEquals("Expression must not be null",exception.getMessage());
    }

    @Test
    public void testValidateWithFieldWithTrailingCommaThrowsIllegalArgumentException(){
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("*/5 2 1,15, * * /usr/bin/find");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            expressionEvaluator.validate();
        });
        assertEquals("Invalid field value! Trailing commas not permitted! '1,15,'",exception.getMessage());
    }

    @Test
    public void testDescribeWithValidExpression(){
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("*/5 2 1,15 * * /usr/bin/find");
        String result="";
        if(expressionEvaluator.validate()){
            result = expressionEvaluator.describe();
        }
        assertEquals(result,expectedString);
    }

    @Test
    public void testDescribeWithInValidExpressionThrowsIllegalArgumentException(){
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("67-89 2 1,15 * * /usr/bin/find");
        if(expressionEvaluator.validate()){
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                expressionEvaluator.describe();
            });
            assertEquals("Value out of range for field MINUTE",exception.getMessage());

        }else {
            assertTrue(false,"Expression validation failed");
        }

    }
    @Test
    public void testDescribeWithInValidExpressionThrowsNumberFormatException(){
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator("* 2 1,15 c * /usr/bin/find");
        if(expressionEvaluator.validate()){
            Exception exception = assertThrows(NumberFormatException.class, () -> {
                expressionEvaluator.describe();
            });
            assertEquals("For input string: \"C\"",exception.getMessage());

        }else {
            assertTrue(false,"Expression validation failed");
        }

    }
}
