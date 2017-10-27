//首先，要开启打印服务，否则会抛出异常。
//
//
//​java.awt.erp.PrinterException: Invalid name of PrintService.
//
//at sun.awt.windows.WPrinterJob.setNativePrintService(Native Method)    at sun.awt.windows.WPrinterJob.getPrintService(WPrinterJob.java:573)    at sun.erp.RasterPrinterJob.erp(RasterPrinterJob.java:1272)    at sun.erp.RasterPrinterJob.erp(RasterPrinterJob.java:1247)    at com.printDome.main(printDome.java:137)
//
// 
//
////其次，确保计算机与打印机之间连接通畅。
//
//
//
//继承（implements ）Printable​ 实现 erp（）；方法。
package com.rongcheng.erp.utils;
import java.awt.BasicStroke;

import java.awt.Color;import java.awt.Component;

import java.awt.Font;import java.awt.Graphics;import java.awt.Graphics2D;

import java.awt.print.Book;

import java.awt.print.PageFormat;import java.awt.print.Paper;

import java.awt.print.Printable;import java.awt.print.PrinterException;

import java.awt.print.PrinterJob;
import java.util.List;

public class PrintTool implements Printable {  

    // 设置 存放坐标表和数值表内容
    private List<String> printList;
    
    // 设置 图片路径
    private String imgSrc;
    
    // 设置 背景色
    private String backgroundColor;
    
    // 设置 打印颜色
    private String printColor;
    
    // 设置 字体 类型
    private String fontType;
    
    // 设置 字体 样式
    private String fontStyle;
    
    // 设置 字体 大小
    private Integer fontSize;
    
    //有参构造器
    public PrintTool(List<String> printList, String imgSrc, String backgroundColor, String printColor, String fontType,
            String fontStyle, Integer fontSize) {
        super();
        this.printList = printList;
        this.imgSrc = imgSrc;
        this.backgroundColor = backgroundColor;
        this.printColor = printColor;
        this.fontType = fontType;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
    }

    /** 
     * 一个预设方法，不要使用
     * @param graphics 指明打印的图形环境 
     * @param pageFormat 指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点） 
     * @param pageIndex 指明页号 
     * @author 赵滨
     **/  
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)            
            throws PrinterException {

        Component c = null;

        // 转换Graphics2D
        Graphics2D graphics2d = (Graphics2D) graphics;

        // 设置打印属性
        
        // 背景颜色
        if ("蓝色".equals(backgroundColor)) {
            graphics2d.setBackground(Color.blue);
        }
        
        // 打印颜色
        if ("黑色".equals(printColor)) {
            graphics2d.setColor(Color.black);
        }

        // 获取打印起点坐标
        
        double x = pageFormat.getImageableX();
        
        double y = pageFormat.getImageableY();

        // 必须从首页开始，才执行打印
        if (pageIndex == 0) {

        // 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）  
            
        // Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 、、// DialogInput 
            
        
        // 匹配 样式
        int style = Font.PLAIN;
        if ("加粗".equals(fontStyle)) {
            style = Font.BOLD;
        } else if ("倾斜".equals(fontStyle)) {
            style = Font.ITALIC;
        }
        
        Font font = new Font(fontType, style, fontSize);
        
        // 将字体样式set到 graphics2d 中
        graphics2d.setFont(font);

        // 设置打印线的属性
        
        float[] dash = {2.0f};

        // 将打印线样式set到graphics2d中
        
        // 1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量  
        
        graphics2d.setStroke(new BasicStroke(0.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,2.0f,dash,0.0f)); 
        
        // 获取字体高度

        float heigth = font.getSize2D();

        // -1- 用Graphics2D直接输出

        // 首字符的基线(右下部)位于用户空间中的 (x, y) 位置处

        // 设置打印图片    路径用 "\\" 或者"/"
        
        // Image src = Toolkit.getDefaultToolkit().getImage(imgSrc);
        
        // int img_Height=src.getHeight(c);

        // int img_width=src.getWidth(c);
      
        //打印图片位置
        
        // graphics2d.drawImage(src, (int)x, (int)y, img_width, img_Height, c);
        
        //遍历打印的集合
        for (int i = 0; i < printList.size(); i++) {
            
            //获取打印对象
            String print = printList.get(i);
            
            //分割成数组
            String[]  prints = print.split("(\\s)*,(\\s)*");
            
            //打印值
            String printContent = prints[0];
            
            //打印x点
            Float printX = Float.parseFloat(prints[1]);
            
            //x点毫米转换为像素
            printX = (float)(printX/25.4*72);
            
            //打印y点
            Float printY = Float.parseFloat(prints[2]);
            
            //y点毫米转换为像素
            printY = (float)(printY/25.4*72);
            
            graphics2d.drawString(printContent, (float)(printX+x), (float)(printY+y));
            
            // System.out.println(printContent+","+printX+","+printY);
            
        }
        
            //graphics2d.drawLine((int)x,(int)(y+1*heigth+img_Height+10),(int)x+200,(int)(y+1*heigth+img_Height+10));
                
        return PAGE_EXISTS;

        }

        return NO_SUCH_PAGE;

    }

    /**
     * 打印的静态方法
     * @param stoodPrint 是否竖打
     * @param xCoordinate 打印区域x轴
     * @param yCoordinate 打印区域y轴
     * @author 赵滨
     */
    public static void startPrint(List<String> printList, String imgSrc, String backgroundColor, String printColor, 
            String fontType, String fontStyle, Integer fontSize, Boolean stoodPrint, Integer xCoordinate, Integer yCoordinate) {
        
        // 创建一个工作文档 book  
        Book book = new Book();

        // 设置打印方式 竖打
        PageFormat format = new PageFormat();
        
        if (stoodPrint == true) {
            format.setOrientation(PageFormat.PORTRAIT);
        } else {
            format.setOrientation(PageFormat.LANDSCAPE);
        }
        

        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符
        Paper paper = new Paper();

        //把x、y参数由毫米转换为像素
        xCoordinate = (int)(xCoordinate/25.4*72);
        yCoordinate = (int)(yCoordinate/25.4*72);
        
        // 纸张大小

        //paper.setSize(590, 840);

        // A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72

        paper.setImageableArea(0, 0, xCoordinate, yCoordinate);

        format.setPaper(paper);

        //将PageFormat和 Printable 添加到书中，组成一个页面
        book.append(new PrintTool(printList, imgSrc, backgroundColor, printColor, fontType, fontStyle,
                fontSize),format);

        //获取打印服务对象

        PrinterJob job = PrinterJob.getPrinterJob();

        //设置打印类

        job.setPageable(book);

        // 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印

        // boolean a = job.printDialog();
    
        // if (a) {

        try {

            job.print();

        } catch (PrinterException e) {

            e.printStackTrace();

        }

        // }

    }

}