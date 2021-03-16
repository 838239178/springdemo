package cn.shijh.domain;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import lombok.Setter;

@JSONType(orders = {"id", "roleName", "roleDesc"})
@Getter
@Setter
public class Role {

    private Long id;
    private String roleName;
    private String roleDesc;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
