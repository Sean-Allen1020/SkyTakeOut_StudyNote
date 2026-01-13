import pojo.Student;
import pojo.Teacher;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.file.Files.newBufferedWriter;

public class ReflectionDemo1 {
    public static void main(String[] args) throws IllegalAccessException, IOException {

        Student student = new Student("Jack", 16, "male", 172.0, "足球");
        Teacher teacher = new Teacher("Marry", 10000.0);

        savaAsJson(teacher);
        savaAsJson(student);
    }
    //nio流方法，用于自动生成目录以及文件，以及文件预设
    public static BufferedWriter openJsonWriter(String fileName) throws IOException {
        Path dir = Paths.get("src\\json");  // 对应：src/json
        Files.createDirectories(dir);           // 自动创建目录（如果不存在）
        Path file = dir.resolve(fileName);      // 创建文件 src/json/xxx.json
        //nio流实例创建，比io流简洁
        return newBufferedWriter(
                file,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING    // 如果文件已经存在，就把文件内容先清空
        );
    }
    //通过反射获取实体类的属性，以及值，生成json文件
    public static void savaAsJson(Object o) throws IllegalAccessException, IOException {
        //获取类名
        String className = o.getClass().getSimpleName();
        //获取成员变量
        Field[] fields = o.getClass().getDeclaredFields();
        //创建IO流
        BufferedWriter bw = openJsonWriter(className + ".json");

        bw.write("{");
        bw.newLine();
        for(int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            //获取成员变量名
            String name = fields[i].getName();
            //获取成员变量值
            Object value = fields[i].get(o);
            //写入属性
            if (fields[i].getType().equals(String.class)) {
                bw.write("\t\"" + name + "\" : \"" + value + "\"");
            } else {
                bw.write("\t\"" + name + "\" : " + value);
            }
            if(i != fields.length - 1) {
                bw.write(",");
            }
            bw.newLine();
        }
        bw.write("}");
        bw.close();
    }
}
