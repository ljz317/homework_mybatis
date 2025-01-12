package cn.covn.homework_mybatis.pojo.entity;

public class ClassName {
    private Integer id;
    private Integer tid;
    private String className;

    @Override
    public String toString() {
        return "ClassName{" +
                "id=" + id +
                ", tid=" + tid +
                ", className='" + className + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
