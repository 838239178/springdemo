package cn.shijh.service;

import cn.shijh.domain.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {
    List<Role> getList();
    boolean save(Role role);
}
