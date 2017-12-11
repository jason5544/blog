package xyz.jason5544.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LastJob {
	public static void mainJob()
	{
		Configuration config = new Configuration();
		
		try
		{
			Job job = new Job(config);
			job.setJarByClass(LastJob.class);
			job.setJobName("blog3");
			job.addCacheFile(new Path("/blog/output1/part-r-00003").toUri());
			job.addCacheFile(new Path("/blog/output2/part-r-00000").toUri());
			

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			job.setMapperClass(LastMapper.class);
			job.setReducerClass(LastReducer.class);
			
			FileInputFormat.addInputPath(job, new Path("/blog/output1"));
			FileOutputFormat.setOutputPath(job, new Path("/blog/output3"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}
}
