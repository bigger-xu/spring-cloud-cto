package com.cto.cloud.advice;

import com.cto.cloud.annotation.IgnoreResponseAdvice;
import com.cto.cloud.vo.CommonResponse;
import com.cto.cloud.vo.ResponseCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-16
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果当前方法所在的类标识了 IgnoreResponseAdvice 注解, 则不需要处理
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        // 如果当前方法标识了 IgnoreResponseAdvice 注解, 则不需要处理
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        // 对响应进行处理, 执行 beforeBodyWrite 方法
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
        // 如果 o 是 null, response 不需要设置 data
        if(o == null){
            return response;
        // 如果 o 已经是 CommonResponse 类型, 强转即可
        }else if(o instanceof CommonResponse){
            response = (CommonResponse<Object>) o;
        // 否则, 把响应对象作为 CommonResponse 的 data 部分
        }else{
            response.setData(o);
        }
        return response;
    }
}
