package br.com.vhlsistemas.dynamicreport.web.rest;


import br.com.vhlsistemas.dynamicreport.service.jasper.JasperService;
import br.com.vhlsistemas.dynamicreport.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/api/jasper")
public class JasperResource {

    private static final String PATH_REPORTS = "reports";
    private static final String PATH_JASPER = PATH_REPORTS.concat("reports/jasper/");
    private static final String PATH_IMG_REPORT = PATH_REPORTS.concat("/images/");
    private static final String REPORT_EXTENSION = ".jasper";
    private static final String AUTHOR = "SREP";
    private static final String IMG = PATH_IMG_REPORT.concat("header-certidao.png");


    @Autowired
    private JasperService jasperService;

    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPDF() throws Exception {

        final String metatitle = "Teste de certidao";
        final String jrxmlname = "certidao-v1";

        ClassLoader classLoader = this.getClass().getClassLoader();
        //URL resource = classLoader.getResource(PATH_JASPER.concat(jrxmlname.concat(REPORT_EXTENSION)));
        URL resource = classLoader.getResource("reports/jasper/layout_paisagem.jasper");

        InputStream report =  new FileInputStream(new File(resource.getFile()));
        byte[] reportByte = jasperService.generatePDF(report, AUTHOR, metatitle, getDefaultParams(), Collections.singletonList(new ArrayList()));
         return HeaderUtil.createHeader(reportByte, "1_capa.pdf");

    }

    private HashMap<String, Object> getDefaultParams() throws Exception{
        final HashMap<String, Object> params = new HashMap<>();
        //params.put("img", ClassLoader.getSystemResource("reports/images/header-certidao.png").getPath());
        params.put("img", ClassLoader.getSystemResource("reports/images/vhl.png").getPath());
        return params;
    }

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemResource(IMG).getPath());
        //System.out.println(ClassLoader.getSystemResource(PATH_IMG_REPORT.concat("certidao_page-0001.jpg")).getPath());
        System.out.println(ClassLoader.getSystemResource("reports/images/certidao_pg1.jpg").getPath());
    }

}
