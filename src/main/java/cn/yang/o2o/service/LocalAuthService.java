package cn.yang.o2o.service;

import cn.yang.o2o.dto.LocalAuthExecution;
import cn.yang.o2o.entity.LocalAuth;
import cn.yang.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {

    /*
     * @Description 通过帐号和密码获取平台帐号信息
     * @Param [userName, password]
     * @Return cn.yang.o2o.entity.LocalAuth
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    /*
     * @Description 通过userId获取平台帐号信息
     * @Param [userId]
     * @Return cn.yang.o2o.entity.LocalAuth
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /*
     * @Description 绑定微信，生成平台专属的帐号
     * @Param [localAuth]
     * @Return LocalAuthExecution
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /*
     * @Description 修改平台帐号的登录密码
     * @Param [userId, username, password, newPassword]
     * @Return cn.yang.o2o.dto.LocalAuthExecution
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException;
}
