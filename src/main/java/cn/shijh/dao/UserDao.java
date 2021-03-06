package cn.shijh.dao;

import cn.shijh.domain.User;

import java.util.List;

public interface UserDao {
    Long insert(User user);
    void delete(String username);
    List<User> findUser(String username);
    List<User> findAll();
}
