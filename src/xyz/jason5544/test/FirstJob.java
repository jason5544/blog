package xyz.jason5544.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FirstJob {
	public static void main(String[] args)
	{
		Configuration config = new Configuration();
		try
		{
			Job job = new Job(config);
			job.setJarByClass(FirstJob.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			job.setNumReduceTasks(4);
			job.setPartitionerClass(FirstPartition.class);
			job.setMapperClass(FirstMapper.class);
			job.setCombinerClass(FirstReducer.class);
			job.setReducerClass(FirstReducer.class);
			FileInputFormat.addInputPath(job, new Path("/blog/input"));
			FileOutputFormat.setOutputPath(job, new Path("/blog/output1"));
			boolean f = job.waitForCompletion(true);
			if (job.waitForCompletion(true))
			{
				System.out.println("FirstJob complete");
				SecondJob.mainJob();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
