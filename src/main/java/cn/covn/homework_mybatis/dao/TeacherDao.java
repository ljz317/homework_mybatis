package cn.covn.homework_mybatis.dao;

import cn.covn.homework_mybatis.pojo.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDao {

    Teacher getTeacherByUid(int id);

    int addTeacher(Teacher teacher);
}
