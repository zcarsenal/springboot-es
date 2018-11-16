package com.zhouc.ffmpeg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@Data
@Document(indexName = "mb-otc-mt", type = "mb-student", shards = 3)
@Mapping(mappingPath = "mapping/student.json")
public class Student {

  @Id
  private Long id;
  private String name;
  @JsonIgnore
  private int age;
  private String grade;
  @JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date date;

}
