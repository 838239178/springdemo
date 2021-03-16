package cn.shijh.service.impl;

import cn.shijh.dao.mapper.RoleMapper;
import cn.shijh.domain.Role;
import cn.shijh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getList() {
        return roleMapper.findAll();
    }

    @Override
    public Role save(Role role) throws IllegalArgumentException {
        int row = roleMapper.insert(role);
        if (row == 0) {
            throw new IllegalArgumentException("role insert fail, check roleName");
        }
        return role;
    }
}
