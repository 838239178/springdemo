package cn.shijh.dao.impl;

import cn.shijh.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDaoImpl implements ContactDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int removeContact(Long userId) throws DataAccessException {
        return jdbcTemplate.update("delete from role_user where userId = ?", userId);
    }

    @Override
    public int setContact(Long userId, Long[] roleIds) {
        int up = 0;
        for (Long roleId : roleIds) {
            up += jdbcTemplate.update("insert into role_user values (?, ?)", roleId, userId);
        }
        return up;
    }

    @Override
    public int setContact(Long[] userIds, Long roleId) {
        int up = 0;
        for (Long userId : userIds) {
            up += jdbcTemplate.update("insert into role_user values (?, ?)", roleId, userId);
        }
        return up;
    }
}
