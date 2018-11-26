package com.zhouc.ffmpeg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Created by zhouc on 2018/11/20 0020.
 */
@Data
@Document(indexName = "mb-otc-teacher")
public class Teacher {

  @Id
  private Long id;
  @Field(type = FieldType.Text)
  private String name;
  //@Field(type = FieldType.Integer ,ignoreFields = "age")
  @JsonIgnore
  private int age;
  @Field(type = FieldType.Double)
  private BigDecimal score;
  @Field(type = FieldType.Text)
  private String grade;
  /**
   * 底层存储结构
   * teacher: {
   *    address.addressId:[1,2,3],
   *    address.name:["张三","李四","王五"]
   * }
   */
  @Field(type = FieldType.Object)
  private List<Address> address;
}
