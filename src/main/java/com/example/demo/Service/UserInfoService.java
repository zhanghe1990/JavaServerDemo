package com.example.demo.Service;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    public int addRecord(UserInfo userInfo){
        return userInfoDao.addRecord(userInfo);
    }

    public List<UserInfo> findAll(){
        return userInfoDao.findAll();
    }

    public void update(UserInfo userInfo)
    {
        userInfoDao.update(userInfo);
    }

    public void delete(String username){
        userInfoDao.delete(username);
    }
}
