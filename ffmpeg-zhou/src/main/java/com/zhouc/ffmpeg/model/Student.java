package com.zhouc.ffmpeg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@Data
@Document(indexName = "mb-otc-mt", type = "mb-student", shards = 3, replicas = 1)
public class Student {

  @Id
  private Long id;
  //@Field
  private String name;
  //@Field
  private String address;
  //@Field
  private int age;
  //@Field
  private String grade;

}
