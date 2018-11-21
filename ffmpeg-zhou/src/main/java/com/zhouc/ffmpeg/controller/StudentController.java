package com.zhouc.ffmpeg.controller;

import com.google.common.collect.Lists;
import com.zhouc.ffmpeg.entity.Student;
import com.zhouc.ffmpeg.repo.StudentRepository;
import java.util.List;
import java.util.Optional;
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

  @GetMapping("/query/nested/{keyword}")
  public List<Student> queryNested(@PathVariable String keyword, int pageSize, int pageNumber) {
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    queryBuilder.must(QueryBuilders.termQuery("address.name.keyword", keyword));
    NestedQueryBuilder nestedQueryBuilder = QueryBuilders
        .nestedQuery("address", queryBuilder, ScoreMode.None);
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Student> search = studentRepository.search(nestedQueryBuilder, pageable);
    List<Student> list = Lists.newArrayList();
    search.forEach(student ->list.add(student));
    return list;
  }

}
