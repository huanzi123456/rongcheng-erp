package com.rongcheng.erp.utils;
  
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.RandomAccessFile;  
import java.io.Reader;  
  
public class FileSqlTransformationTool {
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {
        // TODO Auto-generated method stub  
        String fileName = "C:\\Users\\Administrator\\Desktop\\111.txt";
        
        String tableName = "location_item_stock";
        
//        insertIntoSql(tableName, fileName);
//        selectSql(tableName, fileName);
        updateSql(tableName,fileName);
        
    }  
    
    /**
     * 自动在控制台生成Mybatis的修改语句
     * 
     * <p>文件必须是数据库字段，生成后的必须是实体类对象参数
     * 
     * @param tableName 表名
     * @param fileName 文件名
     * @author 赵滨
     */
    public static void updateSql(String tableName, String fileName) {  
        
        //第一部分
        System.out.println("update\n\t"+tableName+"\nset");
        
        //第二部分
        File file = new File(fileName);  
        BufferedReader reader = null;  
        
        //是否为首字母
        Boolean isFirstLetter = true;
        
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {
                
                //如果不为空，进行操作
                if (!"".equals(tempString)) {
                    
                    //前一个数值
                    String beforeString = tempString;
                    
                    //后一个数值
                    String afterString = "";
                    
                    //拆分成单个字符
                    char[] chs = tempString.toCharArray();
                    
                    //是否为下划线
                    Boolean isUnderline = false;
                    
                    //遍历
                    for (int i = 0; i < chs.length; i++) {

                        //替换下划线
                        if (chs[i] == '_') {
                            isUnderline = true;
                        } else {
                            //如果上一个字母是下划线
                            if (isUnderline == true) {
                                afterString += String.valueOf(chs[i]).toUpperCase();
                                
                            } else {
                                afterString += chs[i];
                            }
                            isUnderline = false;
                        }
                    }
                    
                    //如果是首字母
                    if (isFirstLetter == true) {
                        System.out.print("\t"+beforeString+" = #{"+afterString+"}");
                        
                    } else {
                        //换行 输出
                        System.out.print(",\n\t"+beforeString+" = #{"+afterString+"}");
                    }
                        
                    
                    //使用首字母之后
                    isFirstLetter = false;
                }
                
                
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
        
        //第三部分
        System.out.println("\nwhere\n\tid = #{id}");
    }  
    
    /**
     * 自动在控制台生成Mybatis的查询语句
     * 
     * <p>文件必须是数据库字段，生成后的必须是实体类对象参数
     * 
     * @param tableName 表名
     * @param fileName 文件名
     * @author 赵滨
     */
    public static void selectSql(String tableName, String fileName) {  
        
        //第一部分
        System.out.println("select");
        
        //第二部分
        File file = new File(fileName);  
        BufferedReader reader = null;  
        
        //是否为首字母
        Boolean isFirstLetter = true;
        
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {
                
                //如果不为空，进行操作
                if (!"".equals(tempString)) {
                    
                    //前一个数值
                    String beforeString = tempString;
                    
                    //后一个数值
                    String afterString = "";
                    
                    //拆分成单个字符
                    char[] chs = tempString.toCharArray();
                    
                    //是否为下划线
                    Boolean isUnderline = false;
                    
                    //遍历
                    for (int i = 0; i < chs.length; i++) {

                        //替换下划线
                        if (chs[i] == '_') {
                            isUnderline = true;
                        } else {
                            //如果上一个字母是下划线
                            if (isUnderline == true) {
                                afterString += String.valueOf(chs[i]).toUpperCase();
                                
                            } else {
                                afterString += chs[i];
                            }
                            isUnderline = false;
                        }
                    }
                    
                    //如果两个值相同
                    if (beforeString.equals(afterString)) {
                        //如果是首字母
                        if (isFirstLetter == true) {
                            System.out.print("\t"+beforeString);
                            
                        } else {
                            //换行 输出
                            System.out.print(",\n\t"+beforeString);
                        }
                        
                    } else {
                        //如果是首字母
                        if (isFirstLetter == true) {
                            System.out.print("\t"+beforeString+" as "+afterString);
                            
                        } else {
                            //换行 输出
                            System.out.print(",\n\t"+beforeString+" as "+afterString);
                        }
                        
                    }
                    
                    //使用首字母之后
                    isFirstLetter = false;
                }
                
                
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
        
        //第三部分
        System.out.println("\nfrom\n\t"+tableName);
    }  
    
    
    /**
     * 自动在控制台生成Mybatis的插入语句
     * 
     * <p>文件必须是数据库字段，生成后的必须是实体类对象参数
     * 
     * @param tableName 表名
     * @param fileName 文件名
     * @author 赵滨
     */
    public static void insertIntoSql(String tableName, String fileName) {
        
        //第一部分
        System.out.println("insert into "+tableName+"(");
        
        File file = new File(fileName);
        Reader reader = null;  
        
        //是否为换行符
        Boolean isNewlines = false;
        
        //是否为首字母
        Boolean isFirstLetter = true;
        
        //第二部分
        try {  
            // 一次读一个字符  
            reader = new InputStreamReader(new FileInputStream(file)); 
            
            int tempchar;  
            while ((tempchar = reader.read()) != -1) {  
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。  
                // 但如果这两个字符分开显示时，会换两次行。  
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。  
                if (((char) tempchar) != '\r') {  
                    
                    //如果这一次是换行符
                    if (((char) tempchar) == '\n') {
                        //记录为换行符
                        isNewlines = true;
                        
                    } else {
                        //如果上一次是换行符
                        if (isNewlines == true) {
                            
                            //如果是首字母
                            if (isFirstLetter == true) {
                                System.out.print("\t"+(char) tempchar);
                                
                            } else {
                                //换行 输出
                                System.out.print(",\n\t"+(char) tempchar);
                            }
                            
                        } else {
                            
                            //如果是首字母
                            if (isFirstLetter == true) {
                                System.out.print("\t"+(char) tempchar);
                                
                            } else {
                                System.out.print((char) tempchar);
                            }
                            
                            
                        }
                        //记录不为换行符
                        isNewlines = false;
                        
                        //不是首字母了
                        isFirstLetter = false;
                    }
                    
                }  
            }  
            reader.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        
        //是否为换行符
        isNewlines = false;
        
        //是否为首字母
        isFirstLetter = true;
        
        //第三部分
        System.out.println(")\nvalues(");
        
        //是否为下划线
        Boolean isUnderline = false;
        
        //把content_label 转成 content_label,
        try {  
            // 一次读一个字符  
            reader = new InputStreamReader(new FileInputStream(file));  
            int tempchar;  
            while ((tempchar = reader.read()) != -1) {  
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。  
                // 但如果这两个字符分开显示时，会换两次行。  
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。  
                if (((char) tempchar) != '\r') {
                    
                    //如果这一次是换行符
                    if (((char) tempchar) == '\n') {
                        //记录为换行符
                        isNewlines = true;
                        
                    } else {
                        //如果上一次是换行符
                        if (isNewlines == true) {
                            
                            //如果是首字母
                            if (isFirstLetter == true) {
                                System.out.print("\t#{"+(char) tempchar);
                                
                            } else {// #{carrierId},
                                //换行 输出
                                System.out.print("},\n\t#{"+(char) tempchar);
                            }
                            
                        } else {
                            //替换下划线
                            if (((char) tempchar) == '_') {
                                System.out.print("");
                                isUnderline = true;
                            } else {
                           
                                //如果是首字母
                                if (isFirstLetter == true) {
                                    System.out.print("\t#{"+(char) tempchar);
                                    
                                //如果上一个字母是下划线
                                }else if (isUnderline == true) {
                                    System.out.print(String.valueOf((char) tempchar).toUpperCase());  
                                    
                                } else {
                                    System.out.print((char) tempchar);  
                                }
                                
                                isUnderline = false;
                            }
                            
                        }
                        //记录不为换行符
                        isNewlines = false;
                        
                        //不是首字母了
                        isFirstLetter = false;
                    }
                    
                }  
            }  
            reader.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        //结尾
        System.out.println("})");
    }
    
}