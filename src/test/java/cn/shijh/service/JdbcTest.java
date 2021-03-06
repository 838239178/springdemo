package cn.shijh.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.PreparedStatement;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:application-mvc.xml"})
@WebAppConfiguration
public class JdbcTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDelete() {
        int up = jdbcTemplate.update("delete from users where userName = ? ", "laoqi");
        System.out.println(up);
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
}
