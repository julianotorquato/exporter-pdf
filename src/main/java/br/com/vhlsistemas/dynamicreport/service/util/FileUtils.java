package br.com.vhlsistemas.dynamicreport.service.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {



    public static final String LINE_BREAK = "\r\n";
    public static final String TXT = ".txt";


    public static File createFile(String path, String nameFile, String body) throws Exception {
        return createFile(path, nameFile, body.getBytes("ISO-8859-1"));
    }

    public static File createFile(String path, String nameFile, byte[] bodyBytes) throws Exception {
        File arquivo = getFile(path, nameFile);
        return createFile(arquivo, bodyBytes);
    }

    public static File getFile(String path, String nameFile) throws Exception {
        File pathBase = new File(path);
        if (!pathBase.exists()) {
            pathBase.mkdirs();
        }
        return new File(pathBase, nameFile);
    }

    public static File createFile(File file, byte[] dadosEmBytes) throws Exception {
        createDirBase(file);
        file.createNewFile();
        FileOutputStream stream = new FileOutputStream(file, false);
        try {
            stream.write(dadosEmBytes);
            stream.flush();
            stream.close();
        } finally {
            stream.close();
        }
        return file;
    }

    public static void createDirBase(File file) throws Exception {
        if (!file.getParentFile().exists()) {
            org.apache.commons.io.FileUtils.forceMkdir(file.getParentFile());
        }
    }

    public static ByteArrayOutputStream zipFiles(Map<String, File> zipEntries) {
        return zipFiles(new ByteArrayOutputStream(), zipEntries);
    }

    public static ByteArrayOutputStream zipFiles(ByteArrayOutputStream baos, Map<String, File> zipEntries) {
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (Map.Entry<String, File> e : zipEntries.entrySet()) {
                ZipEntry entry = new ZipEntry(e.getKey());
                zos.putNextEntry(entry);
                try {
                    zos.write(Files.readAllBytes(Paths.get(e.getValue().getAbsolutePath())));
                } catch (NoSuchFileException e1) {
                    e1.printStackTrace();
                }
                zos.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }


    public static void writeFile(String path, String nameFile, String body, boolean append) throws Exception {
        File arquivo = getFile(path, nameFile);
        writeFile(arquivo, body, append);
    }

    public static void writeFile(File file, String body, boolean append) throws Exception {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter out = new FileWriter(file, append);
        out.write(body);
        out.flush();
        out.close();
    }
}
