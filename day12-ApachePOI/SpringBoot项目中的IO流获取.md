### 常规的输入流：
    
`FileInputStream fis = new FileInputStream(path);`


### 但实际项目中

- 项目打包后的文件路径，将会可能会发生改变
- 如果用常规的输入流创建方式，文件的获取就会依赖绝对路径
- 为了让文件获取更具有灵活性：
  - **可以将文件放到`resouce`目录，然后通过反射获取**

`InputStream is = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");`

-------------------

### 常规的输出流

`FileOutputSteam fos = new FileOutputStream(path)`


### 但实际项目中

- 通常文件的输出都是通过浏览器下载
- 所以实际上文件是先被输出到浏览器，然后再由浏览器本身提供下载功能
- 为了让从浏览器获取输出流对象：
  - **可以在需要输出流的方法处写上`response对象`的形参**

`public void exportBussinessData( HttpServletResponse response ){...}`

> 随后在方法中调用 `response.getOutputStream()`，就可以获取输出流对象
> 输出给浏览器后，浏览器会弹出下载框，即可下载