package com.zhouc.ffmpeg.storm.wc;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 * @author Created by zhouc on 2018/11/27 0027.
 */
@Slf4j
public class CountBolt implements IRichBolt {

  private TopologyContext context;

  private OutputCollector collector;

  private Map<String, Integer> map;

  @Override
  public void prepare(Map map, TopologyContext context, OutputCollector collector) {
    this.context = context;
    this.collector = collector;
    this.map = Maps.newHashMap();
  }

  @Override
  public void execute(Tuple tuple) {
    String word = tuple.getString(0);
    Integer count = tuple.getInteger(1);
    if (!map.containsKey(word)) {
      map.put(word, 1);
      return;
    }
    map.put(word, map.get(word) + count);
    collector.ack(tuple);
  }

  @Override
  public void cleanup() {
    log.info("======================");
    map.forEach((key, value) -> {
      log.info("1111111111111111111111111111111");
      log.info("单词为:{},出现的次数为：{}", key, value);
    });
    log.info("==========================");
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields("word-wc"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
