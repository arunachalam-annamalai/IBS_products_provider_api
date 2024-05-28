package com.example.demo.service;

import com.example.demo.data.Product;
import com.example.demo.providerdata.ProviderConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ObjectMapper objectMapper;
    private final List<ProviderConfig> providerConfigs;

    public ProductService(ObjectMapper objectMapper, List<ProviderConfig> providerConfigs) {
        this.objectMapper = objectMapper;
        this.providerConfigs = providerConfigs;
    }

    public List<Product> getProducts() throws IOException {
        List<Product> products = new ArrayList<>();

        for (ProviderConfig providerConfig : providerConfigs) {
            String response = ProviderServiceHelper.loadResponseFromFile(providerConfig.getUrl());
            JsonNode productsNode = objectMapper.readTree(response);

            if (productsNode.isArray()) {
                for (JsonNode productNode : productsNode) {
                    try {
                        products.add(mapProviderProductToProduct(providerConfig.getMappings(), productNode));
                    } catch (Exception e) {
                        // Log the error and continue processing other products
                        System.err.println("Error mapping product: " + e.getMessage());
                    }
                }
            } else {
                System.err.println("Unexpected JSON structure: Expected an array");
            }
        }

        return products;
    }


    private Product mapProviderProductToProduct(Map<String, String> mappings, JsonNode productNode) throws Exception {
        Product product = new Product();

        if (productNode.hasNonNull(mappings.get("id"))) {
            product.setId(productNode.get(mappings.get("id")).asText());
        } else {
            throw new Exception("Missing or null field: " + mappings.get("id"));
        }

        if (productNode.hasNonNull(mappings.get("productName"))) {
            product.setProductName(productNode.get(mappings.get("productName")).asText());
        } else {
            throw new Exception("Missing or null field: " + mappings.get("productName"));
        }

        if (productNode.hasNonNull(mappings.get("description"))) {
            product.setDescription(productNode.get(mappings.get("description")).asText());
        } else {
            throw new Exception("Missing or null field: " + mappings.get("description"));
        }

        return product;
    }
}
