package myssm;

import cn.star.service.UsersService;

import org.junit.Test;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈测试类〉
 */
public class springtest {
    @Test
    public void Test() {
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //获取对象
        UsersService us = (UsersService) ac.getBean("usersService");
        //调用方法
        us.findUsers();
    }

}
