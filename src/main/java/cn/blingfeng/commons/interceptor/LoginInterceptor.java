package cn.blingfeng.commons.interceptor;

import cn.blingfeng.user.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
/**    放行登入入口字符串  */
    private final String PASS_LOGIN = "/login";
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getRequestURI().contains(PASS_LOGIN)) {
            return true;
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        if (user != null) {
            return true;
        }
//        如果没有登录则转到登录页面
        httpServletRequest.getRequestDispatcher(PASS_LOGIN).forward(httpServletRequest,httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
