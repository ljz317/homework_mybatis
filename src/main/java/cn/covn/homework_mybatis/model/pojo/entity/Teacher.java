package cn.covn.homework_mybatis.model.pojo.entity;

public class Teacher {
    private Integer id;
    private Integer uid;
    private String name;
    private Double salary;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
