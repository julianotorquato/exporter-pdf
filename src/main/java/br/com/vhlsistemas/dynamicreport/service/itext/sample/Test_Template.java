package br.com.vhlsistemas.dynamicreport.service.itext.sample;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class Test_Template {

    public static void main(String[] args) throws Exception{

        try{

        //Starting a new pdf document
        Document document = new Document();
        ByteArrayOutputStream os = new ByteArrayOutputStream();

//This is your new pdf doc
        PdfWriter writer = PdfWriter.getInstance(document, os);

        document.open();
        document.newPage();

//Get the file of you 1_capa.pdf, you should use try catch and then close it
//I simply to just show sistem
        FileInputStream template = new FileInputStream(ClassLoader.getSystemResource("1_capa.pdf").getFile());

//Load it into a reader
        PdfReader reader = new PdfReader(template);

//Get the page of the 1_capa.pdf you like
        PdfImportedPage page = writer.getImportedPage(reader, 1);

//Now you can add it to you report
        PdfContentByte cb = writer.getDirectContent();
        cb.addTemplate(page, 0, 0);

//Here goes code of other stuff that you add..
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
