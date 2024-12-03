package co.id.mabrur;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.io.FileOutputStream;
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

            JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(list);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("studentName", "John");
            parameters.put("tableData", dataSource);
            parameters.put("subReport", getSubReport());
            parameters.put("subDataSource", getSubDataSource());
            parameters.put("subParameters", getParameters());


            JasperReport report = JasperCompileManager.compileReport(filePath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);

            JasperExportManager.exportReportToPdfFile(print, "D:\\java_app\\CustomReport.pdf");

            JasperExportManager.exportReportToHtmlFile(print, "D:\\java_app\\CustomReport.html");

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
                    new FileOutputStream(new File("D:\\java_app\\CustomReport.xlsx"))
            ));

            exporter.exportReport();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static JasperReport getSubReport(){
        String filePath ="D:\\java_app\\jasperdemo\\src\\main\\resources\\SimpleReport.jrxml";


        try {
            return JasperCompileManager.compileReport(filePath);
        } catch (JRException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static JRBeanCollectionDataSource getSubDataSource(){
        Student student1 = new Student(1L, "Roni", "Din", "Fatahillah Street", "Cirebon");
        Student student2 = new Student(2L, "Suki", "Puki", "Dewi Sartike Street", "Tegal");
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        return dataSource;
    }

    public static Map<String, Object> getParameters(){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("studentName", "John");
        return parameters;
    }
}
