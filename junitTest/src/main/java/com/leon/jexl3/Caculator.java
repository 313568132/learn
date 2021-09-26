package com.leon.jexl3;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.BigDecimal;

public class Caculator {
    
    @Test
    public void test(){
        String s = "(duty_value_price + duty_value_price * duty_rate) / (1 - exc_rate) * exc_rate";
        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression expression = jexlEngine.createExpression(s);
        JexlContext jexlContext = new MapContext();
        jexlContext.set("duty_value_price", BigDecimal.valueOf(2));
        jexlContext.set("duty_rate",BigDecimal.valueOf(4));
        jexlContext.set("exc_rate",BigDecimal.valueOf(0.3));
        Object evaluate = expression.evaluate(jexlContext);
        System.out.println(evaluate);


        String s2 = "(#dutyValuePrice + #dutyValuePrice * #dutyRate) / (1 - #excRate) * #excRate";
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression1 = parser.parseExpression(s2);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("dutyValuePrice", BigDecimal.valueOf(2));
        context.setVariable("dutyRate",BigDecimal.valueOf(4));
        context.setVariable("excRate",BigDecimal.valueOf(0.3));
        System.out.println(expression1.getValue(context,BigDecimal.class));
    }
}
