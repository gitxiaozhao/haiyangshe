package com.rainier.util;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 导出Excel公共方法
 */
public class ExportExcel {

    // 显示的导出表的标题
    private String title;
    // 导出表的列名
    private String[] rowName;

    private List<Object[]> dataList = new ArrayList<Object[]>();

    HttpServletResponse response;

    // 构造方法，传入要导出的数据
    public ExportExcel(String title, String[] rowName, List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }

    /*
     * 导出数据
     */
    public void export(OutputStream out) throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
            HSSFSheet sheet = workbook.createSheet(title); // 创建工作表

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            // sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面 - 可扩展】
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook); // 单元格样式对象

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0,
                    (rowName.length - 1)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)

            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text); // 设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
            }

            // 将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {

                Object[] obj = dataList.get(i);// 遍历每个对象
                HSSFRow row = sheet.createRow(i + 3);// 创建所需的行数

                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null; // 设置单元格的数据类型
                    if (j == 0) {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i + 1);
                    } else {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                        if (!"".equals(obj[j]) && obj[j] != null) {
                            cell.setCellValue(obj[j].toString()); // 设置单元格的值
                        }
                    }
                    cell.setCellStyle(style); // 设置单元格样式
                }
            }
            // 让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    // 当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue()
                                    .getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * 列头单元格样式
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }


    /**
     22      * 为Excel打上水印工具函数
     23      * 请自行确保参数值，以保证水印图片之间不会覆盖。
     24      * 在计算水印的位置的时候，并没有考虑到单元格合并的情况，请注意
     25      * @param wb       Excel Workbook
     26      * @param sheet    需要打水印的Excel
     27      * @param waterRemarkPath  水印地址，classPath，目前只支持png格式的图片，
     28      *                         因为非png格式的图片打到Excel上后可能会有图片变红的问题，且不容易做出透明效果。
     29      *                         同时请注意传入的地址格式，应该为类似："\\excelTemplate\\test.png"
     30      * @param startXCol  水印起始列
     31      * @param startYRow  水印起始行
     32      * @param betweenXCol 水印横向之间间隔多少列
     33      * @param betweenYRow 水印纵向之间间隔多少行
     34      * @param XCount 横向共有水印多少个
     35      * @param YCount 纵向共有水印多少个
     36      * @param waterRemarkWidth 水印图片宽度为多少列
     37      * @param waterRemarkHeight 水印图片高度为多少行
     38      * @throws IOException
     39      */
     public static void putWaterRemarkToExcel(Workbook wb, Sheet sheet, String waterRemarkPath, int startXCol, int startYRow,
             int betweenXCol, int betweenYRow, int XCount, int YCount,
             int waterRemarkWidth, int waterRemarkHeight) throws IOException{

                 //校验传入的水印图片格式
                 if(!waterRemarkPath.endsWith("png") && !waterRemarkPath.endsWith("PNG")){
                         throw new RuntimeException("向Excel上面打印水印，目前支持png格式的图片。");
                     }

                 //加载图片
                 ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                InputStream imageIn = new FileInputStream(waterRemarkPath);
         if(null == imageIn || imageIn.available() < 1){
                         throw new RuntimeException("向Excel上面打印水印，读取水印图片失败(1)。");
                     }
                 BufferedImage bufferImg = ImageIO.read(imageIn);
                 if(null == bufferImg) {
                         throw new RuntimeException("向Excel上面打印水印，读取水印图片失败(2)。");
                     }
                 ImageIO.write(bufferImg,"png",byteArrayOut);

                 //开始打水印
                 Drawing drawing = sheet.createDrawingPatriarch();

                 //按照共需打印多少行水印进行循环
                 for(int yCount=0; yCount<YCount; yCount++){
                         //按照每行需要打印多少个水印进行循环
                         for(int xCount=0; xCount<XCount; xCount++){
                                 //创建水印图片位置
                                 int xIndexInteger = startXCol + (xCount * waterRemarkWidth) + (xCount * betweenXCol);
                                 int yIndexInteger = startYRow + (yCount * waterRemarkHeight) + (yCount * betweenYRow);

                                 /*
73                  * 参数定义：
74                  * 第一个参数是（x轴的开始节点）；
75                  * 第二个参数是（是y轴的开始节点）；
76                  * 第三个参数是（是x轴的结束节点）；
77                  * 第四个参数是（是y轴的结束节点）；
78                  * 第五个参数是（是从Excel的第几列开始插入图片，从0开始计数）；
79                  * 第六个参数是（是从excel的第几行开始插入图片，从0开始计数）；
80                  * 第七个参数是（图片宽度，共多少列）；
81                  * 第8个参数是（图片高度，共多少行）；
82                  */
                                 ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, xIndexInteger, yIndexInteger, xIndexInteger+waterRemarkWidth, yIndexInteger+waterRemarkHeight);
                                 Picture pic = drawing.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_PNG));
                                 pic.resize();
                             }
                     }
             }

}