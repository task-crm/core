package ru.sop.core.impl.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;


public class CoreAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TOO дописать логику авторизации
        ThreadLocalStorage.setUserId(UUID.fromString("cb73828b-3b85-4ae8-a875-c67d1bb7ac06"));
        ThreadLocalStorage.setTenantId(UUID.fromString("cb73828b-3b85-4ae8-a875-c67d1bb7ac06"));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalStorage.removeAll();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
