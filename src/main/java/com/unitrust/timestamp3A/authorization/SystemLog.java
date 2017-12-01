package com.unitrust.timestamp3A.authorization;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

	String module() default ""; // 模块说明,例如：系统管理-用户管理－列表页面

	String methods() default ""; // 方法说明，例如：用户管理

	String description() default ""; //
}
