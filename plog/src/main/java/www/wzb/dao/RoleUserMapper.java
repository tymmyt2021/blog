package www.wzb.dao;

import www.wzb.entity.RoleUser;
import www.wzb.entity.User;

import java.util.List;

public interface RoleUserMapper {
    void deleteByUid(Long uid);
    List<RoleUser> findByUser(User user);
    int add(RoleUser roleUser);

}
