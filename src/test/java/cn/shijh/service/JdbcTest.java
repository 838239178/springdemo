package cn.shijh.service;

import cn.shijh.utils.MapBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:application-mvc.xml"})
@WebAppConfiguration
public class JdbcTest {

    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDelete() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select *\n" +
                "from users" +
                "         inner join role_user on role_user.userId = users.id" +
                "         inner join roles on roles.id = role_user.roleId");
    }

    @Test
    public void test1() {
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement("delete from users where userName = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "laoqi");
            return ps;
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, holder);
        System.out.println(holder.getKey());
    }

    @Test
    public void join() throws Exception {
        Map<String, Object> res = jdbcTemplate.queryForMap("select users.userName,roles.roleName from users " +
                "left join role_user on users.id = userId " +
                "left join roles on roles.id = role_user.roleId " +
                "where users.userName = 'wangwu'");
        System.out.println(MapBuilder.map(res).json());
    }
}
