package com.zhouc.ffmpeg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Document(indexName = "mb-otc-mt1", type = "mb-teacher", shards = 3)
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
}
