package com.example.demo.service;

import com.example.demo.providerdata.ProviderAProductData;
import com.example.demo.providerdata.ProviderBProductData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProviderServiceHelper {




    public static String loadResponseFromFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath.replace("classpath:", ""));
        byte[] responseBytes = resource.getInputStream().readAllBytes();
        return new String(responseBytes);
    }



}
