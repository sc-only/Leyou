package cn.itcast.service.controller;

import cn.itcast.service.service.UserService;
import cn.itcast.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryUserById(@PathVariable("id") Long id){
        return this.userService.queryUserById(id);
    }

    @GetMapping("all")
    public List<User> show(){
        return this.userService.show();
    }
}
