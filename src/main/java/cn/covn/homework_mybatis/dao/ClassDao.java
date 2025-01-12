package cn.covn.homework_mybatis.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassDao {

    int isHaveClass(String className);
}
