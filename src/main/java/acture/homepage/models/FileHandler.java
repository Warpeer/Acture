package acture.homepage.models;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.*;

public class FileHandler {
    public MultipartFile readLocalFile(String path) throws IOException {
        // Create the file object for the local file
        File file = new File(path);

        // Get the bytes of the file
        byte[] bytes = Files.readAllBytes(file.toPath());

        // Create a new instance of MultipartFile
        return new MultipartFile() {
            @Override
            public String getContentType() {
                return "text/plain";
            }

            @Override
            public byte[] getBytes() throws IOException {
                return bytes;
            }

            @Override
            public String getName() {
                return "file";
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public long getSize() {
                return file.length();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(bytes);
            }

            @Override
            public Resource getResource() {
                return MultipartFile.super.getResource();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }

            @Override
            public void transferTo(Path dest) throws IOException, IllegalStateException {
                MultipartFile.super.transferTo(dest);
            }
        };
    }
}
