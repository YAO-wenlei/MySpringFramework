package com.yao.springframework.aop.Aspectj;

import com.yao.springframework.aop.PointCut;
import com.yao.springframework.aop.PointCutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author yaowenlei
 * @description
 * @date 2022年02月02日 9:37 上午
 */
public class AspectjExpressionPointCutAdvisor implements PointCutAdvisor {
    //切面
    private AspectjExpressionPointcut pointcut;
    //具体的切入方法
    private Advice advice;
    //表达式
    private String expression;

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public PointCut getPointCut() {
        if (null == pointcut) {
            return new AspectjExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
