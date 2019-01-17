package cn.yang.o2o.service;

import cn.yang.o2o.entity.PersonInfo;

public interface PersonInfoService {

    /*
     * @Description 根据用户Id获取personInfo信息
     * @Param [userId]
     * @Return cn.yang.o2o.entity.PersonInfo
     */
    PersonInfo getPersonInfoById(Long userId);
}
