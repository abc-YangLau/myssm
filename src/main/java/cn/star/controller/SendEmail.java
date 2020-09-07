package cn.star.controller;

import cn.star.domain.Users;
import com.sun.mail.util.MailSSLSocketFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * 用户注册发送邮箱验证信息
 */
public class SendEmail {
    public static void sendValidEmail(Users user) throws Exception {
        if (user == null || user.getEmail() == null) {
            throw new Exception("请输入邮箱信息！");
        }
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码
        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //使用JavaMail发送邮件的5个步骤
        //创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("1017413037@qq.com", "vgnxwkpnfnrgbdae");
            }
        });
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和授权码连上邮件服务器
        ts.connect("smtp.qq.com", "1017413037@qq.com", "vgnxwkpnfnrgbdae");
        //4、创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("1017413037@qq.com"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail().trim()));
        //邮件的标题
        message.setSubject("Lau系统验证邮件");
        //邮件的文本内容
        String info = "恭喜您注册成功，您的用户名：" + user.getUsername() + ",您的密码：" + user.getPassword() +
                "，请妥善保管，如有问题请QQ联系网站客服:1017413037。";
//                +"http://"+MAC.getLocalIPList().get("Lau")+":8080/myssm_war_exploded/index.jsp";
        message.setContent(info, "text/html;charset=UTF-8");
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public static void main(String[] args) {

    }
}
