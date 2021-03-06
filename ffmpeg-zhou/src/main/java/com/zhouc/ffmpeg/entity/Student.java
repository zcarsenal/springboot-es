package com.zhouc.ffmpeg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@Data
@Document(indexName = "mb-otc-mt", shards = 3)
@Accessors(chain = true)
//@Mapping(mappingPath = "mapping/student.json")
public class Student {

  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  @Field(type = FieldType.Keyword)
  private String name;
  //@Field(type = FieldType.Integer ,ignoreFields = "age")
  @JsonIgnore
  private int age;
  @Field(type = FieldType.Double)
  private BigDecimal score;
  @Field(type = FieldType.Text)
  private String grade;

  @Field(type = FieldType.Integer)
  private Integer status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom,
      pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
  private Date date;
  @Field(type = FieldType.Nested)
  private List<Address> address;

}
