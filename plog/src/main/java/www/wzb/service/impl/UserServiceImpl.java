package www.wzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.wzb.dao.UserMapper;
import www.wzb.entity.User;
import www.wzb.service.UserService;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public int regist(User user) {
        return userMapper.regist(user);
    }

    @Override
    public User login(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userMapper.findByEmlAndPwd(user);
    }

    @Override
    public User findByEmail(String email) {
        //System.out.println(userMapper.findByEmail(email));
        return userMapper.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        userMapper.deleteByEmail(email);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }


}
