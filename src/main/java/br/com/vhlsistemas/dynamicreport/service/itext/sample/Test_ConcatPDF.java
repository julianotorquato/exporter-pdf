package br.com.vhlsistemas.dynamicreport.service.itext.sample;

import br.com.vhlsistemas.dynamicreport.service.util.PDFUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Test_ConcatPDF {

    public static byte[] getMergePDF(){
        try{
            ClassPathResource classPath = new ClassPathResource("storage/novo");
            File folder  = classPath.getFile();
            File[] listOfFiles = folder.listFiles();
            List<byte[]> bytesDosPdfs = new ArrayList<>();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    bytesDosPdfs.add(Files.readAllBytes(file.toPath()));
                }
            }

            String path = classPath.getURI().getPath();
            String nomeArquivo = "MERGE_FINAL.pdf";
            return PDFUtils.mergePDFsBytes(path, nomeArquivo,  bytesDosPdfs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static File obterArquivo(String localNome) {
        File arquivo = new File(localNome);
        return arquivo;
    }


    public static void criarArquivo(String localNome, byte[] dadosEmBytes) throws Exception {
        File arquivo = obterArquivo(localNome);
        criarArquivo(arquivo, dadosEmBytes);
    }

    public static File criarArquivo(File arquivo, byte[] dadosEmBytes) throws Exception {
        if (!arquivo.getParentFile().exists()) {
            FileUtils.forceMkdir(arquivo.getParentFile());
        }
        FileOutputStream stream = new FileOutputStream(arquivo);
        stream.write(dadosEmBytes);
        stream.flush();
        stream.close();
        arquivo.createNewFile();
        return arquivo;
    }
}
