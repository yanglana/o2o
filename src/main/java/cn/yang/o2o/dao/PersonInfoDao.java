package cn.yang.o2o.dao;

import cn.yang.o2o.entity.PersonInfo;

public interface PersonInfoDao {

    /*
     * @Description 通过用户Id查询用户
     * @Param [userId]
     * @Return cn.yang.o2o.entity.PersonInfo
     */
    PersonInfo queryPersonInfoById(long userId);

    /*
     * @Description 添加用户信息
     * @Param [personInfo]
     * @Return int
     */
    int insertPersonInfo(PersonInfo personInfo);
}
