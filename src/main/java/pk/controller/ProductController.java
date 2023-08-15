package pk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pk.mapperDto.ProductDto;
import pk.service.OrderService;

@RestController
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    //refactor resource_url to application.yml
    //private static final String RESOURCE_URL = "http://172.24.0.2:8080/products";

    @Value("${application.product-service-url}")
    private String productServiceUrl;

    private RestTemplate restTemplate;

    public ProductController(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public ProductDto fetchProductById(long id) {

        return restTemplate.getForObject(productServiceUrl+"/"+String.valueOf(id), ProductDto.class);
    }
}
