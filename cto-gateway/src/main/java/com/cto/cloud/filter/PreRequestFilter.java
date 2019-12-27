package com.cto.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 请求进入时执行的过滤器
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-16
 */
@Component
public class PreRequestFilter extends ZuulFilter {
    /**
     * 定义Filter类型
     * pre 在请求被路由之前被调用
     * route 在路由请求时被调用
     * post 在route和error过滤器之后被调用
     * error 在处理请求出现错误是调用
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 定义Filter执行顺序  越小越靠前
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 定义Filter是否需要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行的操作
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //设置请求开始时间
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime",System.currentTimeMillis());
        return null;
    }
}
