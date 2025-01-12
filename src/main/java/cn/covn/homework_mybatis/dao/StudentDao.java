package cn.covn.homework_mybatis.dao;

import cn.covn.homework_mybatis.pojo.entity.Student;
import cn.covn.homework_mybatis.pojo.vo.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {

    List<String> FindAllStudent();
    Score getScoreByName(String name);
    Score getScoreById(int id);
    int updateScoreByName(String name,Score score);

    int addStudent(Student student);
}
