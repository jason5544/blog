package xyz.jason5544.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class SecondJob {
	public static void mainJob()
	{
		Configuration config = new Configuration();

		try
		{
			Job job = new Job(config);
			job.setJarByClass(SecondJob.class);
			job.setJobName("blog2");
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			job.setMapperClass(SecondMapper.class);
			job.setReducerClass(SecondReducer.class);
			
			FileInputFormat.addInputPath(job, new Path("/blog/output1"));
			FileOutputFormat.setOutputPath(job, new Path("/blog/output2"));

			boolean f = job.waitForCompletion(true);
			if (f)
			{
				System.out.println("Ö´ÐÐ³É¹¦");
				LastJob.mainJob();
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		

		
	}
}
