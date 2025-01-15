package cn.covn.homework_mybatis.model.pojo.vo;

import cn.covn.homework_mybatis.repository.dao.ClassDao;
import cn.covn.homework_mybatis.repository.dao.StudentDao;
import cn.covn.homework_mybatis.repository.dao.TeacherDao;
import cn.covn.homework_mybatis.repository.dao.UserDao;
import cn.covn.homework_mybatis.service.UserService;

public class Daos {
    private UserService userService;
    private UserDao userDao;
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private ClassDao classDao;

    public Daos(UserService userService, UserDao userDao, StudentDao studentDao, TeacherDao teacherDao, ClassDao classDao) {
        this.userService = userService;
        this.userDao = userDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.classDao = classDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public ClassDao getClassDao() {
        return classDao;
    }

    public void setClassDao(ClassDao classDao) {
        this.classDao = classDao;
    }
}
