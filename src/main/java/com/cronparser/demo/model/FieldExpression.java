package com.cronparser.demo.model;

public class FieldExpression {
    public String getExpression() {
        return expression;
    }

    public FieldExpression(String expression, ExpressionType type) {
        this.expression = expression;
        this.type = type;
    }

    public ExpressionType getType() {
        return type;
    }

    private String expression;
    private ExpressionType type;

}
