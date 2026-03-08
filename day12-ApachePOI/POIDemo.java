

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

public class POIDemo {

    /**
     * POI 写Excel文件的模板代码
     */
    public static void writeExcel() throws IOException {

        // 1. 在内存中创建Excel文件
        XSSFWorkbook excel = new XSSFWorkbook();

        // 2. 在Excel文件中创建一个 sheet
        XSSFSheet sheet = excel.createSheet("信息");

        // 3. 在sheet中创建 行对象
        XSSFRow row = sheet.createRow(0);
        // 4. 在 行中创建 单元格对象，并写入内容
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("城市");

        // 创建新行，写入数据
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("张三");
        row.createCell(1).setCellValue("北京");

        row = sheet.createRow(2);
        row.createCell(0).setCellValue("李四");
        row.createCell(1).setCellValue("重庆");

        // 5. 通过输出流将内存中的Excel输出到硬盘里
        FileOutputStream fos = new FileOutputStream("D:\\POItest.xlsx");
        excel.write(fos);

        fos.close();
        excel.close();
    }

    /**
     * POI 读Excel文件的模板代码
     */
    public static void readExcel() throws IOException {

        // 1. 创建 Excel对象，并通过输入流装入文件
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream("D:\\POItest.xlsx"));

        // 2. 读取Excel文件中的 指定sheet
        XSSFSheet sheet = excel.getSheetAt(0);

        // 3. 获取Excel文件中，有文字的最后一行的行号
        for (Row rwo : sheet) {
            System.out.println(rwo.getCell(0) + " " + rwo.getCell(1));
        }

        excel.close();
    }

    public static void main(String[] args) throws IOException {
        writeExcel();
        readExcel();
    }
}
