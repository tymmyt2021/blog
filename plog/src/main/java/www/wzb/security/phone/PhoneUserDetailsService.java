package www.wzb.security.phone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import www.wzb.entity.User;
import www.wzb.service.RoleService;
import www.wzb.service.UserService;
import www.wzb.entity.Role;

import java.util.List;


public class PhoneUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	public UserDetails loadUserByUsername(String phone) throws PhoneNotFoundException {
		System.out.println(userService.findByPhone(phone));
		User user = userService.findByPhone(phone);
		if(user == null){
			throw new PhoneNotFoundException("手机号码错误");
		}

		List<Role> roles = roleService.findByUid(user.getId());
		user.setRoles(roles);
		return user;
	}
}
