package com.zhouc.ffmpeg.storm;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @author Created by zhouc on 2018/11/26 0026.
 */
@Slf4j
public class CallLogCreatorBolt implements IRichBolt {

  private OutputCollector outputCollector;

  @Override
  public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
    this.outputCollector = outputCollector;
  }

  @Override
  public void execute(Tuple tuple) {
    //处理通话记录
    String from = tuple.getString(0);
    String to = tuple.getString(1);
    Integer duration = tuple.getInteger(2);
    //产生行的tuple
    outputCollector.emit(new Values(from + "-" + to, duration));
  }

  @Override
  public void cleanup() {
      log.info("====callLogCreatorBolt 是否进入这里了！！！！！！！！！！！！！！1");
  }

  /**
   * 设置输出字段的名称
   */
  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields("call", "duration"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
