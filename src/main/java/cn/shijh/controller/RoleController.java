package cn.shijh.controller;

import cn.shijh.domain.Role;
import cn.shijh.service.RoleService;
import cn.shijh.utils.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/role", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/list")
    public List<Role> list() {
        return service.getList();
    }

    @PostMapping("/save")
    public Map<String, Object> save(Role role) {
        Role r = service.save(role);
        return MapBuilder.map()
                .add("success", true)
                .add("data", r)
                .build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    private Map<String, Object> exceptionHandler(Exception ex) {
        return MapBuilder.map()
                .add("success", false)
                .add("reason", ex.getMessage())
                .build();
    }
}
