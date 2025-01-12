package cn.covn.homework_mybatis.dao;

import cn.covn.homework_mybatis.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> GetAllUser();

    User CheckUser(String username,String password);

    int isHaveUserByUsername(String username);

    int addUser(User user);



}
