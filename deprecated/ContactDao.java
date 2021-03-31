package cn.shijh.dao.deprecated;

public interface ContactDao {
    int setContact(Long userId, Long[] roleIds);
    int removeContact(Long userId);
    int setContact(Long[] userIds, Long roleId);
}
