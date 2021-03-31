package cn.shijh.dao.mapper;

import cn.shijh.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from users where id=#{id}")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(
                    column = "id",
                    property = "roleList",
                    many = @Many(select = "cn.shijh.dao.mapper.RoleMapper.findByUserId")
            )
    })
    User findById(long id);

    @Delete("delete " +
            "from role_user " +
            "from role_user left join users u on role_user.userId = u.id and u.id = #{id}")
    int delete(long id);

    @SelectProvider(type = SqlProvider.class, method = "selectSql")
    @ResultMap("userMap")
    List<User> findSelective(User user);

    @InsertProvider(type = SqlProvider.class, method = "insertSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User user);

    @Select("select * from users")
    @ResultMap("userMap")
    List<User> findAll();

    @InsertProvider(type = SqlProvider.class, method = "setRoleSql")
    int updateRole(long userId, Long[] roleIds);

    class SqlProvider {
        public String selectSql(User user) {
            return new SQL() {{
                SELECT("*");
                FROM("users");
                if (user.getId() != null) {
                    WHERE("id=#{id}");
                }
                if (user.getUserName() != null) {
                    WHERE("userName=#{userName}");
                }
                if (user.getPassword() != null) {
                    WHERE("password=#{password}");
                }
                if (user.getPhoneNum() != null) {
                    WHERE("phoneNum=#{phoneNme}");
                }
            }}.toString();
        }

        public String insertSql(User user) {
            return new SQL() {{
                INSERT_INTO("users");
                if (user.getUserName() != null) {
                    VALUES("userName", "#{userName}");
                }
                if (user.getPhoneNum() != null) {
                    VALUES("phoneNum", "#{phoneNum}");
                }
                if (user.getEmail() != null) {
                    VALUES("email", "#{email}");
                }
                if (user.getPassword() != null) {
                    VALUES("password", "#{password}");
                }
            }}.toString();
        }

        public String setRoleSql(long userId, Long[] roleIds) {
            return new SQL() {{
                INSERT_INTO("role_user");
                INTO_COLUMNS("userId", "roleId");
                INTO_VALUES(String.format("${%d}", userId), String.format("${%d}", roleIds[0]));
                for (int i = 1; i < roleIds.length; i++) {
                    ADD_ROW();
                    INTO_VALUES(String.format("${%d}", userId), String.format("${%d}", roleIds[i]));
                }
            }}.toString();
        }
    }
}
