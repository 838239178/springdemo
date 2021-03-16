package cn.shijh.domain;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JSONType(orders = {"id", "userName", "password", "email", "phoneNum"})
@Getter
@Setter
public class User {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String phoneNum;

    private List<Role> roleList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
