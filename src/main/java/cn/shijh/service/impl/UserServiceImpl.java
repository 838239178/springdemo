package cn.shijh.service.impl;

import cn.shijh.dao.ContactDao;
import cn.shijh.dao.UserDao;
import cn.shijh.domain.User;
import cn.shijh.service.UserService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements UserService {


    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    @Autowired
    private ContactDao contactDao;


    @Override
    public List<User> getList() {
        List<User> all = userDao.findAll();
        return all != null ? all : new ArrayList<>(0);
    }

    @Override
    public User save(User user, Long[] roleIds) {
        if(!userDao.findUser(user.getUserName()).isEmpty()){
            throw new IllegalArgumentException("User Name has existed");
        }
        Long userId = userDao.insert(user);
        if(userId == null){
            throw new NullValueInNestedPathException(User.class,"id","nested id couldn't be null");
        }
        int up = contactDao.setContact(userId,roleIds);
        if(up != roleIds.length){
            throw new IllegalArgumentException("user has existed role id");
        }
        user.setId(userId);
        return user;
    }

    @Override
    public User delete(String username) {
        List<User> users = userDao.findUser(username);
        if(users.isEmpty())
            throw new NoSuchElementException("User not found");
        User user = users.get(0);
        contactDao.removeContact(user.getId());
        userDao.delete(username);
        return user;
    }

    @Override
    public User verify(String userName, @NotNull String password) {
        List<User> users = userDao.findUser(userName);
        if (users.isEmpty())
            throw new NoSuchElementException("User not found");
        User user = users.get(0);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new IllegalArgumentException("Password wrong");
        }
    }
}
