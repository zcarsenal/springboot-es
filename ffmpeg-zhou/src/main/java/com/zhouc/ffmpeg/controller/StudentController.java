package com.zhouc.ffmpeg.controller;

import com.zhouc.ffmpeg.model.Student;
import com.zhouc.ffmpeg.repo.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    return studentRepository.findByAddress(address);
  }
  @GetMapping("/address/page")
  public List<Student> getByPage(String address) {
    Page<Student> byAddress = studentRepository.getByAddress(address);
    return byAddress.get().collect(Collectors.toList());
  }

  @DeleteMapping("{index}")
  public void deleteIndex(String index) {
    elasticsearchOperations.deleteIndex(index);
  }

}
