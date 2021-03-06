package cn.shijh.service.impl;

import cn.shijh.dao.RoleDao;
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
    private RoleDao dao;

    @Override
    public List<Role> getList() {
        return dao.findAll();
    }

    @Override
    public boolean save(Role role) {
        return dao.insert(role.getRoleName(), role.getRoleDesc()) > 0;
    }
}
