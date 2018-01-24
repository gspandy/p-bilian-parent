package com.bilian.dubbo.aop;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.bilian.bilianInterface.BilianDeleteRedisInterface;
import com.bilian.bilianInterface.BilianInsertRedisInterface;
import com.bilian.enums.BilianRedisEnums;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * <p/>
 * <p><b>introduction:</b>
 * </p>
 * <p><b>packageName:</b>com.chinaredstar.dubbo.aop</p>
 * <p><b>projectName:</b>p-longyi-contract-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/1/17</p>
 *
 * @author xuxiaoming
 */
@Component
@Aspect
@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class BilianRedisAspect {


    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private static final String KONG = "";//初始化
    private Logger logger = LoggerFactory.getLogger(BilianRedisAspect.class);


    @Autowired
    private RedisTemplate redisTemplateString;

    //定义一个切点 新增查询
    @Pointcut("@annotation(com.bilian.bilianInterface.BilianInsertRedisInterface)")
    public void setCacheRedis() {
    }

    //定义一个切点 删除切点
    @Pointcut("@annotation(com.bilian.bilianInterface.BilianDeleteRedisInterface)")
    public void deleteCacheRedis() {
    }


    /**
     * aop实现自定缓存注解
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "setCacheRedis()")
    public Object setCache(ProceedingJoinPoint joinPoint) {

        Object result = null;

        Method method = getMethod(joinPoint);//自定义注解类
        Object[] args = joinPoint.getArgs();
        //拿到切面注解

        BilianInsertRedisInterface redisInterface = method.getAnnotation(BilianInsertRedisInterface.class);

        BilianRedisEnums bilianRedisEnums = redisInterface.bilianRedisEnums();
        try {
            if (bilianRedisEnums == null) {
                //不取缓存直接从拿方法中的返回值
                // 调用数据库查询方法
                result = joinPoint.proceed(args);
                return result;
            }
            String key = bilianRedisEnums.getKey(parseCacheKey(method,args,redisInterface.value()));
            if(StringUtils.isEmpty(key)){
                return joinPoint.proceed(args);
            }
            //从缓存中拿取数据
            BoundValueOperations<String, String> boundValueOperations = redisTemplateString.boundValueOps(key);
            String value= boundValueOperations.get();
            if (StringUtils.isEmpty(value)) {
                result = joinPoint.proceed(args);
                //将结果放入
                boundValueOperations.set(JSON.toJSONString(result));
                if(bilianRedisEnums.getTimeUnit()!=null){
                    boundValueOperations.expire(bilianRedisEnums.getTimeout(), bilianRedisEnums.getTimeUnit());
                }
                return result;
            }
            //缓存命中
            //获取方法的返回类型,让缓存可以返回正确的类型
            Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
            result = JSON.parseObject(value,returnType);
            logger.info("缓存命中:"+JSON.toJSONString(bilianRedisEnums));
            if(result==null){
                return joinPoint.proceed(args);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }


    /**
     * 使用SpeL解析缓存key
     * @param method
     * @param args
     * @param expressionString
     * @return
     */
    private String parseCacheKey(Method method, Object[] args, String expressionString) {
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        if (parameterNames != null && parameterNames.length > 0
                && args != null && args.length > 0
                && args.length == parameterNames.length ) {
            for (int i = 0, length = parameterNames.length; i < length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expressionString);
        return String.valueOf(expression.getValue(context));
    }



    /**
     *      * 在方法调用前清除缓存，然后调用业务方法
     *      * @param joinPoint
     *      * @return
     *      * @throws Throwable
     *
     */
    @Around("deleteCacheRedis()")
    public Object evictCache(ProceedingJoinPoint joinPoint) throws Throwable {
        // 得到被代理的方法
        Method method = getMethod(joinPoint);//自定义注解类
        Object[] args = joinPoint.getArgs();
        // 得到被代理的方法上的注解
        BilianDeleteRedisInterface bilianDeleteRedisInterface = method.getAnnotation(BilianDeleteRedisInterface.class);
        BilianRedisEnums bilianRedisEnums = bilianDeleteRedisInterface.bilianRedisEnums();
        if(bilianRedisEnums!=null){
            String key =bilianRedisEnums.getKey(parseCacheKey(method,args,bilianDeleteRedisInterface.value()));
            if(!StringUtils.isEmpty(key)){
                BoundValueOperations<String, String> boundValueOperations = redisTemplateString.boundValueOps(key);
                boundValueOperations.set(KONG);
                boundValueOperations.expire(1L, TimeUnit.SECONDS);
            }
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }


    public Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            logger.error("annotation no sucheMehtod", e);
        } catch (SecurityException e) {
            logger.error("annotation SecurityException", e);
        }
        return method;

    }

}
