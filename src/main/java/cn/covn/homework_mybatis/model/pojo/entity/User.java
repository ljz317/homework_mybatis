package cn.covn.homework_mybatis.model.pojo.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String role;



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role == null || !role.equals("student")&& !role.equals("teacher")){
            role=null;
        }
        this.role = role;
    }
}
