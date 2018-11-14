package com.zhouc.ffmpeg.repo;

import com.zhouc.ffmpeg.model.Student;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@Repository
public interface StudentRepository extends ElasticsearchRepository<Student, Long> ,StudentOperations{

  @Query("{\"bool\" : {\"must\" : {\"match\" : {\"address\" : \"?\"}}}}")
  List<Student> findByAddress(String address);

}
