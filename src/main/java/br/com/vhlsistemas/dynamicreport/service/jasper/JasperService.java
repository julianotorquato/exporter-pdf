package br.com.vhlsistemas.dynamicreport.service.jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class JasperService {


    private final String EXTENSION = ".pdf";

    private final Logger log = LoggerFactory.getLogger(JasperService.class);

    private JasperPrint fillReport(InputStream report, List list, Map<String, Object> parameters) {
        try {
            JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(list);
            return JasperFillManager.fillReport(report, parameters, collectionDataSource);


        } catch (JRException e) {
            e.printStackTrace();
            log.error("Error fill report " + e.getMessage());
        }
        return null;
    }

    private byte[] exportPDF(JasperPrint jasperPrint, String author, String title) {
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            ByteArrayOutputStream file = new ByteArrayOutputStream();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));

            SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
            exportConfig.setMetadataAuthor(author);
            exportConfig.setMetadataTitle(title);

            exporter.setConfiguration(exportConfig);

            exporter.exportReport();

            return file.toByteArray();

        } catch (JRException e) {
            e.printStackTrace();
            log.error("Error export report " + e.getMessage());
        }
        return null;
    }

    public byte[] generatePDF(InputStream in, String author, String title, Map<String, Object> parameters, List list) {
        JasperPrint jp = fillReport(in, list, parameters);
        return exportPDF(jp, author, title);
    }

}
