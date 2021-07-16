package com.phynos.solar.common.util.excel;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/7/12 16:19
 */
public class EasyExcelExportUtil {

    public static void export(
            String fileName,
            HttpServletResponse httpServletResponse,
            Class clazz,
            List data) {
        try (OutputStream outputStream = httpServletResponse.getOutputStream()) {
            // 清空response
            httpServletResponse.reset();
            /*application/vnd.ms-excel告诉浏览器要下载的是个excel*/
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            /*请求头设置，Content-Disposition为下载标识，attachment标识以附件方式下载*/
            httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes(), "ISO8859-1") + ".xlsx");
            EasyExcel.write(outputStream, clazz).sheet("数据导出测试1").doWrite(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
