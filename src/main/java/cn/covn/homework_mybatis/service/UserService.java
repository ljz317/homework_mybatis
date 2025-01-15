package cn.covn.homework_mybatis.service;

import cn.covn.homework_mybatis.repository.dao.UserDao;
import cn.covn.homework_mybatis.model.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User Login(String username, String password)
    {
        return userDao.CheckUser(username, password);
    };
}
