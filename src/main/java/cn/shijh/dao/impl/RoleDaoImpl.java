package cn.shijh.dao.impl;

import cn.shijh.dao.RoleDao;
import cn.shijh.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query("select * from roles", new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public int insert(String name, @Nullable String desc) {
        return jdbcTemplate.update("insert into roles values (?, ?)", name, desc);
    }
}
