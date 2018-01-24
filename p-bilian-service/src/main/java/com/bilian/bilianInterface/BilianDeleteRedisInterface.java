package com.bilian.bilianInterface;

import com.bilian.enums.BilianRedisEnums;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p/>
 * <p><b>introduction:shan</b>
 * </p>
 * <p><b>packageName:</b>com.chinaredstar.bilianInterface</p>
 * <p><b>projectName:</b>p-bilian-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/1/17</p>
 *
 * @author xuxiaoming
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@Documented
public @interface BilianDeleteRedisInterface {

    //自己要加的主键的id
    @AliasFor("value")
    String value() default "";



    @AliasFor("bilianRedisEnums")
    BilianRedisEnums bilianRedisEnums() default BilianRedisEnums.BILIAN_DEFULT;


}
