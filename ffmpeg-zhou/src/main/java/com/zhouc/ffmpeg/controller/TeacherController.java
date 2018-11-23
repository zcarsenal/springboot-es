package com.zhouc.ffmpeg.controller;

import com.google.common.collect.Lists;
import com.zhouc.ffmpeg.entity.Teacher;
import com.zhouc.ffmpeg.repo.TeacherRepository;
import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
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
@RequestMapping("/teacher")
public class TeacherController {

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  @Autowired
  private TeacherRepository teacherRepository;

  @PostMapping
  public void save(@RequestBody Teacher teacher) {
    teacherRepository.save(teacher);
  }

  @GetMapping("/query/object/{cityName}/{cityId}")
  public List<Teacher> queryNested(@PathVariable String cityName, @PathVariable String cityId,
      int pageSize, int pageNumber) {
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    queryBuilder.must(QueryBuilders.termQuery("address.name.keyword", cityName))
        .must(QueryBuilders.termQuery("address.addressId", cityId));
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Teacher> search = teacherRepository.search(queryBuilder, pageable);
    List<Teacher> list = Lists.newArrayList();
    search.forEach(teacher -> list.add(teacher));
    return list;
  }

}
