package com.zhouc.ffmpeg.listener;

import com.zhouc.ffmpeg.entity.Student;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationStartupListener implements ApplicationRunner {

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {
    try {
      log.info(">>>>>>>>>>>>>>>APPLICATION STARTUP LISTENER IS RUNNING<<<<<<<<<<<<<");
      //elasticsearchOperations.putMapping(Student.class);

      log.info(">>>>>>>>>>>>>>>APPLICATION STARTUP LISTENER IS COMPLETED<<<<<<<<<<<<<");
    } catch (Exception e) {
      log.error("APPLICATION STARTUP EXCEPTION :{}", e.getMessage());
      e.printStackTrace();
    }
  }
}
