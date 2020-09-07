package myssm;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 等额本息还款，也称定期付息，即借款人每月按相等的金额偿还贷款本息，其中每月贷款利息按月初剩余贷款本金计算并逐月结清。把按揭贷款的本金总额与利息总额相加，
 * 然后平均分摊到还款期限的每个月中。作为还款人，每个月还给银行固定金额，但每月还款额中的本金比重逐月递增、利息比重逐月递减。
 */

public class AverageCapitalPlusInterestUtils {

    public static final int MO = 12;

    public static void main(String[] args) {
        double invest = 1000000; // 本金
        int totalMonth = 10;
        double yearRate = 0.049; // 年利率

//        Map<String ,Object> result = getAverageCapitalPlusInterest(invest, yearRate, totalMonth);
        System.out.println(getAverageCapitalPlusInterestAll(500000, 0.0325, 500000, 0.049, 10));
    }


    /**
     * 等额本息
     *
     * @param invest     总金额
     * @param yearRate   年利率
     * @param totalMonth 贷款年限
     * @return
     */
    public static Map<String, Object> getAverageCapitalPlusInterest(double invest, double yearRate, int totalMonth) {
        int month = totalMonth * MO;

        Map<String, Object> result = new HashMap<String, Object>();

        BigDecimal benxi = getPerMonthPrincipalInterest(invest, yearRate, month);
        Map<Integer, BigDecimal> mapPrincipal = getPerMonthPrincipal(invest, yearRate, month);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, month);
        BigDecimal lixicount = getInterestCount(invest, yearRate, month);
        BigDecimal allcount = getPrincipalInterestCount(invest, yearRate, month);

        result.put("allMonth", month);//总月数
        result.put("benxi", benxi);//成本+利息

//        result.put("benjin", mapPrincipal.get(1));//首月本金
//        result.put("firstMapInterest", mapInterest.get(1));//首月利息
        result.put("lixicount", lixicount);//总利息
        result.put("allcount", allcount);//本息合计


        System.out.println("等额本息---每月本息：" + benxi);
        System.out.println("等额本金---首月本金:" + mapPrincipal.get(1));
        System.out.println("等额本息---每月本金：" + mapPrincipal);
        System.out.println("等额本息---每月利息：" + mapInterest);
        System.out.println("等额本息---首月利息：" + mapInterest.get(1));
        System.out.println("等额本息---总利息：" + lixicount);
        System.out.println("等额本息---本息合计：" + allcount);
        return result;
    }

    /**
     * 等额本息组合贷
     *
     * @param invest1    公基金
     * @param yearRate1  公积金年利率
     * @param invest2    商贷金额
     * @param yearRate2  商贷年利率
     * @param totalMonth 贷款年限
     * @return
     */
    public static Map<String, Object> getAverageCapitalPlusInterestAll(double invest1, double yearRate1, double invest2, double yearRate2, int totalMonth) {
        int month = totalMonth * MO;

        Map<String, Object> map = new HashMap<String, Object>();

        //成本+利息
        BigDecimal benxi1 = getPerMonthPrincipalInterest(invest1, yearRate1, month);
        //总利息
        BigDecimal lixicount1 = getInterestCount(invest1, yearRate1, month);
        //合计
        BigDecimal allcount1 = getPrincipalInterestCount(invest1, yearRate1, month);

        //成本+利息
        BigDecimal benxi2 = getPerMonthPrincipalInterest(invest2, yearRate2, month);
        //总利息
        BigDecimal lixicount2 = getInterestCount(invest2, yearRate2, month);
        //合计
        BigDecimal allcount2 = getPrincipalInterestCount(invest2, yearRate2, month);

        DecimalFormat df = new DecimalFormat("#.00");

        map.put("allMonth", month);//总月数
        map.put("benxi", df.format(benxi1.setScale(2, BigDecimal.ROUND_HALF_UP).add(benxi2.setScale(2, BigDecimal.ROUND_HALF_UP))));//成本+利息
        map.put("lixicount", df.format(lixicount1.add(lixicount2)));//总利息
        map.put("allcount", df.format(allcount1.add(allcount2)));//本息合计
        return map;
    }


    /**
     * 等额本息计算获取还款方式为等额本息的每月偿还本金和利息
     * <p>
     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     *
     * @param invest   总借款额（贷款本金）
     * @param yearRate 年利率
     * @param month    还款总月数
     * @return 每月偿还本金和利息, 不四舍五入，直接截取小数点最后两位
     */
    public static BigDecimal getPerMonthPrincipalInterest(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / MO;
        BigDecimal monthIncome = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_UP);
        //return monthIncome.doubleValue();
        return monthIncome;
    }

    /**
     * 等额本息计算获取还款方式为等额本息的每月偿还利息
     * <p>
     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
     *
     * @param invest   总借款额（贷款本金）
     * @param yearRate 年利率
     * @param month    还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, BigDecimal> getPerMonthInterest(double invest, double yearRate, int totalmonth) {
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        double monthRate = yearRate / MO;
        BigDecimal monthInterest;
        for (int i = 1; i < totalmonth + 1; i++) {
            BigDecimal multiply = new BigDecimal(invest).multiply(new BigDecimal(monthRate));
            BigDecimal sub = new BigDecimal(Math.pow(1 + monthRate, totalmonth))
                    .subtract(new BigDecimal(Math.pow(1 + monthRate, i - 1)));
            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2,
                    BigDecimal.ROUND_HALF_UP);
            monthInterest = monthInterest.setScale(2, BigDecimal.ROUND_HALF_UP);
            map.put(i, monthInterest);
        }
        return map;
    }

    /**
     * 等额本息计算获取还款方式为等额本息的每月偿还本金
     *
     * @param invest   总借款额（贷款本金）
     * @param yearRate 年利率
     * @param month    还款总月数
     * @return 每月偿还本金
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / MO;
        BigDecimal monthIncome = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_HALF_UP);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalmonth);
        Map<Integer, BigDecimal> mapPrincipal = new HashMap<Integer, BigDecimal>();

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            mapPrincipal.put(entry.getKey(), monthIncome.subtract(entry.getValue()));
        }
        return mapPrincipal;
    }

    /**
     * 等额本息计算获取还款方式为等额本息的总利息
     *
     * @param invest   总借款额（贷款本金）
     * @param yearRate 年利率
     * @param month    还款总月数
     * @return 总利息
     */
    public static BigDecimal getInterestCount(double invest, double yearRate, int totalmonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, totalmonth);

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            count = count.add(entry.getValue());
        }
        return count;
    }

    /**
     * 应还本金总和
     *
     * @param invest   总借款额（贷款本金）
     * @param yearRate 年利率
     * @param month    还款总月数
     * @return 应还本金总和
     */
    public static BigDecimal getPrincipalInterestCount(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / MO;
        BigDecimal perMonthInterest = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal count = perMonthInterest.multiply(new BigDecimal(totalmonth));
        count = count.setScale(2, BigDecimal.ROUND_HALF_UP);
        return count;
    }
}