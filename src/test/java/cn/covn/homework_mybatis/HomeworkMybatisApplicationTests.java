package cn.covn.homework_mybatis;

import cn.covn.homework_mybatis.dao.StudentDao;
import cn.covn.homework_mybatis.dao.TeacherDao;
import cn.covn.homework_mybatis.dao.UserDao;
import cn.covn.homework_mybatis.pojo.entity.User;
import cn.covn.homework_mybatis.pojo.vo.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeworkMybatisApplicationTests {

    @Autowired
    UserDao userDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Test
    void contextLoads() {
    }
    @Test
    void FindAllOfUser() {
        for (User user : userDao.GetAllUser()) {
            System.out.println(user);
        }
    }

    @Test
    void CheckUser() {
        if(userDao.CheckUser("aoligei","aoligei1")==null)
        {
            System.out.println("失败");
            return;
        }
        System.out.println("成功");
    }
    @Test
    void FindAllOfStudent() {
        studentDao.FindAllStudent().forEach(System.out::println);
    }
    @Test
    void FindScoreByName() {
        System.out.println(studentDao.getScoreByName("xuesheng"));
    }
    @Test
    void updateScoreByName() {
        Score score = new Score();
        score.setChinese(100.0);
        score.setMath(100.0);
        score.setEnglish(100.0);
        System.out.println(studentDao.updateScoreByName("xuesheng",score));
    }
    @Test
    void FindTeacherById() {
        System.out.println(teacherDao.getTeacherByUid(1));
    }
}
