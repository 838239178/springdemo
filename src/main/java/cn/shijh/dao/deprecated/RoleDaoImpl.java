package cn.shijh.dao.deprecated;

import cn.shijh.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

import java.util.List;

@Deprecated
public class RoleDaoImpl implements RoleDao {

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
