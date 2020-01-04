//package utils.util;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.beans.BeanUtils;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * excel工具类
// *
// * @author Mark sunlightcs@gmail.com
// * @since 2018-03-24
// */
//public class ExcelUtils {
//
//    /**
//     * Excel导出     辅助理解的两个文件 CodeMatchBean.java   CodeMatchController.java
//     *
//     * @param response      response
//     * @param fileName      文件名
//     * @param sourceList          数据List
//     * @param targetClass     对象Class，即目标对象Class
//     */
//    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> sourceList, Class<?> targetClass) throws IOException {
//
//        // EasyPOI的接口ExcelExportUtil，创建workbook对象   PS：new ExportParams()这个参数类似给sheet表上加标题，括号内可传String参数
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), targetClass, sourceList);
//
//        // 设置字符编码
//        response.setCharacterEncoding("UTF-8");  //
//        // 首部字段属性,Content-Type,内容类型,用于定义网络文件的类型和网页的编码，决定浏览器将以什么形式、什么编码读取这个文件
//        response.setHeader("content-Type", "application/vnd.ms-excel;charset=utf-8");
//        // Content-disposition,可以控制用户请求所得的内容存为一个文件的时候提供一个默认的文件
//        response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
//
//        // 将内存中的文件通过workbook的write方法（方法里面传入OutputStream参数），将文件输出到本地指定的位置
//        ServletOutputStream out = response.getOutputStream();
//        workbook.write(out);  // OutputStream
//
//        //刷新缓冲，将缓冲区中的数据全部取出来,close 方法，自动调用flush方法
//        out.flush();
//    }
//
//    /**
//     * Excel导出，先sourceList转换成List<targetClass>，再导出
//     *
//     * @param response      response
//     * @param fileName      文件名
//     * @param sourceList    原数据List  查询到的原数据，可能包含多余的字段（不需要显示的字段），此时需要把他转化为目标对象Class
//     * @param targetClass   目标对象Class  // 原数据10个字段，而只需要显示和导出其中5个字段
//     */
//    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
//                                     Class<?> targetClass) throws Exception {
//        List targetList = new ArrayList<>(sourceList.size());
//        for(Object source : sourceList){
//            Object target = targetClass.newInstance();
//            BeanUtils.copyProperties(source, target); // Spring中BeanUtils的API，通过反射将一个对象的值赋值个另外一个对象（前提是对象中属性的名字相同）
//            targetList.add(target);
//        }
//
//        exportExcel(response, fileName, targetList, targetClass);
//    }
//}
