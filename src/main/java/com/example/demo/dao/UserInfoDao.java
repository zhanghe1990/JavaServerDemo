package com.example.demo.dao;

import com.example.demo.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {
    int addRecord(UserInfo userInfo);

    List<UserInfo> findAll();

    void update(UserInfo userInfo);

    void delete(String username);
}
