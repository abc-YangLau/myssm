package cn.star.domain;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户类〉
 */

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String password;

    private String phoneNum;

    private String email;

    public Users(String password) {
        this.password = password;
    }

    public Users() {}

    public Users(int id, String username, String password, String phoneNum, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
    }
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

