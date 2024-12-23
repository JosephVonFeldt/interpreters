package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    final Environment outer;
    private final Map<String, Object> values = new HashMap<>();
    Environment(){
        this.outer = null;
    }
    Environment(Environment outer){
        this.outer = outer;
    }
    void define(String name, Object value) {
        values.put(name, value);
    }
    Object get(Token name){
        if (values.containsKey(name.lexeme)){
            return values.get(name.lexeme);
        }
        if (this.outer != null ){
            return this.outer.get(name);
        }
        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");

    }
    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }
        if (this.outer != null ){
            this.outer.assign(name, value);
            return;
        }
        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");
    }
}
