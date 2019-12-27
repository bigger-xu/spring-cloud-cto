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
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-27
 */
@Slf4j
@Component
public class OAuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("OAuth Start");
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if(StringUtils.startsWith(request.getRequestURI(),"/auth")){
            return null;
        }
        String authHeader = request.getHeader("Authorization");
        if(StringUtils.isBlank(authHeader)){
            return null;
        }
        if(!StringUtils.startsWithIgnoreCase("authHeader","bearer ")){
            return null;
        }
        try{
            TokenInfo tokenInfo = getTokenInfo(authHeader);
            request.setAttribute("tokenInfo",tokenInfo);

        }catch (Exception e){
            log.error("Get Token Info Fail.",e);
        }

        return null;
    }

    private TokenInfo getTokenInfo(String authHeader) {
        String token = StringUtils.substringAfter(authHeader,"bearer ");
        return null;
    }
}
