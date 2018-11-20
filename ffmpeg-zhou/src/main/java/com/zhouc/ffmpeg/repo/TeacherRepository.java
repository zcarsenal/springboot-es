package com.zhouc.ffmpeg.repo;

import com.zhouc.ffmpeg.entity.Student;
import com.zhouc.ffmpeg.entity.Teacher;
import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@Repository
public interface TeacherRepository extends ElasticsearchRepository<Teacher, Long>{

  List<Teacher> findByName(String name);

}
