package cn.shijh.dao.deprecated;

import cn.shijh.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Deprecated
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void delete(String username) throws DataAccessException {
        jdbcTemplate.update("delete from users where userName = ?", username);
    }

    @Override
    public Long insert(User user) throws DataAccessException {
        PreparedStatementCreator  psc = connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into users values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhoneNum());
            return ps;
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, holder);
        return holder.getKey().longValue();
    }

    @Override
    public List<User> findUser(String username) {
        return jdbcTemplate.query("select * from users where username = ?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
