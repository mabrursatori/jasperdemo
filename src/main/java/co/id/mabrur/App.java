package co.id.mabrur;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        try{
            String filePath ="D:\\java_app\\jasperdemo\\src\\main\\resources\\SimpleReport.jrxml";

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("studentName", "John");

            Student student1 = new Student(1L, "Roni", "Din", "Fatahillah Street", "Cirebon");
            Student student2 = new Student(2L, "Suki", "Puki", "Dewi Sartike Street", "Tegal");
            List<Student> list = new ArrayList<>();
            list.add(student1);
            list.add(student2);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

            JasperReport report = JasperCompileManager.compileReport(filePath);

            JRBaseTextField textField = (JRBaseTextField) report.getTitle().getElementByKey("name");

            textField.setForecolor(Color.RED);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(print, "D:\\java_app\\ExampleReport.pdf");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
