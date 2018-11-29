package com.zhouc.ffmpeg.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Created by zhouc on 2018/11/28 0028.
 */
@Component
@Slf4j
public class SchduleTest {

  @Scheduled(cron="0/5 * *  * * ? ")
  public void schetest(){
    log.info("测试定时任务5秒执行一次 scheduled invoke!");
  }

}
