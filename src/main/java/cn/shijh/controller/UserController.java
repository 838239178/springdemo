package cn.shijh.controller;

import cn.shijh.domain.User;
import cn.shijh.service.UserService;
import cn.shijh.utils.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.NoSuchElementException;

@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public Map<String, Object> login(String userName, String password, HttpSession session) {
        User user = service.verify(userName, password);
        session.setAttribute("user", user);
        return MapBuilder.map()
                .add("success", true)
                .add("data", user)
                .build();
    }

    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        if (session.getAttribute("user") == null)
            throw new NoSuchElementException("User not login");
        session.removeAttribute("user");
        return MapBuilder.map()
                .add("success", true)
                .build();
    }

    @PostMapping("/register")
    public Map<String, Object> register(User user, Long[] roleIds) {
        User data = service.save(user, roleIds);
        return MapBuilder.map()
                .add("success", true)
                .add("data", data)
                .build();
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(User user) {
        User data = service.delete(user);
        return MapBuilder.map()
                .add("success", true)
                .add("data", data)
                .build();
    }

    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class})
    private Map<String, Object> exceptionHandler(Exception ex) {
        return MapBuilder.map()
                .add("success", false)
                .add("reason", ex.getMessage())
                .build();
    }
}
