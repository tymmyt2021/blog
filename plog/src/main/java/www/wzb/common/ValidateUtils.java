package www.wzb.common;

import java.util.Date;


public class ValidateUtils {
    public static boolean isAccountNonExpired(Date now,Date lastTime,Long expiredTime){
        long nowTime = now.getTime()/1000;
        long lastLoginTime = lastTime.getTime()/1000;
        if(nowTime-lastLoginTime<=expiredTime){
            return true;
        }
        return false;
    }
}
