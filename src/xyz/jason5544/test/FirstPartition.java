package xyz.jason5544.test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class FirstPartition extends HashPartitioner<Text, IntWritable>
{
	public int getPartition(Text key, IntWritable value, int reduceCount)
	{
		if (key.equals(new Text("count")))
			return 3;
		else
			return super.getPartition(key, value, reduceCount - 1);
	}
}
