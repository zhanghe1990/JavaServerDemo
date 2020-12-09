package com.example.demo.controller;


import com.example.demo.Service.UserInfoService;
import com.example.demo.entity.ResponBean;
import com.example.demo.entity.UserInfo;
import com.example.demo.util.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
@Api("userinfo测试controller")
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/hello")
    @ApiOperation("测试接口")
    public String hello() {
        return "hello";
    }

    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponBean<List<UserInfo>> findAll(){
        ResponBean<List<UserInfo>> responBean = new ResponBean<>();
        List<UserInfo> resultList = userInfoService.findAll();
        if(resultList == null){
            responBean.setReturnCode("-1");
            responBean.setReturnMsg("获取失败");
        }else {
            responBean.setReturnCode("00");
            responBean.setReturnMsg("成功");
            responBean.setData(resultList);
        }
        return responBean;
    }

    @ApiOperation("增加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody UserInfo userInfo){
        long id = IdWorker.nextId();
        userInfo.setId(id);
        userInfoService.addRecord(userInfo);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody UserInfo userInfo){
        userInfoService.update(userInfo);
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam String username){
        userInfoService.delete(username);
    }

}
