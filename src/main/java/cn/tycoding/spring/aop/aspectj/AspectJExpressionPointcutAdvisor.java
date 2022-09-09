package cn.tycoding.spring.aop.aspectj;

import cn.tycoding.spring.aop.Pointcut;
import cn.tycoding.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 定义切面，切面=切点+通知
 *
 * @author tycoding
 * @since 2022/9/9
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
        pointcut = new AspectJExpressionPointcut(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
