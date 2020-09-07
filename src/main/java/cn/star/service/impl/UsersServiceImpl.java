package cn.star.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.star.dao.UsersDao;
import cn.star.domain.AverageCapital;
import cn.star.domain.Users;
import cn.star.domain.inputDomain;
import cn.star.service.UsersService;

/**
 * 〈一句话功能简述〉<br>
 * <p>
 * 〈Service 实现类〉
 *
 * @author OneStar
 * @create 2019/11/9
 * @since 1.0.0
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    public List<Users> findUsers() {
        // TODO Auto-generated method stub
        System.out.println("业务层：查询用户");
        return usersDao.findUsers();
    }

    public void insertUsers(Users users) {
        // TODO Auto-generated method stub
        System.out.println("业务层：用户注册");
        usersDao.insertUsers(users);
    }

    public void deleteUsersById(Users users) {
        usersDao.deleteUsersById(users);
    }

    public boolean login(Users users) {
        // TODO Auto-generated method stub
        System.out.println("业务层：用户登录");
        if (usersDao.login(users) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 等額本金計算
    public List<AverageCapital> averageCapital(inputDomain input) {
        List<AverageCapital> list = new ArrayList<AverageCapital>();

        // 每月本金
        double monthPri = getPerMonthPrincipal(input.getInvest(), input.getMonth());
        // 获取月利率
        double monthRate = input.getYearRate() / 12;
        monthRate = new BigDecimal(monthRate).setScale(6, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= input.getMonth(); i++) {
            AverageCapital averageCapital = new AverageCapital();
            // 每个月本息
            double monthRes = monthPri + (input.getInvest() - monthPri * (i - 1)) * monthRate;
            monthRes = new BigDecimal(monthRes).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
//			map.put(i, monthRes);
            averageCapital.setNumMonth((i - 1) / 12 + 1);
            averageCapital.setBenjin(monthPri);
            averageCapital.setMonthRate(monthRes);
            averageCapital
                    .setInterest(new BigDecimal(monthRes - monthPri).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
            list.add(averageCapital);
        }
        return list;
    }

    public double getPerMonthPrincipal(double invest, int totalMonth) {
        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }

    // 等额本息
    public List<AverageCapital> averageCapitalPlusInterest(inputDomain temp) {
        List<AverageCapital> list = new ArrayList<AverageCapital>();
        double monthRate = temp.getYearRate() / 12;
        BigDecimal monthIncome = new BigDecimal(temp.getInvest())
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, temp.getMonth())))
                .divide(new BigDecimal(Math.pow(1 + monthRate, temp.getMonth()) - 1), 2, BigDecimal.ROUND_DOWN);
        // monthIncome.doubleValue();每个月本息
        BigDecimal monthInterest;// 每月利息
        for (int i = 1; i < temp.getMonth() + 1; i++) {
            AverageCapital averageCapital = new AverageCapital();
            BigDecimal multiply = new BigDecimal(temp.getInvest()).multiply(new BigDecimal(monthRate));
            BigDecimal sub = new BigDecimal(Math.pow(1 + monthRate, temp.getMonth()))
                    .subtract(new BigDecimal(Math.pow(1 + monthRate, i - 1)));
            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, temp.getMonth()) - 1),
                    6, BigDecimal.ROUND_DOWN);
            monthInterest = monthInterest.setScale(2, BigDecimal.ROUND_DOWN);
//            map.put(i, monthInterest);
            averageCapital.setNumMonth((i - 1) / 12 + 1);
            averageCapital.setMonthRate(monthIncome.doubleValue());
            averageCapital.setInterest(monthInterest.doubleValue());
            averageCapital
                    .setBenjin((monthIncome.subtract(monthInterest)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
            list.add(averageCapital);
        }
        return list;
    }

    /**
     * 导出EXcel表格
     */
    public void export(String[] titles, ServletOutputStream out, List<AverageCapital> list) {
        try {
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            // 居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);// 列索引从0开始
                hssfCell.setCellValue(titles[i]);// 列名1
                hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
            }
            // 第五步，写入实体数据
            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i + 1);
                AverageCapital averageCapital = list.get(i);
                // 第六步，创建单元格，并设置值
                row.createCell(0).setCellValue(averageCapital.getNumMonth());
                row.createCell(1).setCellValue(averageCapital.getMonthRate());
                row.createCell(2).setCellValue(averageCapital.getBenjin());
                row.createCell(3).setCellValue(averageCapital.getInterest());
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
