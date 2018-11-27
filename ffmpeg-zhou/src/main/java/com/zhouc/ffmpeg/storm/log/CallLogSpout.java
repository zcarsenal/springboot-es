package com.zhouc.ffmpeg.storm.log;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * Spout负责产生流数据
 *
 * @author Created by zhouc on 2018/11/26 0026.
 */
public class CallLogSpout implements IRichSpout {

  // Spout输出收集器
  private SpoutOutputCollector collector;
  // 是否完成
  private boolean completed = false;
  // 上下文
  private TopologyContext topologyContext;
  //随机发生器
  private Random random = new Random();

  //计数器
  private Integer idx = 0;

  @Override
  public void open(Map map, TopologyContext topologyContext,
      SpoutOutputCollector collector) {
    this.topologyContext = topologyContext;
    this.collector = collector;
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

  /**
   * 下一个元组
   */
  @Override
  public void nextTuple() {
    if (this.idx <= 1000) {
      List<String> mobileNumbers = Lists.newArrayList();
      mobileNumbers.add("1234123401");
      mobileNumbers.add("1234123402");
      mobileNumbers.add("1234123403");
      mobileNumbers.add("1234123404");
      Integer localIdx = 0;
      while (localIdx++ < 100 && this.idx++ < 1000) {
        //取出主叫
        String caller = mobileNumbers.get(random.nextInt(4));
        //去除被叫
        String callee = mobileNumbers.get(random.nextInt(4));
        while (callee.equals(caller)) {
          //重新去除被叫
          callee = mobileNumbers.get(random.nextInt(4));
        }
        //模拟通话时长
        Integer duration = random.nextInt(60);
        //输出元组
        this.collector.emit(new Values(caller, callee, duration));
      }
    }
  }

  @Override
  public void ack(Object o) {

  }

  @Override
  public void fail(Object o) {

  }

  //定义输出的字段名称
  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    outputFieldsDeclarer.declare(new Fields("from", "to", "count"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
