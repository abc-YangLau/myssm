package cn.star.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.star.domain.Users;

/**
 * 〈一句话功能简述〉<br>
 * 〈数据访问层 UsersDao 接口〉
 */
//@Repository
@Mapper
public interface UsersDao {

    //查询所有用户
//    @Select("select * from users")
    public List<Users> findUsers();

    //用户注册
//    @Insert("INSERT INTO USERS (username,PASSWORD,email,phoneNum) VALUES(#{username},#{PASSWORD},#{email},#{phoneNum})")
    public void insertUsers(@Param("users") Users users);

    //用户登录
//    @Select("select * from users where username=#{username} and PASSWORD=#{PASSWORD}")
    public Users login(@Param("users") Users users);

    //删除用户
//    @Delete("delete from users where id=#{id}")
    public void deleteUsersById(@Param("users") Users users);
}

