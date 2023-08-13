package pk.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pk.mapperDto.ProductDto;

@RestController
public class ProductController {
    private static final String RESOURCE_URL = "http://172.24.0.2:8081/products";
    private RestTemplate restTemplate;

    public ProductController(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public ProductDto fetchProductById(long id) {
        return restTemplate.getForObject(RESOURCE_URL+"/"+String.valueOf(id), ProductDto.class);
    }
}
