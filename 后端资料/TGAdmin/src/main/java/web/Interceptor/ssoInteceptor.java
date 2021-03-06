package web.Interceptor;

/**
 * Created by tanjian on 17/02/25.
 * 拦截器，实现SSO单点登录功能
 */

import data.domain.admininfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import web.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public class SsoInteceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Cookie[] cookies = request.getCookies();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        /*String currPath = basePath.substring(0, basePath.length()-1) + request.getServletPath();*/
        String currPath = request.getServletPath();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("TGLogin".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    String[] split = value.split("_");
                    if (checkLogin(split[0], split[1])) {
                        return true;
                    }
                }
            }
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (null != username && null != password) {
            if (checkLogin(username, password)) {
                Cookie ck = new Cookie("TGLogin", username + "_" + password);
                ck.setPath("/");//设置到共有的根路径下
                ck.setMaxAge(3600);
                response.addCookie(ck);
                return true;
            }
        }
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public boolean checkLogin(String username, String password) throws NoSuchAlgorithmException {
        admininfo admin=new admininfo();
        admin.setAdminAccount(username);
        admin.setAdminPwd(password);
        return userService.Validate(admin);
    }
}  
