package com.zhouc.ffmpeg.util;

import com.zhouc.ffmpeg.exception.GeneratorException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 全局唯一的ID 工厂 使用的是 Twitter-Snowflake 算法 可以参考
 * -----http://yuanhsh.iteye.com/blog/2209696 经过测试 在4个线程 同时不间断生产 1000W主键的时候
 * 性能和准确性都能得到保障 每秒能产生26W+的唯一主键
 *
 * @author Administrator
 */
@Component
public class PkGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(
      PkGenerator.class);
  private static final long TWEPOCH = 1361753741828L;
  private static final long WORKER_ID_BITS = 10L;
  private static final long MAX_WORKER_ID = -1L ^ -1L << WORKER_ID_BITS;
  private static final long SEQUENCE_BITS = 12L;
  public static final long SEQUENCE_MASK = -1L ^ -1L << SEQUENCE_BITS;
  private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
  private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
  private final long workerId;
  private long sequence = 0L;
  private long lastTimestamp = -1L;

  public PkGenerator() {
    this.workerId = 0x000000FF & getLastIP();
    if (workerId > MAX_WORKER_ID || workerId < 0) {
      throw new GeneratorException();
    }
  }

  @Synchronized
  public long nextId() {
    long timestamp = this.timeGen();
    if (this.lastTimestamp == timestamp) {
      this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
      if (this.sequence == 0) {
        timestamp = this.tilNextMillis(this.lastTimestamp);
      }
    } else {
      this.sequence = 0;
    }
    if (timestamp < this.lastTimestamp) {
      throw new GeneratorException();
    }

    this.lastTimestamp = timestamp;
    long nextId = (timestamp - TWEPOCH << TIMESTAMP_LEFT_SHIFT) | (this.workerId << WORKER_ID_SHIFT)
        | (this.sequence);
    LOG.info("PkGenerator pk {}", nextId);
    return nextId;
  }

  private long tilNextMillis(final long lastTimestamp) {
    long timestamp = this.timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = this.timeGen();
    }
    return timestamp;
  }

  private long timeGen() {
    return System.currentTimeMillis();
  }

  private byte getLastIP() {
    byte lastIp = 0;
    try {
      InetAddress ip = InetAddress.getLocalHost();
      byte[] ipByte = ip.getAddress();
      lastIp = ipByte[ipByte.length - 1];
    } catch (UnknownHostException e) {
      throw new GeneratorException();
    }
    return lastIp;
  }

}