package com.zhouc.ffmpeg.repo.impl;

import com.zhouc.ffmpeg.model.Student;
import com.zhouc.ffmpeg.repo.StudentOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
public class StudentRepositoryImpl implements StudentOperations {

  @Autowired
  ElasticsearchOperations elasticsearchOperations;

  @Override
  public Page<Student> getByAddress(String country) {
    return null;
  }
}
