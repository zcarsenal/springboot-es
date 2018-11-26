package com.zhouc.ffmpeg.storm;

import java.util.Map;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;

/**
 * Spout负责产生流数据
 *
 * @author Created by zhouc on 2018/11/26 0026.
 */
public class CallLogSpout implements IRichSpout {

  @Override
  public void open(Map map, TopologyContext topologyContext,
      SpoutOutputCollector spoutOutputCollector) {

  }

  @Override
  public void close() {

  }

  @Override
  public void activate() {

  }

  @Override
  public void deactivate() {

  }

  @Override
  public void nextTuple() {

  }

  @Override
  public void ack(Object o) {

  }

  @Override
  public void fail(Object o) {

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
