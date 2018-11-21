package com.zhouc.ffmpeg.repo;

import com.zhouc.ffmpeg.entity.OrderItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Created by zhouc on 2018/11/14 0014.
 */
@Repository
public interface OrderItemRepository extends ElasticsearchRepository<OrderItem, Long>{

}