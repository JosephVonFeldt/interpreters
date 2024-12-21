package com.craftinginterpreters.lox;

public class AstReversePolishPrinting implements Expr.Visitor<String> {

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Grouping(new Expr.Binary(new Expr.Literal(1),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Expr.Literal(2)))
                ,
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Binary(new Expr.Literal(4),
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(3)))
                );

        System.out.println(new AstReversePolishPrinting().print(expression));
    }
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return reversePolish(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return expr.expression.accept(this);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return reversePolish(expr.operator.lexeme, expr.right);
    }

    private String reversePolish(String operator, Expr left, Expr right) {
        StringBuilder builder = new StringBuilder();

        builder.append(left.accept(this)).append(" ");
        builder.append(right.accept(this)).append(" ");
        builder.append(operator).append("");

        return builder.toString();
    }

    // TODO: I'm not sure how to handle unary operators, So I guess I'm doing this
    private String reversePolish(String operator, Expr expr) {
        StringBuilder builder = new StringBuilder();
        builder.append("( ");
        builder.append(operator);
        builder.append("(");
        builder.append(expr.accept(this)).append(" ");
        builder.append(")");
        builder.append(")");
        return builder.toString();
    }

}