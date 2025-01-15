package cn.covn.homework_mybatis.repository.dao;

import cn.covn.homework_mybatis.model.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> GetAllUser();

    User CheckUser(String username,String password);

    int isHaveUserByUsername(String username);

    int addUser(User user);



}
