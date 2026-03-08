## 介绍

Apache POI 是一个处理 Microsoft Office 各种文件格式的开源项目。简单来说就是，我们可以使用 POI 在 Java 程序中对 Microsoft Office 各种文件进行读写操作。

一般情况下，POI 都是用于操作 Excel 文件。

比如：

- Java 程序 ← 读 ← Excel 文件（`hello.xlsx`）
- Java 程序 → 写 → Excel 文件（`hello.xlsx`）

## Apache POI 的应用场景

- 银行网银系统导出交易明细
- 各种业务系统导出 Excel 报表
- 批量导入业务数据

-------------------------

## 使用方式

### 导入 maven 坐标
```xml
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>3.16</version>
    </dependency>

    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>3.16</version>
    </dependency>
```

### 注意！！！

- 在实际的开发中，由于Excel的格式各种各样，完全用 POI来创建一个 Excel文件是十分繁琐的
- 所以，一般会事先创建一个模板Excel，**POI需要做的仅仅只是在表格里填上数据**就可以了 
- 也就是说，POI 通常只负责写，不负责表格设计

------------------------

### 读写代码
```java
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
```