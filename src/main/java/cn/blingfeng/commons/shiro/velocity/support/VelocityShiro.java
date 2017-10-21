package cn.blingfeng.commons.shiro.velocity.support;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Shiro权限标签(Velocity版) 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com 
 * @date 2016年12月3日 下午11:32:47 
 */
public class VelocityShiro {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 是否拥有该权限 
     *
     * @param permission
     *            权限标识 
     * @return true：是 false：否 
     */
    public boolean hasPermission(String permission) {
        logger.info(permission);
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }

    /**
     * 是否拥有该权限 
     *
     * @param permission
     *            权限标识 
     * @return true：是 false：否 
     */
    public static boolean hasPermissionInMethod(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }
    public Object getPrincipalProperty(String property) {
        Subject subject = SecurityUtils.getSubject();

        if (subject != null) {
            Object principal = subject.getPrincipal();

            try {
                BeanInfo bi = Introspector.getBeanInfo(principal.getClass());

                for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
                    if (pd.getName().equals(property) == true) {
                        return pd.getReadMethod().invoke(principal, (Object[]) null);
                    }
                }

                logger.trace("Property [{}] not found in principal of type [{}]", property,
                        principal.getClass().getName());
            } catch (Exception e) {
                logger.trace("Error reading property [{}] from principal of type [{}]", property,
                        principal.getClass().getName());
            }
        }

        return null;
    }

}  