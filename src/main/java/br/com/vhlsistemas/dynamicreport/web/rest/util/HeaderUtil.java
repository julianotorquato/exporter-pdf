package br.com.vhlsistemas.dynamicreport.web.rest.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class HeaderUtil {

    public static ResponseEntity<Resource> createHeader(byte[] bytes, String fileName) throws Exception {
        String fileNameWithoutComma = Objects.nonNull(fileName) ?  fileName.replace(",","") : "";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=" + fileNameWithoutComma);

        ByteArrayResource resource = new ByteArrayResource(bytes);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
