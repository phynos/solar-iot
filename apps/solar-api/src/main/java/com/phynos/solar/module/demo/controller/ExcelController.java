package com.phynos.solar.module.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.phynos.solar.module.demo.vo.ExcelDemoVO;
import com.phynos.solar.util.excel.ExcelStyleUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/24 08:58
 */
@Slf4j
@Controller
public class ExcelController {

    @GetMapping("/export")
    public void rtAlarmListExport2(HttpServletResponse response) {
        List<ExcelDemoVO> data = new ArrayList<>();
        export(data, "demo", "导出例子", response);
    }

    private void export(
            List<ExcelDemoVO> data,
            String fileName,
            String sheetName,
            HttpServletResponse response) {
        //生成excel文件
        ExportParams params = new ExportParams();
        params.setSheetName(sheetName);
        params.setType(ExcelType.XSSF);
        params.setStyle(ExcelStyleUtil.class);
        try (OutputStream os = response.getOutputStream();
             Workbook workbook = ExcelExportUtil.exportExcel(params, ExcelDemoVO.class, data)
        ) {
            response.setContentType("application/vnd.ms-excel");
            String finalFileName = fileName + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename=" + finalFileName);
            workbook.write(os);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
