package cn.shijh.dao.mapper;

import cn.shijh.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    @Select("select * from role_user,roles " +
            "where role_user.userId = #{userId} " +
            "and role_user.roleId = roles.id")
    List<Role> findByUserId(@Param("userId") int userId);

    @Select("select * from roles")
    List<Role> findAll();

    @Insert("insert into roles values (#{roleName},#{roleDesc})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Role role);

}
