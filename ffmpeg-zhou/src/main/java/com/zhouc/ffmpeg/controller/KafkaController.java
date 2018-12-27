package com.zhouc.ffmpeg.controller;

import com.zhouc.ffmpeg.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by zhouc on 2018/11/29 0029.
 */
@RestController
@RequestMapping("kafka")
public class KafkaController {

  @Autowired
  private KafkaTemplate template;

  @RequestMapping("send")
  public void send() {
    template.send("topic1", new Student());
  }

}
