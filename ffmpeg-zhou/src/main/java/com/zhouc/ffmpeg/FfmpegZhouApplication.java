package com.zhouc.ffmpeg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FfmpegZhouApplication {

  public static void main(String[] args) {
    SpringApplication.run(FfmpegZhouApplication.class, args);
  }

}
