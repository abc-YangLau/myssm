package cn.star.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.star.domain.AverageCapital;
import cn.star.domain.Users;
import cn.star.domain.inputDomain;
import cn.star.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    Date date = new Date(System.currentTimeMillis());

    /**
     * 查询用户
     *
     * @return
     */
    @RequestMapping("/findUsers")
    public String findUsers(Model model) {
        System.out.println("表现层：查询用户");
        // 调用service对象的方法进行测试
        List<Users> list = usersService.findUsers();
        model.addAttribute("userlist", list);
        return "Users";
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public String logon(Users users) throws Exception {
        return "logon";
    }

    @RequestMapping("/insert")
    public String insert(Users users) throws Exception {
        System.out.println("注册");
        if (users.getUsername() == null || users.getUsername().equals("") || users.getPassword() == null
                || users.getPassword().contentEquals("")) {
            return "false";
        }
        // 调用注入的 usersService 调用 insertUsers 方法F
        usersService.insertUsers(users);
        SendEmail.sendValidEmail(users);
        return "success";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(Users users, Model model) {
        usersService.deleteUsersById(users);
        List<Users> list = usersService.findUsers();
        model.addAttribute("userlist", list);
        return "Users";
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public String login(Users users) {
        System.out.println("登录");
        // 调用注入的 usersService 调用 login 方法
        if (usersService.login(users)) {
            return "successlogin";
        } else {
            return "falselogin";
        }
    }

    /**
     * 等額本息計算
     */
    @RequestMapping("/averageCapital")
    public String averageCapital(inputDomain temp, Model model) {
        List<AverageCapital> list = usersService.averageCapital(temp);
        model.addAttribute("averageCapitalList", list);
        return "AverageCaptial";
    }

    /**
     * 等额本金计算
     */
    @RequestMapping("/averageCapitalPlusInterest")
    public String averageCapitalPlusInterest(inputDomain temp, Model model) {
        List<AverageCapital> list = usersService.averageCapitalPlusInterest(temp);
        model.addAttribute("averageCaptialPlusInterstList", list);
        return "AverageCaptialPlusInterst";
    }

    /**
     * 导出Excel数据
     */
    @RequestMapping("/downloadAverageCapitalPlusInterest")
    public String downloadAverageCapitalPlusInterest(HttpServletResponse response, inputDomain temp, Model model) {
        response.setContentType("application/binary;charset=UTF-8");
        List<AverageCapital> list = usersService.averageCapitalPlusInterest(temp);

        try {
            ServletOutputStream out = response.getOutputStream();
            try {
                // 设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
                response.setHeader("Content-Disposition",
                        "attachment;fileName=AverageCaptialPlusInterst" + URLEncoder.encode(df.format(date) + ".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            String[] titles = {"还款年数", "每月本息", "每月本金", "每月利息"};
            usersService.export(titles, out, list);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "导出信息失败";
        }
    }

    @RequestMapping("/downloadAverageCapital")
    public String downloadAverageCapital(HttpServletResponse response, inputDomain temp, Model model) {
        response.setContentType("application/binary;charset=UTF-8");
        List<AverageCapital> list = usersService.averageCapital(temp);
        try {
            ServletOutputStream out = response.getOutputStream();
            try {
                // 设置文件头)
                response.setHeader("Content-Disposition",
                        "attachment;fileName=AverageCaptial" + URLEncoder.encode(df.format(date) + ".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            String[] titles = {"还款年数", "每月本息", "每月本金", "每月利息"};
            usersService.export(titles, out, list);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "导出信息失败";
        }
    }


}
