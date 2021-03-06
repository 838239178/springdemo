package cn.shijh.service;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:application-mvc.xml"})
@WebAppConfiguration
public class RoleControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc
    }

    @Test
    public void test1() {
        try {
            MvcResult res = mockMvc.perform(
                    MockMvcRequestBuilders.get("/role/list").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andReturn();
            System.out.println(res.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        MvcResult res = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/role/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .characterEncoding("UTF-8")
                        .param("roleName", "宇航员").param("roleDesc", "上天的人")
        ).andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }

    @Test
    public void test3() throws Exception {
        MvcResult res = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/user/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .characterEncoding("UTF-8")
                        .param("userName", "qqqq")
                        .param("password", "123")
        ).andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }

    @Test
    public void test4() throws Exception {
        MvcResult res = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/user/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .characterEncoding("UTF-8")
                        .param("userName", "laoliu")
                        .param("password", "123")
                        .param("email", "laoliu@123.com")
                        .param("phoneNum", "1266")
                        .param("roleIds", "1")
                        .param("roleIds", "3")
        ).andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }

    @Test
    public void test5() throws Exception{
        MvcResult res = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/user/cancellation")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .characterEncoding("UTF-8")
                        .param("userName", "laoliu")
        ).andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }



    @Test
    public void test6() throws Exception {
        MvcResult res = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/logout")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andReturn();
        System.out.println(res.getResponse().getContentAsString());
    }
}