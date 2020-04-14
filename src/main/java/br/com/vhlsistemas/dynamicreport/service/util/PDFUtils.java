package br.com.vhlsistemas.dynamicreport.service.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class PDFUtils {

    public static byte[] mergePDFsBytesFiles(String pathNovoPDF, String nmArquivoNovoPDF, List<File> arquivos)
            throws Exception {
        File file = mergePDFsFiles(pathNovoPDF, nmArquivoNovoPDF, arquivos);
        return getFileBytes(file);
    }

    public static File mergePDFsFiles(String pathNovoPDF, String nmArquivoNovoPDF, List<File> arquivos)
            throws Exception {
        List<InputStream> pdfs = new ArrayList<InputStream>();
        for (File file : arquivos) {
            pdfs.add(new FileInputStream(file));
        }
        File file = new File(pathNovoPDF, nmArquivoNovoPDF);
        OutputStream output = new FileOutputStream(file);
        concatPDFs(pdfs, output, true);
        return file;
    }

    public static File mergePDFs(String pathNovoPDF, String nmArquivoNovoPDF, List<byte[]> bytesDosPdfs)
            throws Exception {
        return mergePDFs(pathNovoPDF, nmArquivoNovoPDF, bytesDosPdfs, true);
    }
    
    
    public static File mergePDFs(String pathNovoPDF, String nmArquivoNovoPDF, List<byte[]> bytesDosPdfs, boolean mostraPagina)
            throws Exception {
        List<InputStream> pdfs = new ArrayList<InputStream>();
        for (byte[] bs : bytesDosPdfs) {
            pdfs.add(new ByteArrayInputStream(bs));
        }
        File file = new File(pathNovoPDF, nmArquivoNovoPDF);
        OutputStream output = new FileOutputStream(file);
        concatPDFs(pdfs, output, mostraPagina);
        return file;
    }

    public static byte[] mergePDFsBytes(String pathNovoPDF, String nmArquivoNovoPDF, List<byte[]> bytesDosPdfs)
            throws Exception {
        return mergePDFsBytes(pathNovoPDF, nmArquivoNovoPDF, bytesDosPdfs, true);
    }
    
    
    public static byte[] mergePDFsBytes(String pathNovoPDF, String nmArquivoNovoPDF, List<byte[]> bytesDosPdfs, boolean mostraPagina)
            throws Exception {
        File file = mergePDFs(pathNovoPDF, nmArquivoNovoPDF, bytesDosPdfs, mostraPagina);
        return getFileBytes(file);
    }

    public static byte[] converterHTML2PDFBytes(String path, String nmArquivo, String html) throws Exception {
        File file = new File(path, nmArquivo);
        converterHTML2PDF(file, html);
        return getFileBytes(file);
    }

    public static File converterHTML2PDF(File pdf, String html) throws Exception {
        OutputStream os = new FileOutputStream(pdf);
        convert(html, os);
        os.close();
        return pdf;
    }

    public static File converterHTML2PDF(String path, String nmArquivo, String html) throws Exception {
        File file = new File(path, nmArquivo);
        return converterHTML2PDF(file, html);
    }

    public static byte[] convertFromHtmlToBytes(File pdf,String input) throws Exception {
        OutputStream os = new FileOutputStream(pdf);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(input, null);
        renderer.layout();
        renderer.createPDF(os);
        os.close();
        return Files.readAllBytes(pdf.toPath());
    }

    private static void convert(String input, OutputStream out) throws Exception {
        convert(new ByteArrayInputStream(input.getBytes()), out);
    }

    private static void convert(InputStream input, OutputStream out) throws Exception {
        Tidy tidy = new Tidy();
        Document doc = tidy.parseDOM(input, null);
        ITextRenderer renderer = new ITextRenderer();
//        for (File arquivo : UtilArquivo.listaArquivosDiretorio(ParserInfraestrutura.getFonts())) {
//            renderer.getFontResolver().addFont(arquivo.getAbsolutePath(), Boolean.TRUE);
//        }
        renderer.setDocument(doc, null);
        renderer.layout();
        renderer.createPDF(out);
    }

    private static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {
        com.itextpdf.text.Document document = null; 
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();
            // Cria os Readers para os pdfs
            boolean primeiraPagina = true;
            Rectangle dimensaoPrimeiraPagina = null;
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
                if(primeiraPagina){
                    dimensaoPrimeiraPagina = pdfReader.getPageSize(1);
                    primeiraPagina = false;
                }
            }
            // Cria o writer para o outputStream
            document = new com.itextpdf.text.Document(dimensaoPrimeiraPagina);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent();
            // Dados
            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();
            // varre os pdfs e adiciona no output
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
                // Cria uma nova pagina no arquivo resultante para cada pdf da lista
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    pageOfCurrentReaderPDF++;
                    document.setPageSize(pdfReader.getPageSize(pageOfCurrentReaderPDF));
                    document.newPage();
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
                    // Codigo para paginacao
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " de " + totalPages,
                                520, 5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private static byte[] getFileBytes(File file) throws Exception {
        FileInputStream fi = new FileInputStream(file);
        int size = new Long(file.length()).intValue();
        byte[] bytes = new byte[size];
        fi.read(bytes);
        fi.close();
        file.delete();
        return bytes;
    }
    
    public static int getNumeroDePaginas(byte[] bytes) throws Exception {
        PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(bytes));
        return pdfReader.getNumberOfPages();
    }

}
