package com.haui.bookshopwebsite.service.impl;

import com.haui.bookshopwebsite.service.ExportService;
import com.haui.bookshopwebsite.dto.BookDto;
import com.haui.bookshopwebsite.dto.CategoryDto;
import com.haui.bookshopwebsite.dto.OrderDTO;
import com.haui.bookshopwebsite.entity.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ExportSerivceImpl implements ExportService {

    @Override
    public String exportOrderReport(User user, List<OrderDTO> orderDTOList, String keyword) {

        String filePath = getClass().getClassLoader().getResource("orders.jrxml").getPath();
        Map<String, Object> parameters = new HashMap<>();
        JRBeanCollectionDataSource orderDataSource = new JRBeanCollectionDataSource(orderDTOList);

        parameters.put("fullName", user.getFullName());
        parameters.put("email", user.getEmail());
        parameters.put("phoneNumber", user.getPhoneNumber());
        parameters.put("orderDataSet", orderDataSource);

        try {
            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, orderDataSource);
//            String desktopPath = System.getProperty("user.home") + "/OneDrive/Desktop";
//            String outputFile = desktopPath + "/order";
            String outputFile ="D:/Downloads/order";
            if(Objects.equals(keyword, "pdf")){
                JasperExportManager.exportReportToPdfFile(print, outputFile + ".pdf");
            }
            if(Objects.equals(keyword, "html")){
                JasperExportManager.exportReportToHtmlFile(print, outputFile + ".html");
            }
        } catch (JRException e) {
            return "Cannot find jasper report file";
        }


        return "Xuất báo cáo thành công";
    }

    @Override
    public String exportCategoryReport(User user, List<CategoryDto> categoryDTOList, String keyword) {
        String filePath = getClass().getClassLoader().getResource("category1.jrxml").getPath();
        Map<String, Object> parameters = new HashMap<>();
        JRBeanCollectionDataSource categoryDataSource = new JRBeanCollectionDataSource(categoryDTOList);
//
//        parameters.put("fullName", user.getFullName());
//        parameters.put("email", user.getEmail());
//        parameters.put("phoneNumber", user.getPhoneNumber());
        parameters.put("categoryDataSet", categoryDataSource);

        try {
            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, categoryDataSource);
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String outputFile = desktopPath + "/category";
            if(Objects.equals(keyword, "pdf")){
                JasperExportManager.exportReportToPdfFile(print, outputFile + ".pdf");
            }
            if(Objects.equals(keyword, "html")){
                JasperExportManager.exportReportToHtmlFile(print, outputFile + ".html");
            }
        } catch (JRException e) {
            return "Cannot find jasper report file";
        }


        return "Xuất báo cáo thành công";
    }

    @Override
    public String exportBookReport(User user, List<BookDto> bookDtoList, String keyword) {
        String filePath = getClass().getClassLoader().getResource("book1.jrxml").getPath();
        Map<String, Object> parameters = new HashMap<>();
        JRBeanCollectionDataSource bookDataSource = new JRBeanCollectionDataSource(bookDtoList);

//        parameters.put("fullName", user.getFullName());
//        parameters.put("email", user.getEmail());
//        parameters.put("phoneNumber", user.getPhoneNumber());
        parameters.put("bookDataSet", bookDataSource);

        try {
            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, bookDataSource);
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String outputFile = desktopPath + "/book";
            if(Objects.equals(keyword, "pdf")){
                JasperExportManager.exportReportToPdfFile(print, outputFile + ".pdf");
            }
            if(Objects.equals(keyword, "html")){
                JasperExportManager.exportReportToHtmlFile(print, outputFile + ".html");
            }
        } catch (JRException e) {
            return "Cannot find jasper report file";
        }


        return "Xuất báo cáo thành công";
    }

}
