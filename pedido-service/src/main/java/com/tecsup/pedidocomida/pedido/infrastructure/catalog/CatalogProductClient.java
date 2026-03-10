package com.tecsup.pedidocomida.pedido.infrastructure.catalog;

import com.tecsup.pedidocomida.pedido.application.ProductCatalogPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CatalogProductClient implements ProductCatalogPort {

    private final RestTemplate restTemplate;
    private final String catalogBaseUrl;

    public CatalogProductClient(RestTemplate restTemplate,
                                @Value("${integration.catalog.base-url:http://catalogo-service:8082}") String catalogBaseUrl) {
        this.restTemplate = restTemplate;
        this.catalogBaseUrl = catalogBaseUrl;
    }

    @Override
    public Map<Long, BigDecimal> getProductPricesById(Set<Long> productIds, String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);

        ResponseEntity<List<CatalogProductResponse>> response = restTemplate.exchange(
                catalogBaseUrl + "/api/products",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );

        List<CatalogProductResponse> products = response.getBody();
        if (products == null) {
            return Collections.emptyMap();
        }

        return products.stream()
                .filter(p -> p.id() != null && p.price() != null && productIds.contains(p.id()))
                .collect(Collectors.toMap(CatalogProductResponse::id, CatalogProductResponse::price, (a, b) -> a));
    }
}
