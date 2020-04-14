package br.com.vhlsistemas.dynamicreport.service.itext.sample;

import br.com.vhlsistemas.dynamicreport.service.util.FileUtils;
import br.com.vhlsistemas.dynamicreport.service.util.PDFUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Test_Html2PDF {

    public static void main(String[] args) throws Exception{
//        System.out.println(ClassLoader.getSystemResource("pdf_result.pdf").toURI().getPath());
//        System.out.println(ClassLoader.getSystemResource("pdf_result.pdf").getPath());
//        System.out.println(getPDFFromhtml().length+"");

        System.currentTimeMillis();

        String path = "storage/v1";
        for (int i = 0; i < 100; i++) {
            String nameFile = UUID.randomUUID().toString().concat(".pdf");
            File file = new File(path.concat("/"+nameFile));
            FileUtils.createFile(file, getPDFFromhtml());//foi par ao target
        }

        File fileFinal = new File(ClassLoader.getSystemResource("pdf_result.pdf").toURI().getPath());
        FileUtils.createFile(fileFinal, getMergePDF(path));//foi par ao target

        System.currentTimeMillis();
    }
    public static byte[] getMergePDF(String path) throws Exception{
        try{
            ClassPathResource classPath = new ClassPathResource(path);
            File folder  = classPath.getFile();
            File[] listOfFiles = folder.listFiles();
            List<byte[]> bytesDosPdfs = new ArrayList<>();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    bytesDosPdfs.add(Files.readAllBytes(file.toPath()));
                }
            }

            String pathName = classPath.getURI().getPath();
            String nomeArquivo = "MERGE_FINAL.pdf";
            return PDFUtils.mergePDFsBytes(pathName, nomeArquivo,  bytesDosPdfs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] getPDFFromhtml() throws Exception{
        File file = new File(ClassLoader.getSystemResource("pdf_result.pdf").getPath());
        return PDFUtils.convertFromHtmlToBytes(file, getHtmlWithImg());
    }


    private static String getHtmlWithImg(){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" th:lang=\"${#locale.language}\" lang=\"en\">\n" +
                "<head>\n" +
                "    <style>\n" +
                "        p {\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            margin: 1cm 0;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            font-size: 14px;\n" +
                "            border: 1px solid transparent;\n" +
                "        }\n" +
                "\n" +
                "        td.titulo {\n" +
                "            width: 30%;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        td.titulo div:before {\n" +
                "            float: left;\n" +
                "            width: 0;\n" +
                "            white-space: nowrap;\n" +
                "            content: \".\" \".\"\n" +
                "        }\n" +
                "\n" +
                "        td.titulo span:first-child {\n" +
                "            padding-right: 0.25em;\n" +
                "            background: white\n" +
                "        }\n" +
                "\n" +
                "        td.titulo span + span {\n" +
                "            float: right;\n" +
                "            padding-left: 0.25em;\n" +
                "            background: white\n" +
                "        }\n" +
                "\n" +
                "        td.espaco {\n" +
                "            width: 10%;\n" +
                "        }\n" +
                "\n" +
                "        td.valor {\n" +
                "            width: 15%;\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "        img.imagem-ficha {\n" +
                "            height: 19cm;\n" +
                "            width: auto;\n" +
                "            margin: 1cm auto 0;\n" +
                "            text-align: center;\n" +
                "            page-break-after: always;\n" +
                "        }\n" +
                "\n" +
                "        .quebra-linha {}\n" +
                "\n" +
                "        @page {\n" +
                "            @bottom-right {\n" +
                "                content: \"Page \" counter(page) \" of \" counter(pages);\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "    <title th:text=\"#{email.activation.title}\">JHipster activation</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <link rel=\"shortcut icon\" th:href=\"@{|${baseUrl}/favicon.ico|}\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\"https://www.w3schools.com/images/w3schools_green.jpg\" alt=\"W3Schools.com\" style=\"width:240px;height:240px;\"/> "+
                "<br/>\n" +
                "<br/>\n" +
                "<div>\n" +
                "<br/>\n" +
                "<p style=\"text-align:center;font-size:20px\"><b abrir=\"\">CERTIDÃO DE ÔNUS E AÇÕES</b></p>\n" +
                "<p><b abrir=\"\">CERTIFICA</b> que, em relação à área de terreno urbano, com 66,00m², situada na Rua Vila Boa, <strong>Granjas Santos Dumont</strong>, de propriedade de <b>SEBASTIÃO DE BRITO CARVALHO</b>, transcrita nesta Serventia sob n. <b>3</b> no Livro 3-AJ, fl. 247, <b abrir=\"\"> existem ônus</b>, conforme consta na <strong>Inscrição n. 1.747, Livro 4-A, fls. 241, em 25/05/1961: Reserva de Usufruto Vitalício</strong> para Jandira Ferreira Rios, brasileira, desquitada proprietária, residente e domiciliada nesta Capital, e <b>não existem ações</b>, reais ou pessoais reipersecutórias. Certidão emitida nos termos do art. 19, § 1º, da Lei n. 6.015/1973 e item 80, inciso XI, da Tabela XIV do Regimento de Custas e Emolumentos do Estado de Goiás.</p>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Emols.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">26,84</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Fundesp.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">2,68</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Fundaf.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,40</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Funesp.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">2,15</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Estado.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,81</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Fesemps:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">1,07</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Funemp.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,81</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Funcomp:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,67</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Fepadsaj.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,54</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Funproge:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,67</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Fundepeg.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,54</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Femal:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">0,40</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Taxa Jud.:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">15,14</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>ISS:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">1,34</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td class=\"titulo\">\n" +
                "            <div><span>Total:</span> <span>R$</span></div>\n" +
                "        </td>\n" +
                "        <td class=\"valor\">54,062</td>\n" +
                "        <td class=\"espaco\"></td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<center>Selo digital n. <b><span class=\"text-danger-soft erro-minuta\" style=\"color: red;\">$(selo)</span></b></center>\n" +
                "<br/>\n" +
                "<center>Consulte o selo em: https://extrajudicial.tjgo.jus.br/selo</center>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<center>Goiânia/GO, 10 de Abril de 2020</center>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<center>___________________________________</center>\n" +
                "<center><span class=\"text-danger-soft erro-minuta\" style=\"color: red;\">$(usuario.nome)</span></center>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<p>Atenção: para fins de transmissão (compra e venda, permuta, doação etc.), essa certidão possui validade de 30 (trinta) dias, conforme estabelece o art. 1°, IV, b, do Decreto n. 93.240/1986, que regulamenta a Lei n. 7.433/1985.</p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private static String getHTML(){

        return "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <style>\n" +
                "\n" +
                "        @page{\n" +
                "\n" +
                "            @bottom-left {\n" +
                "                content: element(footer);\n" +
                "                vertical-align: top;\n" +
                "                padding-top: 10px;\n" +
                "                /*      border: solid red;    */\n" +
                "            }\n" +
                "\n" +
                "            @top-right {\n" +
                "                content: element(header);\n" +
                "                vertical-align: bottom;\n" +
                "                padding-bottom: 10px;\n" +
                "                /*          border: solid green;   */\n" +
                "            }\n" +
                "\n" +
                "            size: A4 portrait;\n" +
                "            margin-top:5.5cm;\n" +
                "            margin-left:3cm;\n" +
                "            margin-right:2cm;\n" +
                "            margin-bottom:3.7cm;\n" +
                "        }\n" +
                "\n" +
                "        div.header {\n" +
                "            display: block;\n" +
                "            position: running(header);\n" +
                "            /*border-bottom: 1px solid black;*/\n" +
                "        }\n" +
                "\n" +
                "        div.footer {\n" +
                "            margin-top: 0.5cm;\n" +
                "            display: block;\n" +
                "            position: running(footer);\n" +
                "            border-top: 1px solid black;\n" +
                "        }\n" +
                "\n" +
                "        div.content {\n" +
                "            /*  border: solid purple;  */\n" +
                "            display: block;\n" +
                "            width: 15.4cm;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        #pagenumber:before {\n" +
                "            content: counter(page);\n" +
                "        }\n" +
                "\n" +
                "        #pagecount:before {\n" +
                "            content: counter(pages);\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            margin: 1cm 0;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            font-size: 14px;\n" +
                "            border: 1px solid transparent;\n" +
                "        }\n" +
                "\n" +
                "        td.titulo {\n" +
                "            width: 30%;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        td.titulo div:before {\n" +
                "            float: left;\n" +
                "            width: 0;\n" +
                "            white-space: nowrap;\n" +
                "            content: \".\" \".\"\n" +
                "        }\n" +
                "\n" +
                "        td.titulo span:first-child {\n" +
                "            padding-right: 0.25em;\n" +
                "            background: white\n" +
                "        }\n" +
                "\n" +
                "        td.titulo span + span {\n" +
                "            float: right;\n" +
                "            padding-left: 0.25em;\n" +
                "            background: white\n" +
                "        }\n" +
                "\n" +
                "        td.espaco {\n" +
                "            width: 10%;\n" +
                "        }\n" +
                "\n" +
                "        td.valor {\n" +
                "            width: 15%;\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "        img.imagem-ficha {\n" +
                "            height: 19cm;\n" +
                "            width: auto;\n" +
                "            margin: 1cm auto 0;\n" +
                "            text-align: center;\n" +
                "            page-break-after: always;\n" +
                "        }\n" +
                "\n" +
                "        .quebra-linha {}\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"header\">\n" +
                "    <img src=\"../../reports/images/header-certidao.png\" width=\"1000\"\n" +
                "         th:src=\"@{../../reports/images/header-certidao.png}\"/>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"footer\" style=\"font-family: Courier New, monospace\">\n" +
                "    <div>Pedido n. 26, de 06/04/2020</div>\n" +
                "    <p>Certidão emitida em 10/04/2020 às 02:07:48</p>\n" +
                "    <p>Página <span id=\"pagenumber\"></span> de <span id=\"pagecount\"></span></p>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"content\">\n" +
                "    <div>\n" +
                "        <br/>\n" +
                "        <p style=\"text-align:center;font-size:20px\"><b abrir=\"\">CERTIDÃO DE ÔNUS E AÇÕES</b></p>\n" +
                "        <p><b abrir=\"\">CERTIFICA</b> que, em relação à área de terreno urbano, com 66,00m², situada na Rua Vila Boa, <strong>Granjas Santos Dumont</strong>, de propriedade de <b>SEBASTIÃO DE BRITO CARVALHO</b>, transcrita nesta Serventia sob n. <b>3</b> no Livro 3-AJ, fl. 247, <b abrir=\"\"> existem ônus</b>, conforme consta na <strong>Inscrição n. 1.747, Livro 4-A, fls. 241, em 25/05/1961: Reserva de Usufruto Vitalício</strong> para Jandira Ferreira Rios, brasileira, desquitada proprietária, residente e domiciliada nesta Capital, e <b>não existem ações</b>, reais ou pessoais reipersecutórias. Certidão emitida nos termos do art. 19, § 1º, da Lei n. 6.015/1973 e item 80, inciso XI, da Tabela XIV do Regimento de Custas e Emolumentos do Estado de Goiás.</p>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Emols.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">26,84</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Fundesp.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">2,68</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Fundaf.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,40</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Funesp.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">2,15</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Estado.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,81</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Fesemps:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">1,07</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Funemp.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,81</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Funcomp:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,67</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Fepadsaj.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,54</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Funproge:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,67</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Fundepeg.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,54</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Femal:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">0,40</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Taxa Jud.:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">15,14</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>ISS:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">1,34</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titulo\">\n" +
                "                    <div><span>Total:</span> <span>R$</span></div>\n" +
                "                </td>\n" +
                "                <td class=\"valor\">54,062</td>\n" +
                "                <td class=\"espaco\"></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <center>Selo digital n. <b><span class=\"text-danger-soft erro-minuta\" style=\"color: red;\">${SELO}</span></b></center>\n" +
                "        <br/>\n" +
                "        <center>Consulte o selo em: https://extrajudicial.tjgo.jus.br/selo</center>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <center>Goiânia/GO, 10 de Abril de 2020</center>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <center>___________________________________</center>\n" +
                "        <center><span class=\"text-danger-soft erro-minuta\" style=\"color: red;\">${NOME_USUARIO}</span></center>\n" +
                "        <br/>\n" +
                "        <br/>\n" +
                "        <p>Atenção: para fins de transmissão (compra e venda, permuta, doação etc.), essa certidão possui validade de 30 (trinta) dias, conforme estabelece o art. 1°, IV, b, do Decreto n. 93.240/1986, que regulamenta a Lei n. 7.433/1985.</p>\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

    }

}


