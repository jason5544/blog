package xyz.jason5544.test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SecondReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		int sum = 0;
		for (IntWritable i : values)
		{
			sum += i.get();
		}
		System.out.println("sum: " + sum);
		context.write(key, new IntWritable(sum));
	}
	

}
