package br.com.vhlsistemas.dynamicreport.web.rest;


import br.com.vhlsistemas.dynamicreport.service.itext.sample.Test_ConcatPDF;
import br.com.vhlsistemas.dynamicreport.service.itext.sample.Test_Html2PDF;
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
@RequestMapping("/api/merge")
public class MergePDfResource {

    @Autowired

    private JasperService jasperService;

    @GetMapping("/pdf")
    public ResponseEntity<Resource> getPDF() throws Exception {
         return HeaderUtil.createHeader(Test_ConcatPDF.getMergePDF(), "1_capa.pdf");
    }

    @GetMapping("/html")
    public ResponseEntity<Resource> gethtml() throws Exception {
        return HeaderUtil.createHeader(Test_Html2PDF.getPDFFromhtml(), "1_capa.pdf");
    }


}
