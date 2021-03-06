package www.wzb.service;

import www.wzb.entity.UserInfo;

public interface UserInfoService {
    /**
     * 根据用户id查找用户详细信息
     * @param id
     * @return
     */
    UserInfo findByUid(Long uid);

    /**
     * 更新用户详细信息
     * @param userInfo
     */
    void update(UserInfo userInfo);

    /**
     * 添加用户详细新
     * @param userInfo
     */
    void add(UserInfo userInfo);
}
