package cn.yang.o2o.service.impl;

import cn.yang.o2o.dto.LocalAuthExecution;
import cn.yang.o2o.entity.LocalAuth;
import cn.yang.o2o.exceptions.LocalAuthOperationException;
import cn.yang.o2o.service.LocalAuthService;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/28 17:34
 */
public class LocalAuthServiceImpl implements LocalAuthService {
    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password) {
        return null;
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return null;
    }

    @Override
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        return null;
    }

    @Override
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        return null;
    }
}
