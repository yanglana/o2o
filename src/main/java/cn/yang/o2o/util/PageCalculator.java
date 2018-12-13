package cn.yang.o2o.util;

/**
 * @Description 将页数转换为行数
 * @Author yanglan
 * @Date 2018/12/13 16:20
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
