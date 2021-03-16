package cn.shijh.service;

import cn.shijh.domain.User;

import java.util.List;

public interface UserService {
    List<User> getList();

    User delete(User user);

    User save(User user, Long[] roleIds) throws IllegalArgumentException;

    User verify(String name, String pwd);
}
