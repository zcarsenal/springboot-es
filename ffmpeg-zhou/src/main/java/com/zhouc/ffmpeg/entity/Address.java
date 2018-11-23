package com.zhouc.ffmpeg.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Created by zhouc on 2018/11/16 0016.
 */
@Data
@Accessors(chain = true)
public class Address {

  private int addressId;
  private String name;

}
