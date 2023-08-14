package pk.service;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pk.controller.ProductController;
import pk.entity.Order;
import pk.exception.EntityNotFoundException;
import pk.mapperDto.OrderMapper;
import pk.mapperDto.ProductDto;
import pk.modelDto.OrderDto;
import pk.repository.OrderJpaRepository;
import pk.springboot.Application;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderJpaRepository orderJpaRepository;

    private ProductController productController;

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Autowired
    OrderServiceImpl(OrderJpaRepository orderJpaRepository, ProductController productController) {
        this.orderJpaRepository = orderJpaRepository;
        this.productController=productController;
    }
    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        //Order order =orderMapper.OrderDtoToOrder(orderDto);
        for (Long productId: orderDto.getProductsId()) {
            try {
                ProductDto productDto = productController.fetchProductById(productId);
            }catch (HttpClientErrorException ex){
                if(ex.getStatusCode()== HttpStatus.NOT_FOUND) {
                    throw new RuntimeException("invalid product id:" + productId);
                }
            }

        }

        return orderMapper.OrderToOrderDto(orderJpaRepository.save(orderMapper.OrderDtoToOrder(orderDto)));
    }

    public List<OrderDto> gerOrdersList() {
        return orderMapper.OrdersToOrdersDto(orderJpaRepository.findAll());
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
              return orderMapper.OrderToOrderDto(orderJpaRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException()));
     }

    @Override
    public void removeOrderById(Long orderId) {
        try {
            orderJpaRepository.deleteById(orderId);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }

    }
    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        return orderMapper.OrderToOrderDto(orderJpaRepository.save(orderMapper.OrderDtoToOrder(orderDto)));
    }

}
