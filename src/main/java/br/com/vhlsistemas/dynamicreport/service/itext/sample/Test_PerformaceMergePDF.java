package br.com.vhlsistemas.dynamicreport.service.itext.sample;

import br.com.vhlsistemas.dynamicreport.service.util.FileUtils;
import br.com.vhlsistemas.dynamicreport.service.util.PDFUtils;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Test_PerformaceMergePDF {

    static String PATH = "teste_performace";
    static String EXTENSION = ".pdf";





    public static void main(String[] args) throws Exception{
        SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        System.out.println(f2.format(Calendar.getInstance().getTime()));
        File pathFile = new File(PATH);
        pathFile.mkdir();

        List<File> files = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            String nameFile = UUID.randomUUID().toString().concat(EXTENSION);
            File file = new File(pathFile, nameFile);
            file.createNewFile();
            files.add(FileUtils.createFile(file, Test_Html2PDF.getPDFFromhtml()));
        }

        PDFUtils.mergePDFsFiles(pathFile.getAbsolutePath(), "200-pg.pdf", files);

        System.out.println(f2.format(Calendar.getInstance().getTime()));

    }

}
