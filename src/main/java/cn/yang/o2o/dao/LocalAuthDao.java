package cn.yang.o2o.dao;

import cn.yang.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LocalAuthDao {

    /*
     * @Description 通过帐号和密码查询对应信息，登录用
     * @Param [username, password]
     * @Return cn.yang.o2o.entity.LocalAuth
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /*
     * @Description 通过用户Id查询对应localauth
     * @Param [userId]
     * @Return cn.yang.o2o.entity.LocalAuth
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    /*
     * @Description 添加平台帐号
     * @Param [localAuth]
     * @Return int
     */
    int insertLocalAuth(LocalAuth localAuth);

    /*
     * @Description 通过userId,username,password更改密码
     * @Param [userId, username, password, newPassword, lastEditTime]
     * @Return int
     */
    int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
