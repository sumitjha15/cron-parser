package com.cronparser.demo.parsers;

import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.rules.AbstractRuleBuilder;
import org.springframework.util.Assert;

import java.util.Arrays;

public class ExpressionEvaluator {
    private String expression;
    private String[] expressionParts;
    public ExpressionEvaluator(String expression){
        this.expression =  expression;
    }

    /**
     * Validate expression for required input
     *
     * @return
     */
    public boolean validate(){
        Assert.notNull(expression, "Expression must not be null");
        final String replaced = expression.replaceAll("\\s+", " ").trim();
        if (replaced.isEmpty()) {
            throw new IllegalArgumentException("Empty expression!");
        }
        final String[] expressionParts = replaced.toUpperCase().split(" ");
        Assert.isTrue(expressionParts.length==6,"Expression is invalid !!");
        String fieldWithTrailingCommas = Arrays.stream(expressionParts).filter(x -> x.endsWith(",")).
                findAny().orElse(null);
        if(fieldWithTrailingCommas!=null){
            throw new IllegalArgumentException(String.format("Invalid field value! Trailing commas not permitted! '%s'",
                    fieldWithTrailingCommas));
        }
        this.expressionParts = expressionParts;
        return true;
    }

    /**
     * Describe cron expression
     *
     * @return
     */
    public String describe(){
        String min = expressionParts[0];
        String hour = expressionParts[1];
        String dayOfMonth = expressionParts[2];
        String month = expressionParts[3];
        String dayOfWeek = expressionParts[4];
        String command = expressionParts[5];
        AbstractRuleBuilder ruleBuilder = RuleGenerator.getInstance();
        String minuteString = ruleBuilder.getRule(new FieldExpression(min, ExpressionType.MINUTE)).parse();
        String hourString = ruleBuilder.getRule(new FieldExpression(hour, ExpressionType.HOUR)).parse();
        String dayOfMonthString = ruleBuilder.getRule(new FieldExpression(dayOfMonth, ExpressionType.DAY_OF_MONTH)).parse();
        String monthString = ruleBuilder.getRule(new FieldExpression(month, ExpressionType.MONTH)).parse();
        String dayOfWeekString = ruleBuilder.getRule(new FieldExpression(dayOfWeek, ExpressionType.DAY_OF_WEEK)).parse();
        String result =
                "\n======================================="+
                "\nminute        "+ minuteString+
                "\nhour          "+ hourString+
                "\nday of month  "+ dayOfMonthString+
                "\nmonth         "+ monthString+
                "\nday of week   "+ dayOfWeekString+
                "\ncommand       "+ command+"\n"+
                        "\n=======================================";
        return result;
    }
}
