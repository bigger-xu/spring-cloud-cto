package com.cto.cloud.filter;

import com.cto.cloud.entity.TokenInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权过滤器
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-29
 */
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("Authorization Start");
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if(isNeedAuth(request)){
            TokenInfo tokenInfo = (TokenInfo) request.getAttribute("tokenInfo");
            if(tokenInfo != null && tokenInfo.isActive()){
                if(!hasPermission(tokenInfo,request)){
                    log.info("update OAuthLog,fail 403");
                    handlerError(403,"no Permission",context);
                }
            }else{
                //放过请求token的请求,否则返回401
                if(!StringUtils.startsWith(request.getRequestURI(),"/auth")){
                    log.info("update OAuthLog,fail 401");
                    handlerError(401,"no token message",context);
                }
            }
        }
        return null;
    }

    /**
     * 判断用户是否有权限访问
     * @param tokenInfo
     * @param request
     * @return
     */
    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
        //TODO 判断用户是否有权限访问
        return true;
    }

    private void handlerError(int status, String message,RequestContext context) {
        context.setResponseStatusCode(status);
        context.setResponseBody("{\"message\":\""+message+"\"}");
        context.setSendZuulResponse(false);


    }

    private boolean isNeedAuth(HttpServletRequest request) {
        //TODO 判断是否需要认证,或者判断认证哪个端
        return true;
    }
}
