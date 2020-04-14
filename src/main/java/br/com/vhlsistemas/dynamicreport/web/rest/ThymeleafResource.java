package br.com.vhlsistemas.dynamicreport.web.rest;


import br.com.vhlsistemas.dynamicreport.service.PdfGenaratorService;
import br.com.vhlsistemas.dynamicreport.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the {@link Thymeleaf} entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api/thymeleaf")
public class ThymeleafResource {

    @Autowired
    private PdfGenaratorService pdfGenaratorService;

    /**
     * Gets a list of all roles.
     *
     * @return a string list of all roles.
     */
    @GetMapping("/pdf")
    public  ResponseEntity<Resource> getPDF() throws Exception {
        Map params = new HashMap();
        params.put("SELO", "Selo da certidao 2");
        params.put("NOME_USUARIO", "Juliano Torquato");
        return HeaderUtil.createHeader(pdfGenaratorService.createPDFWithTemplateTymeleaf("v2/certidao", params), "1_capa.pdf");
    }



}