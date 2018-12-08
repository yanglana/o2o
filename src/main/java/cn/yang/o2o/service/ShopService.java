package cn.yang.o2o.service;

import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}
