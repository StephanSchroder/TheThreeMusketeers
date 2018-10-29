/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;


import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Stephan
 */
public class Reports<T> {
    
    
    public void createReport(String fileName,List<T> data)
    {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(fileName+".pdf"));
            doc.open();
            for(Object item : data)
            {
                if (item instanceof Stock) {
                    Stock tmp = (Stock) item;
                   doc.add(new Paragraph(tmp.toString())); 
                }
            
            }
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
