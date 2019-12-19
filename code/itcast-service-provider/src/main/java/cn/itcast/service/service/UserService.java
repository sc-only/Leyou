package cn.itcast.service.service;

import cn.itcast.service.mapper.UserMapper;
import cn.itcast.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    public List<User> show(){
        List<User> list = this.userMapper.selectAll();
        return list;
    }
}
