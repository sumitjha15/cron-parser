package com.cronparser.demo.rules;

import com.cronparser.demo.model.Constants;
import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.validator.RangeValidator;
import org.springframework.util.Assert;

public class DefaultRuleBuilder extends AbstractRuleBuilder{
    private FieldExpression fieldExpression;
    public DefaultRuleBuilder(AbstractRuleBuilder nextRule) {
        super(nextRule);
    }
    public AbstractRuleBuilder getRule(FieldExpression fieldExpression){
            this.fieldExpression = fieldExpression;
            return this;
    }

    @Override
    public String parse() {
        String expression = this.fieldExpression.getExpression().trim();
        ExpressionType type = this.fieldExpression.getType();
        String result = Constants.EMPTY;
        Assert.hasLength(expression,"Invalid value for field "+type);
        int value = Integer.parseInt(expression);
        Assert.isTrue(RangeValidator.validateRange(value,type),"Value out of range for field "+type);
        result += value;
        return result;
    }
}
