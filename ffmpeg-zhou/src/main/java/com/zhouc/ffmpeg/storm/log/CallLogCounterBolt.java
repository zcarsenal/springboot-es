package com.zhouc.ffmpeg.storm.log;

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
 * @author Created by zhouc on 2018/11/26 0026.
 */
@Slf4j
public class CallLogCounterBolt implements IRichBolt {

  private Map<String, Integer> countMap;

  private OutputCollector outputCollector;

  @Override
  public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    this.countMap = Maps.newHashMap();
    this.outputCollector = outputCollector;

  }

  @Override
  public void execute(Tuple tuple) {
    String call = tuple.getString(0);
    Integer duration = tuple.getInteger(1);
    log.info("通话信息:{},通话时长:{}",call,duration);
    if (!countMap.containsKey(call)) {
      countMap.put(call, 1);
    } else {
      countMap.put(call, countMap.get(call) + 1);
    }
    outputCollector.ack(tuple);
  }

  @Override
  public void cleanup() {
    log.info("打印啦啦啦啦啦啦啦啦!!!!");
    countMap.forEach((k, v) -> {
      log.info("呼叫信息：{}，呼叫次数：{}", k, v);
    });
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields("call"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
