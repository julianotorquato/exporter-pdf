package br.com.vhlsistemas.dynamicreport.service;

import br.com.vhlsistemas.dynamicreport.service.util.TymeleafUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class PdfGenaratorService {


    @Autowired
    private TemplateEngine templateEngine;


    public byte[] createPDFWithTemplateTymeleaf(String templateName, Map map) throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        String body = templateEngine.process("v2/body-certidao", new Context());
        body = TymeleafUtils.replaceAllFrom(body, map);
        Context context = new Context();
        context.setVariable("CORPO", body);


        String processedHtml = templateEngine.process(templateName, context);
        ByteArrayOutputStream outputStream =null;
        try {
            outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();

            System.out.println("PDF created successfully");
            return outputStream.toByteArray();
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) { /*ignore*/ }
            }
        }
    }
}
