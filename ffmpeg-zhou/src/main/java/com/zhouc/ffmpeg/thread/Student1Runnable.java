package com.zhouc.ffmpeg.thread;

import com.google.common.collect.Lists;
import com.zhouc.ffmpeg.entity.Address;
import com.zhouc.ffmpeg.entity.Student;
import com.zhouc.ffmpeg.repo.StudentRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Created by zhouc on 2018/11/23 0023.
 */
@Slf4j
public class Student1Runnable implements Runnable {

  private CountDownLatch countDownLatch;

  private StudentRepository studentRepository;

  private int id;

  public Student1Runnable(CountDownLatch countDownLatch, int id,
      StudentRepository studentRepository) {
    this.countDownLatch = countDownLatch;
    this.id = id;
    this.studentRepository = studentRepository;
  }

  @Override
  public void run() {
    /*try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }*/
    for (int i = 0; i < 100000 ; i++) {
      log.info("开始处理线程数据");
      Student student = new Student();
      student.setId(Long.valueOf(i*id))
          .setName("张三" + id)
          .setAge(30 + id)
          .setDate(new Date())
          .setGrade("三年级")
          .setScore(new BigDecimal(100));
      List<Address> list = Lists.newArrayList();
      Address address = new Address();
      address.setName("天津").setAddressId(id + 5);
      Address address1 = new Address();
      address1.setName("北京").setAddressId(id - 5);
      list.add(address);
      list.add(address1);
      student.setAddress(list);
      studentRepository.save(student);
    }
    countDownLatch.countDown();
  }
}
