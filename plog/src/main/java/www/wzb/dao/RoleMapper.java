package www.wzb.dao;
import www.wzb.entity.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RoleMapper {
    /**
     * 根据用户id查询角色信息
     * @param uid
     * @return
     */
    List<Role> findByUid(@Param("uid")Long uid);

    Role findById(Long id);

    int add(Role role);
}
