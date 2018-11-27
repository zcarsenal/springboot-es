package com.zhouc.ffmpeg.storm.log;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @author Created by zhouc on 2018/11/26 0026.
 */
public class App {

  public static void main(String[] args) throws InterruptedException {
    TopologyBuilder builder = new TopologyBuilder();
    //设置Spout
    builder.setSpout("spout",new CallLogSpout());
    //设置creator-bolt
    builder.setBolt("creator-bolt",new CallLogCreatorBolt()).shuffleGrouping("spout");
    builder.setBolt("counter-bolt",new CallLogCounterBolt()).fieldsGrouping("creator-bolt",new Fields("call"));

    LocalCluster cluster = new LocalCluster();
    Config conf = new Config();
    conf.setDebug(true);
    cluster.submitTopology("MyTestStorm",conf,builder.createTopology());
    Thread.sleep(10000);
    cluster.shutdown();
  }
}
