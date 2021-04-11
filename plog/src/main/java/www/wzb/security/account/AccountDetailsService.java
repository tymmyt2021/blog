package www.wzb.security.account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import www.wzb.entity.User;
import www.wzb.entity.Role;
import www.wzb.service.RoleService;
import www.wzb.service.UserService;


import java.util.List;

/**
 * Created by 12903 on 2018/6/30.
 */
public class AccountDetailsService implements UserDetailsService{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        User user = userService.findByEmail(email);

        if(user == null){

            throw new UsernameNotFoundException("用户名或密码错误");

        }
        //System.out.println("666666");
        List<Role> roles = roleService.findByUid(user.getId());
        //System.out.println(roles);
        user.setRoles(roles);
        //System.out.println("666"+user);

        //

        return user;
    }
}
