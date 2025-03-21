package com.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] bytes, Object value, byte[] keyBytes, Cluster cluster) {

        if (keyBytes == null)
            throw new InvalidRecordException("Need Message key");
        if (((String)key).equals("Pangyo"))
            return 0;

        List<PartitionInfo> partitions = cluster.availablePartitionsForTopic(topic);
        int numPartitions = partitions.size();

        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> map) {}
}
