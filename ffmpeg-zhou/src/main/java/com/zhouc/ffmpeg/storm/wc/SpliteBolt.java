package com.zhouc.ffmpeg.storm.wc;

import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @author Created by zhouc on 2018/11/27 0027.
 */
public class SpliteBolt implements IRichBolt {

  private TopologyContext context;

  private OutputCollector collector;

  @Override
  public void prepare(Map map, TopologyContext context, OutputCollector collector) {
    this.context = context;
    this.collector = collector;
  }

  @Override
  public void execute(Tuple tuple) {
    String line = tuple.getString(0);
    String[] worlds = line.split(" ");
    for (String world : worlds) {
      collector.emit(new Values(world, 1));
    }
  }

  @Override
  public void cleanup() {

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("word", "count"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
