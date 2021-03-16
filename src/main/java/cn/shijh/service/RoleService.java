package cn.shijh.service;

import cn.shijh.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> getList();

    Role save(Role role) throws IllegalArgumentException;
}
