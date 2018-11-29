package com.zhouc.ffmpeg.storm.wc;

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
 * @author Created by zhouc on 2018/11/27 0027.
 */
public class WorldSpout implements IRichSpout {

  private TopologyContext context;

  private SpoutOutputCollector collector;

  private List<String> words;

  private Random random = new Random();

  @Override
  public void open(Map map, TopologyContext context,
      SpoutOutputCollector collector) {
    this.context = context;
    this.collector = collector;
    words = Lists.newArrayList();
    words.add("hello words json");
    words.add("hello words jack");
    words.add("hello words mack");
    words.add("hello words tomas");
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
    collector.emit(new Values(words.get(random.nextInt(4))));
  }

  @Override
  public void ack(Object o) {

  }

  @Override
  public void fail(Object o) {

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("line"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
