package pers.zb.wechat.rpc.api.wxapi.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 微信请求处理类型
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqType {

	public String value() default "";
}
