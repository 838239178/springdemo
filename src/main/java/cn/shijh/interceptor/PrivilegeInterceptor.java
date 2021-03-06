package cn.shijh.interceptor;

import cn.shijh.domain.User;
import cn.shijh.utils.MapBuilder;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            PrintWriter writer = response.getWriter();
            String error = MapBuilder.map()
                    .add("error", "No privilege, please login in")
                    .json();
            writer.write(error);
            writer.close();
            return false;
        }

        return true;
    }
}
