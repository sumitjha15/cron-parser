package com.cronparser.demo.parsers;

import com.cronparser.demo.model.ExpressionType;
import com.cronparser.demo.model.FieldExpression;
import com.cronparser.demo.rules.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleGeneratorTests {
    @Test
    public void testRuleBuilderForSlashRule(){
        AbstractRuleBuilder ruleBuilder = RuleGenerator.getInstance();
        AbstractRuleBuilder slashRuleBuilder = ruleBuilder.
                getRule(new FieldExpression("1/5", ExpressionType.MINUTE));
        Assert.isTrue(slashRuleBuilder instanceof SlashRuleBuilder,"Failed to fetch correct rule");
        String result = slashRuleBuilder.parse();
        assertEquals("1 6 11 16 21 26 31 36 41 46 51 56",result);
    }

    @Test
    public void testRuleBuilderForHyphenRule(){
        AbstractRuleBuilder ruleBuilder = RuleGenerator.getInstance();
        AbstractRuleBuilder hyphenRuleBuilder = ruleBuilder.
                getRule(new FieldExpression("11-15", ExpressionType.HOUR));
        Assert.isTrue(hyphenRuleBuilder instanceof HyphenRuleBuilder,"Failed to fetch correct rule");
        String result = hyphenRuleBuilder.parse();
        assertEquals("11 12 13 14 15",result);
    }

    @Test
    public void testRuleBuilderForCommaRule(){
        AbstractRuleBuilder ruleBuilder = RuleGenerator.getInstance();
        AbstractRuleBuilder commaRuleBuilder = ruleBuilder.
                getRule(new FieldExpression("11,12,15", ExpressionType.DAY_OF_MONTH));
        Assert.isTrue(commaRuleBuilder instanceof CommaRuleBuilder,"Failed to fetch correct rule");
        String result = commaRuleBuilder.parse();
        assertEquals("11 12 15",result);
    }

    @Test
    public void testRuleBuilderForAsteriskRule(){
        AbstractRuleBuilder ruleBuilder = RuleGenerator.getInstance();
        AbstractRuleBuilder asteriskRuleBuilder = ruleBuilder.
                getRule(new FieldExpression("*", ExpressionType.DAY_OF_WEEK));
        Assert.isTrue(asteriskRuleBuilder instanceof AsteriskRuleBuilder,"Failed to fetch correct rule");
        String result = asteriskRuleBuilder.parse();
        assertEquals("1 2 3 4 5 6 7",result);
    }
}
