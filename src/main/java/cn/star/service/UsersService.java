
package cn.star.service;

import cn.star.domain.AverageCapital;
import cn.star.domain.Users;
import cn.star.domain.inputDomain;

import java.util.List;

import javax.servlet.ServletOutputStream;

public interface UsersService {
    //查询所有用户
    public List<Users> findUsers();

    //用户注册
    public void insertUsers(Users users);

    //删除用户
    public void deleteUsersById(Users users);

    //用户登录
    public boolean login(Users users);

    //等額本息計算
    public List<AverageCapital> averageCapital(inputDomain input);

    //等额本金计算
    public List<AverageCapital> averageCapitalPlusInterest(inputDomain temp);

    //导出excel表
    public void export(String[] titles, ServletOutputStream out, List<AverageCapital> list);
}

