package myssm;

import cn.star.dao.UsersDao;
import cn.star.domain.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈mybatis测试类〉
 */

public class mybatistest {
    /**
     * 测试查询用户
     *
     * @throws Exception
     */
    @Test
    public void run1() throws Exception {
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        UsersDao dao = session.getMapper(UsersDao.class);
        // 查询所有数据
        List<Users> list = dao.findUsers();
        for (Users users : list) {
            System.out.println(users);
        }
        // 关闭资源
        session.close();
        in.close();
    }

    /**
     * 测试注册
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void run2() throws Exception {
        Users users = new Users("password");
        users.setUsername("forthstar");
        users.setPassword("520");
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
        UsersDao dao = session.getMapper(UsersDao.class);
        // 保存
        dao.insertUsers(users);
        // 提交事务
        session.commit();
        // 关闭资源
        session.close();
        in.close();
    }

}

