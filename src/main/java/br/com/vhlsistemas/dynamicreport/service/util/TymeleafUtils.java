package br.com.vhlsistemas.dynamicreport.service.util;

import org.thymeleaf.context.Context;

import java.util.Iterator;
import java.util.Map;

public class TymeleafUtils {

    public static Context fromMap(Map params){
        Context ctx = new Context();
        if (params != null) {
            Iterator itMap = params.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                ctx.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }
        return ctx;
    }


    public static String replaceAllFrom(String body, Map params){
        String regex = "\\$[\\{]([^}]*)[\\}]";
        if (params != null) {
            Iterator itMap = params.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                body = body.replaceAll(regex, pair.getValue().toString());
            }
        }
        return body;
    }


    public static void main(String[] args) {

        String body = "<div>\n" +
                "    <br/>\n" +
                "    <p style=\"text-align:center;font-size:20px\"><b abrir=\"\">CERTIDÃO DE ÔNUS E AÇÕES</b></p>\n" +
                "    <p><b abrir=\"\">CERTIFICA</b> que, em relação à área de terreno urbano, com 66,00m², situada na Rua Vila Boa, <strong>Granjas Santos Dumont</strong>, de propriedade de <b>SEBASTIÃO DE BRITO CARVALHO</b>, transcrita nesta Serventia sob n. <b>3</b> no Livro 3-AJ, fl. 247, <b abrir=\"\"> existem ônus</b>, conforme consta na <strong>Inscrição n. 1.747, Livro 4-A, fls. 241, em 25/05/1961: Reserva de Usufruto Vitalício</strong> para Jandira Ferreira Rios, brasileira, desquitada proprietária, residente e domiciliada nesta Capital, e <b>não existem ações</b>, reais ou pessoais reipersecutórias. Certidão emitida nos termos do art. 19, § 1º, da Lei n. 6.015/1973 e item 80, inciso XI, da Tabela XIV do Regimento de Custas e Emolumentos do Estado de Goiás.</p>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Emols.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">26,84</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Fundesp.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">2,68</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Fundaf.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,40</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Funesp.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">2,15</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Estado.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,81</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Fesemps:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">1,07</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Funemp.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,81</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Funcomp:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,67</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Fepadsaj.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,54</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Funproge:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,67</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Fundepeg.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,54</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Femal:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">0,40</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Taxa Jud.:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">15,14</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>ISS:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">1,34</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td class=\"titulo\">\n" +
                "                <div><span>Total:</span> <span>R$</span></div>\n" +
                "            </td>\n" +
                "            <td class=\"valor\">54,062</td>\n" +
                "            <td class=\"espaco\"></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <center>Selo digital n. <b><span class=\"text-danger-soft erro-minuta\" style=\"color: red;\">${SELO}</span></b></center>\n" +
                "    <br/>\n" +
                "    <center>Consulte o selo em: https://extrajudicial.tjgo.jus.br/selo</center>\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "    <center>Goiânia/GO, 10 de Abril de 2020</center>\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "    <center>___________________________________</center>\n" +
                "    <center><span class=\"text-danger-soft erro-minuta\" style=\"color: red;\">${NOME_USUARIO}</span></center>\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "    <p>Atenção: para fins de transmissão (compra e venda, permuta, doação etc.), essa certidão possui validade de 30 (trinta) dias, conforme estabelece o art. 1°, IV, b, do Decreto n. 93.240/1986, que regulamenta a Lei n. 7.433/1985.</p>\n" +
                "</div>\n";

        String regex = "\\$[\\{]([SELO}]*)[\\}]";

        body = body.replace(regex, "SELOOOOOOOOOOOO");


        System.out.println(body);


    }
}
