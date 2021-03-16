package cn.shijh.service;

import cn.shijh.dao.mapper.UserMapper;
import cn.shijh.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:application-mvc.xml"})
@WebAppConfiguration
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() throws Exception {
        User user = userMapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void test2() throws Exception {
        System.out.println(userMapper.findAll());
    }

    @Test
    @Transactional
    public void test3() throws Exception {
        User user = new User();
        user.setUserName("jack");
        user.setEmail("jack@123.com");
        user.setPhoneNum("1299");
        user.setPassword("123");
        System.out.println(userMapper.insert(user));
        System.out.println(user);
        Assert.assertNotNull("id except long value but return null", user.getId());
    }

    @Test
    @Transactional
    public void test4() throws Exception {
        int up = userMapper.updateRole(21, new Long[]{1L, 2L});
        System.out.println(up);
    }
}
