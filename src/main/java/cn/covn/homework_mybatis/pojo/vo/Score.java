package cn.covn.homework_mybatis.pojo.vo;

public class Score {
    private Double chinese;
    private Double math;
    private Double english;

    @Override
    public String toString() {
        return "Score{" +
                "chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }

    public Double getChinese() {
        return chinese;
    }

    public void setChinese(Double chinese) {
        this.chinese = chinese;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getEnglish() {
        return english;
    }

    public void setEnglish(Double english) {
        this.english = english;
    }
}
