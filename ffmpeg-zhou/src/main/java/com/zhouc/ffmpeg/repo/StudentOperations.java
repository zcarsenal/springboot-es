package com.zhouc.ffmpeg.repo;

import com.zhouc.ffmpeg.entity.Student;
import org.springframework.data.domain.Page;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
public interface StudentOperations {

  Page<Student> getByAddress(String country);
}
