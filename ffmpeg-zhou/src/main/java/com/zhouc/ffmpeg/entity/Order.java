package com.zhouc.ffmpeg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Created by zhouc on 2018/11/21 0021.
 */
@Data
@Document(indexName = "mb-order-index", type = "order",createIndex = false)
public class Order {

  @Id
  private int id;
  @Field(type = FieldType.Text)
  private String orderId;
  @Field(type = FieldType.Text)
  private String orderType;
  @Field(type = FieldType.Integer)
  private int statue;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom,
      pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
  private Date createTime;


}
