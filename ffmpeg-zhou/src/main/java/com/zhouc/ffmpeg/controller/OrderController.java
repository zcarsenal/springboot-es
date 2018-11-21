package com.zhouc.ffmpeg.controller;

import com.zhouc.ffmpeg.dto.OrderDTO;
import com.zhouc.ffmpeg.entity.Order;
import com.zhouc.ffmpeg.entity.OrderItem;
import com.zhouc.ffmpeg.repo.OrderItemRepository;
import com.zhouc.ffmpeg.repo.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
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
      item.setOrderId(order.getOrderId());
      OrderItem orderItem = new OrderItem();
      BeanUtils.copyProperties(item, orderItem);
      orderItemRepository.save(orderItem);
    });
  }

}
