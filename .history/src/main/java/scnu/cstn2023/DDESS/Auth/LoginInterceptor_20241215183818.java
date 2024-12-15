package scnu.cstn2023.DDESS.Auth;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component // 在容器中进行注册
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String token = request.getHeader("token");
        if(JwtTokenProvider.validateToken(token)){
            return true;
        }
        // 失败我们跳转回登录页面
        request.setAttribute("msg","登录出错");
        request.getRemoteHost();
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }

}
