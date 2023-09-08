package com.cronparser.demo.rules;

import com.cronparser.demo.model.Constants;
import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.validator.RangeValidator;
import org.springframework.util.Assert;

public class CommaRuleBuilder extends AbstractRuleBuilder{
    private FieldExpression fieldExpression;
    public CommaRuleBuilder(AbstractRuleBuilder nextRule) {
        super(nextRule);
    }
    public AbstractRuleBuilder getRule(FieldExpression fieldExpression){
        if(fieldExpression.getExpression().contains(",")){
            this.fieldExpression = fieldExpression;
            return this;
        }
        return super.getRule(fieldExpression);
    }

    @Override
    public String parse() {
        String result = Constants.EMPTY;
        String expression = this.fieldExpression.getExpression().trim();
        ExpressionType type = this.fieldExpression.getType();
        String[] expressionParts= expression.split(Constants.COMMA);
        Assert.notEmpty(expressionParts,"Invalid value for field "+type);
        for(String expressionPart:expressionParts){
            Assert.isTrue(RangeValidator.validateRange(Integer.parseInt(expressionPart),type),
                    "Value out of range for field "+ type );
            result += expressionPart + Constants.SPACE;
        }
        return result.trim();
    }
}
