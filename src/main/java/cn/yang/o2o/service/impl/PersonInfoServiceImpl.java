package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.PersonInfoDao;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/15 17:56
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }
}
