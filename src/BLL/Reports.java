/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;


import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Stephan
 */
public class Reports<T> {
    
    
    public void createReport(String fileName,ArrayList<T> data)
    {
     Document doc = new Document( PageSize.A4,50,50,50,50);
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
            PdfWriter writer= PdfWriter.getInstance(doc, new FileOutputStream(dateFormat.format(date)+ fileName+".pdf"));
            doc.open();
            String anchorString=String .format("Created on: "+dateFormat.format(date));
            Anchor anchor= new Anchor(anchorString);
            Paragraph title = new Paragraph("Report "+ dateFormat.format(date)+ " on Stocks",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.BOLD,new CMYKColor(0,102,153,255)));
            Chapter chapter = new Chapter(title,0);
            chapter.setNumberDepth(0);
            chapter.add(anchor);
            List reportList = new List(true);
            ListItem listItem = null;
            
            for(Object item : data)
            {
                if (item instanceof Stock) {
                    listItem = new ListItem(((Stock) item).getReportFormat());
                    reportList.add(listItem);
 
                }
                chapter.add(reportList);
            
            }
            doc.add(chapter);
            doc.close();
            writer.close();
            
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
