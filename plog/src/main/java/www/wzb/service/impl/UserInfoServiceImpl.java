package www.wzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.wzb.dao.UserInfoMapper;
import www.wzb.entity.UserInfo;
import www.wzb.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo findByUid(Long uid) {
        return userInfoMapper.findByUid(uid);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.update(userInfo);
    }

    @Override
    public void add(UserInfo userInfo) {
        userInfoMapper.add(userInfo);
    }
}
