package com.cronparser.demo.rules;

import com.cronparser.demo.model.Constants;
import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.validator.RangeValidator;
import org.springframework.util.Assert;

public class HyphenRuleBuilder extends AbstractRuleBuilder{
    private FieldExpression fieldExpression;
    public HyphenRuleBuilder(AbstractRuleBuilder nextRule) {
        super(nextRule);
    }
    public AbstractRuleBuilder getRule(FieldExpression fieldExpression){
        if(fieldExpression.getExpression().contains("-")){
            this.fieldExpression=fieldExpression;
            return this;
        }
        return super.getRule(fieldExpression);
    }

    @Override
    public String parse() {
        String result = Constants.EMPTY;
        String expression = this.fieldExpression.getExpression().trim();
        ExpressionType type = this.fieldExpression.getType();
        String[] expressionParts = expression.split(Constants.HYPHEN);
        Assert.isTrue(expressionParts.length==2,"Invalid value for field minute");
        int start=Integer.parseInt(expressionParts[0]);
        int end=Integer.parseInt(expressionParts[1]);
        Assert.isTrue(RangeValidator.validateRange(start,type)&&RangeValidator.validateRange(start,type),
                "Value out of range for field "+ type );
        for(;start<=end;start++){
            result += start + Constants.SPACE;
        }
        return result.trim();
    }
}
