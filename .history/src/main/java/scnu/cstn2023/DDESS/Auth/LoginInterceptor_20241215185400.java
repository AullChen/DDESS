package scnu.cstn2023.DDESS.Auth;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // 在容器中进行注册
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public LoginInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
                System.out.println("OPTIONS请求，放行");
                return true;
            }
            String token = request.getHeader("token");
            if (jwtTokenProvider.validateToken(token)) {
                return true;
            }
            // 失败我们跳转回登录页面
            request.setAttribute("msg", "登录出错");
            request.getRemoteHost();
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        } catch (Exception e) {
            // 记录异常信息
            e.printStackTrace();
            // 设置响应状态码为500
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // 返回友好的错误信息
            response.getWriter().write("内部服务器错误，请联系管理员。");
            return false;
        }
    }
}