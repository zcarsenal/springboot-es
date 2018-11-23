package com.zhouc.ffmpeg.entity;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Created by zhouc on 2018/11/21 0021.
 */
@Data
@Document(indexName = "mb-order-item-index")
public class OrderItem {

  @Id
  private int id;
  @Field(type = FieldType.Text)
  private String name;
  @Field(type = FieldType.Double)
  private BigDecimal price;
  @Field(type = FieldType.Text)
  private String orderPId;

}
