package com.zhouc.ffmpeg.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhouc.ffmpeg.entity.OrderItem;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author Created by zhouc on 2018/11/21 0021.
 */
@Data
public class OrderDTO {

  private int id;
  private String orderId;
  private String orderType;
  private int statue;
  private List<OrderItemDTO> items;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

}
