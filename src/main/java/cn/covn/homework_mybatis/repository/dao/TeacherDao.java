package cn.covn.homework_mybatis.repository.dao;

import cn.covn.homework_mybatis.model.pojo.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDao {

    Teacher getTeacherByUid(int id);

    int addTeacher(Teacher teacher);
}
