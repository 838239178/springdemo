package cn.shijh.controller;

import cn.shijh.domain.User;
import cn.shijh.service.UserService;
import cn.shijh.utils.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.NoSuchElementException;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    @ResponseBody
    private Map<String, Object> login(String userName, String password, HttpSession session) {
        User user = service.verify(userName,password);
        session.setAttribute("user", user);
        return MapBuilder.map()
                .add("success", true)
                .add("data", user)
                .build();
    }

    @GetMapping("/logout")
    @ResponseBody
    private Map<String, Object> logout(HttpSession session) {
        if(session.getAttribute("user") == null)
            throw new NoSuchElementException("User not login");
        session.removeAttribute("user");
        return MapBuilder.map()
                .add("success", true)
                .build();
    }

    @PostMapping("/register")
    @ResponseBody
    private Map<String, Object> register(User user, Long[] roleIds) {
        User data = service.save(user, roleIds);
        return MapBuilder.map()
                .add("success", true)
                .add("data", data)
                .build();
    }

    @RequestMapping("/cancellation")
    @ResponseBody
    private Map<String, Object> cancellation(User user){
        User data = service.delete(user.getUserName());
        return MapBuilder.map()
                .add("success", true)
                .add("data", data)
                .build();
    }

    @ExceptionHandler({ NoSuchElementException.class, IllegalArgumentException.class})
    @ResponseBody
    private Map<String,Object> exceptionHandler(Exception ex){
        return MapBuilder.map()
                .add("success", false)
                .add("reason", ex.getMessage())
                .build();
    }
}
