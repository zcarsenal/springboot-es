package com.zhouc.ffmpeg.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.tools.ant.types.selectors.TypeSelector.FileType;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Created by zhouc on 2018/11/16 0016.
 */
@Data
@Accessors(chain = true)
public class Address {

  private int addressId;
  @Field(type = FieldType.Keyword)
  private String name;

}
