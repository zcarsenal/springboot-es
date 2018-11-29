package com.zhouc.ffmpeg.storm.wc;

import java.util.concurrent.TimeUnit;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @author Created by zhouc on 2018/11/27 0027.
 */
public class App {

  public static void main(String[] args) throws InterruptedException {
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("word-spout",new WorldSpout(),3);
    builder.setBolt("split-bolt",new SpliteBolt()).shuffleGrouping("word-spout");
    builder.setBolt("count-bolt",new CountBolt()).fieldsGrouping("split-bolt",new Fields("word"));

    Config config = new Config();
    config.setDebug(true);

    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("my-word-count",config,builder.createTopology());
    TimeUnit.SECONDS.sleep(5);

    cluster.shutdown();
  }
}
