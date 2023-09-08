package com.cronparser.demo.rules;

import com.cronparser.demo.model.FieldExpression;

/**
 * Abstract Class to getNextRule
 */
public abstract class AbstractRuleBuilder {
    private AbstractRuleBuilder nextRule;
    private FieldExpression fieldExpression;

    public AbstractRuleBuilder(AbstractRuleBuilder nextRule){
        this.nextRule=nextRule;
    }

    /**
     * Get Rule for the FieldExpression
     *
     * @param fieldExpression
     * @return
     */
    public AbstractRuleBuilder getRule(FieldExpression fieldExpression){
            if(nextRule==null){
                return null;
            }
            return nextRule.getRule(fieldExpression);
    }
    public abstract String parse();
}
