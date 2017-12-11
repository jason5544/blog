package xyz.jason5544.test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FirstReducer extends Reducer<Text, IntWritable, Text, IntWritable>
{
	protected void reduce(Text arg0, Iterable<IntWritable> arg1, Context arg2)
		throws IOException, InterruptedException
	{
		int sum = 0;
		for (IntWritable i : arg1)
		{
			sum = sum + i.get();
		}
		
		if (arg0.equals(new Text("count")))
		{
			System.out.println(arg0.toString()+ "------------" + sum);
		}
		arg2.write(arg0, new IntWritable(sum));
	}
}
