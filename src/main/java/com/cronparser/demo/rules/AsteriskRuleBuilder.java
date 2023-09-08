package com.cronparser.demo.rules;

import com.cronparser.demo.model.Constants;
import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.validator.RangeValidator;

public class AsteriskRuleBuilder extends AbstractRuleBuilder{
    public AsteriskRuleBuilder(AbstractRuleBuilder nextRule) {
        super(nextRule);
    }
    private FieldExpression fieldExpression;

    public AbstractRuleBuilder getRule(FieldExpression fieldExpression){
        if(fieldExpression.getExpression().contains("*")){
            this.fieldExpression = fieldExpression;
            return this;
        }
        return super.getRule(fieldExpression);
    }

    @Override
    public String parse() {
        String result = Constants.EMPTY;
        String expression = this.fieldExpression.getExpression();
        ExpressionType type = this.fieldExpression.getType();
        for(int start = RangeValidator.rangeMap.get(type).getMinValue(); start<=RangeValidator.rangeMap.get(type).getMaxValue(); start++){
            result += start + Constants.SPACE;
        }
        return result.trim();
    }
}
