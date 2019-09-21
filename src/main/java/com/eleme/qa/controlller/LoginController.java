package com.eleme.qa.controlller;

import com.eleme.qa.pojo.User;
import com.eleme.qa.result.Result;
import com.eleme.qa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.eleme.qa.pojo.User;
import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser){
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
//        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
//            String message = "账号密码错误";
//            System.out.println("test");
//            return new Result(400);
//        } else {
//            return new Result(200);
//        }

        //数据库服务
        User user = userService.get(username,requestUser.getPassword());
        if (null == user){
            return new Result(400);
        }else{
            return new Result(200);
        }
    }
}
