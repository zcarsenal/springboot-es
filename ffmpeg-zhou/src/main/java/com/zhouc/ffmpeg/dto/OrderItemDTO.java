package com.zhouc.ffmpeg.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author Created by zhouc on 2018/11/21 0021.
 */
@Data
public class OrderItemDTO {
  private int id;
  private String name;
  private BigDecimal price;
  private String orderId;
}
