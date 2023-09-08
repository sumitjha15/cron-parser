package com.cronparser.demo.parsers;

import com.cronparser.demo.rules.*;

public class RuleGenerator {
    private static AbstractRuleBuilder ruleBuilder;
    private static RuleGenerator ruleGenerator = null;
    private RuleGenerator(){
        this.ruleBuilder = new SlashRuleBuilder(new HyphenRuleBuilder(new CommaRuleBuilder(new AsteriskRuleBuilder(new DefaultRuleBuilder(null)))));
    }
    public static AbstractRuleBuilder getInstance(){
        if(ruleGenerator == null){
            ruleGenerator = new RuleGenerator();
        }
        return ruleBuilder;
    }
}