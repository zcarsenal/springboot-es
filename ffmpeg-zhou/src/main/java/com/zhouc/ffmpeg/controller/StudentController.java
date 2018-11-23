package com.zhouc.ffmpeg.controller;

import com.google.common.collect.Lists;
import com.zhouc.ffmpeg.entity.Student;
import com.zhouc.ffmpeg.repo.StudentRepository;
import com.zhouc.ffmpeg.thread.Student1Runnable;
import com.zhouc.ffmpeg.thread.StudentRunnable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@RestController
@RequestMapping("/stu")
@Slf4j
public class StudentController {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  @PostMapping
  public void save(@RequestBody Student student) {
    studentRepository.save(student);
  }

  @DeleteMapping("/{id}")
  public void delete(Long id) {
    studentRepository.deleteById(id);
  }

  @GetMapping("/{id}")
  public Student getByID(Long id) {
    Optional<Student> student = studentRepository.findById(id);
    return student.get();
  }

  @GetMapping("/address/{address}")
  public List<Student> getByID(String address) {
    return studentRepository.findXxx(address);
  }

  @GetMapping("/address/page")
  public List<Student> getByPage(String address) {
    BoolQueryBuilder builder = QueryBuilders.boolQuery();
    builder.must(QueryBuilders.matchQuery("address", address));
    Iterable<Student> search = studentRepository.search(builder);
    List<Student> list = Lists.newArrayList();
    search.forEach(student -> {
      list.add(student);
    });
    return list;
  }

  @DeleteMapping("{index}")
  public void deleteIndex(String index) {
    elasticsearchOperations.deleteIndex(index);
  }

  @GetMapping("/query/object/{cityName}/{cityId}")
  public List<Student> queryNested(@PathVariable String cityName, @PathVariable String cityId,
      int pageSize, int pageNumber) {
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    queryBuilder.must(QueryBuilders.termQuery("address.name.keyword", cityName))
        .must(QueryBuilders.termQuery("address.addressId", cityId));
    NestedQueryBuilder nestedQueryBuilder = QueryBuilders
        .nestedQuery("address", queryBuilder, ScoreMode.None);
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Student> search = studentRepository.search(nestedQueryBuilder, pageable);
    List<Student> list = Lists.newArrayList();
    search.forEach(student -> list.add(student));
    return list;
  }

  @PostMapping("bulk/{size}/{now}")
  public void save(@PathVariable int size, @PathVariable int now) {

    //elasticsearchOperations.deleteIndex(Student.class);
    //studentRepository.deleteAll();

    int count = size + now;
    CountDownLatch countDownLatch = new CountDownLatch(size);
    long startTime = System.currentTimeMillis();
    for (int i = now; i < count; i++) {
      Thread thread = new Thread(new StudentRunnable(countDownLatch, i, studentRepository));
      thread.start();
    }
    try {
      countDownLatch.await();
      long endTime = System.currentTimeMillis();
      log.info("处理插入{}条数据 总用时:{}", size, (endTime - startTime));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @PostMapping("bulk1/{size}")
  public void save1(@PathVariable int size) {

    //elasticsearchOperations.deleteIndex(Student.class);
    studentRepository.deleteAll();

    int count = size;
    CountDownLatch countDownLatch = new CountDownLatch(count);
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < count; i++) {
      Thread thread = new Thread(new Student1Runnable(countDownLatch, i, studentRepository));
      thread.start();
    }
    try {
      countDownLatch.await();
      long endTime = System.currentTimeMillis();
      log.info("处理插入{}万条数据 总用时:{}", size, (endTime - startTime));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
