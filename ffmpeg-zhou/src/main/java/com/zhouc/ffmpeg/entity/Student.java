package com.zhouc.ffmpeg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Document(indexName = "mb-otc-mt", type = "mb-student", shards = 3)
@Accessors(chain = true)
//@Mapping(mappingPath = "mapping/student.json")
public class Student {

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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom,
      pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
  private Date date;
  @Field(type = FieldType.Nested)
  private List<Address> address;

}
