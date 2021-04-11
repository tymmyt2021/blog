package www.wzb.dao;
import www.wzb.entity.User;

public interface UserMapper {
    User findByPhone(String phone);

    User findByEmail(String email);

    User findByEmlAndPwd(User user);

    User findById(Long id);

    int regist(User user);

    void deleteByEmail(String email);

    void update(User user);



}
