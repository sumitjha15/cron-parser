package com.cronparser.demo.rules;

import com.cronparser.demo.model.Constants;
import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.validator.RangeValidator;
import org.springframework.util.Assert;

public class SlashRuleBuilder extends AbstractRuleBuilder{
    private FieldExpression fieldExpression;
    public SlashRuleBuilder(AbstractRuleBuilder nextRule) {
        super(nextRule);
    }
    public AbstractRuleBuilder getRule(FieldExpression fieldExpression){
        if(fieldExpression.getExpression().contains("/")){
            this.fieldExpression = fieldExpression;
            return this;
        }
        return super.getRule(fieldExpression);
    }

    public String parse(){
        String expression = this.fieldExpression.getExpression().trim();
        ExpressionType type = this.fieldExpression.getType();
        String result = Constants.EMPTY;
        String[] expressionPart= expression.split(Constants.SLASH);
        Assert.isTrue(expressionPart.length==2,"Invalid value ["+expression+"] for field "+type);
        int start=expressionPart[0].equals(Constants.ASTERISK)?0:Integer.parseInt(expressionPart[0]);
        int incrementValue=Integer.parseInt(expressionPart[1]);
        Assert.isTrue(RangeValidator.validateRange(start,type),"Value out of range for field "+ type );
        Assert.isTrue(RangeValidator.validateRange(incrementValue,type),"Value out of range for field "+ type);
        for(;start<=RangeValidator.rangeMap.get(type).getMaxValue();start+=incrementValue){
            result += start + Constants.SPACE;
        }
        return result.trim();
    }
}
