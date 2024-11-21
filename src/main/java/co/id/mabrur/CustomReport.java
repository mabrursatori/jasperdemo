package co.id.mabrur;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomReport {
    public static void main(String[] args){
        try{
            String filePath = "D:\\java_app\\jasperdemo\\src\\main\\resources\\Student.jrxml";

            Subject subject1 = new Subject("Naruto", 90);
            Subject subject2 = new Subject("One Piece", 80);
            Subject subject3 = new Subject("Dragon Ball", 80);
            Subject subject4 = new Subject("Jujutsu Kaisen", 60);
            Subject subject5 = new Subject("Boruto", 30);

            List<Subject> list = new ArrayList<>();
            list.add(subject1);
            list.add(subject2);
            list.add(subject3);
            list.add(subject4);
            list.add(subject5);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("studentName", "John");
            parameters.put("tableData", dataSource);



            JasperReport report = JasperCompileManager.compileReport(filePath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            JasperExportManager.exportReportToPdfFile(print, "D:\\java_app\\CustomReport.pdf");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
