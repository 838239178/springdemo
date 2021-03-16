package cn.shijh.service.impl;

import cn.shijh.dao.mapper.UserMapper;
import cn.shijh.domain.User;
import cn.shijh.service.UserService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getList() {
        return userMapper.findAll();
    }

    @Override
    public User save(User user, Long[] roleIds) throws IllegalArgumentException {
        int row = userMapper.insert(user);
        if (row == 0) {
            throw new IllegalArgumentException("user insert fail, check username and email");
        }
        if (user.getId() == null) {
            throw new NullValueInNestedPathException(User.class, "id", "nested id couldn't be null");
        }
        row = userMapper.updateRole(user.getId(), roleIds);
        if (row != roleIds.length) {
            throw new IllegalArgumentException("user has existed role id");
        }
        return user;
    }

    @Override
    public User delete(User user) {
        int up = userMapper.delete(user.getId());
        if (up == 0)
            throw new NoSuchElementException("User not found");
        return user;
    }

    @Override
    public User verify(String userName, @NotNull String password) {
        List<User> users = userMapper.findSelective(new User() {{
            setUserName(userName);
            setPassword(password);
        }});
        if (users.isEmpty())
            throw new NoSuchElementException("username no exit or password wrong");
        return users.get(0);
    }
}
