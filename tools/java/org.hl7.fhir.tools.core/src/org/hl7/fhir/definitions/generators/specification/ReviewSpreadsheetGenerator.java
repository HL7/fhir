package org.hl7.fhir.definitions.generators.specification;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.StructureDefinition;

public class ReviewSpreadsheetGenerator {

  public void generate(String filename, String author, Calendar genDate, StructureDefinition profile) throws Exception {
    HSSFWorkbook workbook = new HSSFWorkbook();
        
    HSSFPalette palette = workbook.getCustomPalette();
    palette.setColorAtIndex(HSSFColor.LAVENDER.index, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0);
    
    generateReviewHeader(workbook);
    generateReviewSheet(workbook, profile);
    FileOutputStream out = new FileOutputStream(filename);
    workbook.write(out);
    out.close();
  }

  private void generateReviewSheet(HSSFWorkbook workbook, StructureDefinition profile) {
    HSSFSheet sheet = workbook.createSheet(sanitize(profile.getName()));
    sheet.setColumnWidth(0, 8000);
    sheet.setColumnWidth(3, 100);
    
    HSSFFont font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    font.setFontName("Calibri");
    HSSFCellStyle style = workbook.createCellStyle();
    style.setFont(font);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setFillBackgroundColor(new HSSFColor.LAVENDER().getIndex());
    style.setFillForegroundColor(new HSSFColor.LAVENDER().getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    
    addRow(sheet, style, "Path", "StructureDefinition", "Value Set", "Definition", "Your Comments").setRowStyle(style);

    font = workbook.createFont();
    font.setFontName("Calibri");
    style = workbook.createCellStyle();
    
    ElementDefinition ed = profile.getSnapshot().getElement().get(0);
    String path = ed.getPath();
    addRow(sheet, style, path+" : "+profile.getType(), profile.getName(), "", ed.getDefinition(), "");
    processRows(workbook, path, profile.getSnapshot().getElement(), 1, sheet, "  ");
  }

  private String sanitize(String name) {
    StringBuilder b = new StringBuilder();
    for (char c : name.toCharArray())
      if (Character.isAlphabetic(c) || Character.isDigit(c))
        b.append(c);
      else
        b.append(' ');
    return b.toString();
  }

  private int processRows(HSSFWorkbook workbook, String path, List<ElementDefinition> list, int i, HSSFSheet sheet, String indent) {
    ElementDefinition ed = list.get(i);
    HSSFFont font = workbook.createFont();
    font.setFontName("Calibri");
    HSSFCellStyle style = workbook.createCellStyle();
    style.setFont(font);
    
    while (i < list.size() && ed.getPath().startsWith(path+".")) {
      HSSFRow row = sheet.createRow(sheet.getPhysicalNumberOfRows());
      int c = 0;
      HSSFRichTextString richString;
      if (ed.getType().size() == 0) {
        richString = new HSSFRichTextString(indent+ed.getPath().substring(path.length()+1)+" ["+describeCardinality(ed)+"]");
      } else if (ed.getType().size() == 1) {
        richString = new HSSFRichTextString(indent+ed.getPath().substring(path.length()+1)+" : "+ed.getType().get(0).getCode()+" ["+describeCardinality(ed)+"]");
        HSSFFont fontBlue = workbook.createFont();
        fontBlue.setFontName("Calibri");
        fontBlue.setColor(new HSSFColor.DARK_BLUE().getIndex());
        richString.applyFont(indent.length()+ed.getPath().length() - (path.length()+1), richString.length()- describeCardinality(ed).length()-3, fontBlue);
      } else { 
        richString = new HSSFRichTextString(indent+ed.getPath().substring(path.length()+1)+" : * ["+describeCardinality(ed)+"]");
      }

      HSSFCell cell = row.createCell(c++);
      cell.setCellStyle(style);
      cell.setCellValue(richString);
      
      
      if (ed.getType().size() == 0) {
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue(ed.getName());
        
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue("");
        i++;
        if (i < list.size())
          i = processRows(workbook, ed.getPath(), list, i, sheet, indent+"  ");
      } else if (ed.getType().size() == 1) {
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue(ed.getType().get(0).getProfile());
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue(describeBinding(ed));
        i++;
      } else {
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue(ed.getName());
        cell = row.createCell(c++);
        cell.setCellStyle(style);
        cell.setCellValue(describeBinding(ed));
        i++;
      }
      cell = row.createCell(c++);
      cell.setCellStyle(style);
      cell = row.createCell(c++);
      cell.setCellStyle(style);
      cell.setCellValue("");
      if (i < list.size())
        ed = list.get(i);
    }
    return i;
    
  }

  private String describeBinding(ElementDefinition def) {
    if (!def.hasBinding())
      return "";
    return def.getBinding().getName();
  }

  private String describeCardinality(ElementDefinition d) {
    if (d.getMax() == null && d.getMinElement() == null)
      return "";
    else if (d.getMax() == null)
      return Integer.toString(d.getMin()) + "..?";
    else
      return Integer.toString(d.getMin()) + ".." + d.getMax();
  }

  private void generateReviewHeader(HSSFWorkbook workbook) {
    HSSFSheet sheet = workbook.createSheet("Review Details");
    HSSFFont font = workbook.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    HSSFCellStyle style = workbook.createCellStyle();
    style.setFont(font);
    
    addRow(sheet, null, "Name:", "", "The name of the person filling out this sheet").getCell(0).setCellStyle(style);
    addRow(sheet, null, "Email:", "", "The email address to contact about these comments").getCell(0).setCellStyle(style);
    addRow(sheet, null, "Member:", "", "The name of the organizational member these comments represent").getCell(0).setCellStyle(style);
    addRow(sheet, null, "Date:", "", "The date these comments were made").getCell(0).setCellStyle(style);
    addRow(sheet, null, "Version:", "", "The FHIR Version for these comments").getCell(0).setCellStyle(style);
  }

  private HSSFRow addRow(HSSFSheet sheet, HSSFCellStyle style, String... cells) {
    HSSFRow row = sheet.createRow(sheet.getPhysicalNumberOfRows());
    for (int i = 0; i < cells.length; i++) {
      HSSFCell cell = row.createCell(i);
      cell.setCellValue(cells[i]);
      if (style != null)
        cell.setCellStyle(style);
    }
    return row; 
  }
}
