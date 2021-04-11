package www.wzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.wzb.dao.RoleMapper;
import www.wzb.entity.Role;
import www.wzb.service.RoleService;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public int add(Role role) {
        return roleMapper.add(role);
    }

    @Override
    public List<Role> findByUid(Long uid) {
        System.out.println(uid+"     "+roleMapper.findByUid(uid));
        return roleMapper.findByUid(uid);
    }
}
