package www.wzb.dao;

import www.wzb.entity.UserInfo;

public interface UserInfoMapper {

    UserInfo findByUid(Long uid);

    void update(UserInfo userInfo);

    void add(UserInfo userInfo);
}
