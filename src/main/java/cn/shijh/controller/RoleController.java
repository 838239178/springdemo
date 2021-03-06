package cn.shijh.controller;

import cn.shijh.domain.Role;
import cn.shijh.service.RoleService;
import cn.shijh.utils.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/list")
    @ResponseBody
    public List<Role> list() {
        return service.getList();
    }

    @PostMapping("/save")
    @ResponseBody
    private Map<String, Object> save(Role role) {
        return MapBuilder.map()
                .add("success", service.save(role)).build();
    }

}
