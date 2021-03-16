package cn.shijh.dao.deprecated;

import cn.shijh.domain.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();
    int insert(String name, String desc);
}
