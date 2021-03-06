package cn.shijh.service;

import cn.shijh.domain.User;

import java.util.List;

public interface UserService {
    List<User> getList();
    User delete(String username);
    User save(User user, Long[] roleIds);
    User verify(String name, String pwd);
}
