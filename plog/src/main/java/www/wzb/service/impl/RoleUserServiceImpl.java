package www.wzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.wzb.dao.RoleUserMapper;
import www.wzb.entity.RoleUser;
import www.wzb.entity.User;
import www.wzb.service.RoleUserService;

import java.util.List;
@Service
public class RoleUserServiceImpl implements RoleUserService{
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Override
    public List<RoleUser> findByUser(User user) {

        return roleUserMapper.findByUser(user);
    }

    @Override
    public int add(RoleUser roleUser) {
        return roleUserMapper.add(roleUser);
    }

    @Override
    public void deleteByUid(Long uid) {
        roleUserMapper.deleteByUid(uid);
    }
}
