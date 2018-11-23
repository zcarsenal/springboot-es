package com.zhouc.ffmpeg.controller;

import com.zhouc.ffmpeg.dto.OrderDTO;
import com.zhouc.ffmpeg.dto.OrderItemDTO;
import com.zhouc.ffmpeg.entity.Order;
import com.zhouc.ffmpeg.entity.OrderItem;
import com.zhouc.ffmpeg.repo.OrderItemRepository;
import com.zhouc.ffmpeg.repo.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  @PostMapping
  public void save(@RequestBody OrderDTO order) {
    Order o = new Order();
    BeanUtils.copyProperties(order, o);
    orderRepository.save(o);
    order.getItems().stream().forEach(item -> {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrderPId(order.getOrderId());
      BeanUtils.copyProperties(item, orderItem);
      orderItemRepository.save(orderItem);
    });
  }

  @GetMapping("/getById/{id}")
  public OrderDTO getOrderById(@PathVariable Long id) {
    OrderDTO orderDTO = new OrderDTO();
    boolean boo = orderRepository.existsById(id);
    if (!boo) {
      return orderDTO;
    }
    Optional<Order> byId = orderRepository.findById(id);
    byId.ifPresent(order -> {
      BeanUtils.copyProperties(order, orderDTO);
      List<OrderItem> byOrderPId = orderItemRepository
          .findByOrderPId(String.valueOf(order.getOrderId()));
      List<OrderItemDTO> collect = byOrderPId.stream().map(orderItem -> {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            BeanUtils.copyProperties(orderItem, orderItemDTO);
            return orderItemDTO;
          }
      ).collect(Collectors.toList());
      orderDTO.setItems(collect);
    });
    return orderDTO;
  }

}
