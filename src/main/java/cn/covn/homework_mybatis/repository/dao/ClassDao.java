package cn.covn.homework_mybatis.repository.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassDao {

    int isHaveClass(String className);
}
