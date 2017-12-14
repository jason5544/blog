package xyz.jason5544.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;



public class LastMapper extends Mapper<LongWritable, Text, Text, Text >
{
	public static Map<String, Integer> cmap = null;
	public static Map<String, Integer> df = null;
		
	protected void setup(Context context) 
			throws IOException, InterruptedException
	{
		System.out.println("----------------------------setup-----------------------");
		
		if (cmap == null || cmap.size() == 0 || df == null || df.size() == 0)
		{

//			System.out.println("----------------------------read url------------------------");
			URI[] ss = context.getCacheFiles();
			
			if (ss != null)
			{
				for (int i = 0; i < ss.length; i++)
				{
					URI uri = ss[i];
					System.out.println("path: "+ uri.getPath());
					if (uri.getPath().endsWith("part-r-00003"))
					{
						Path path = new Path(uri.getPath());
						BufferedReader br = new BufferedReader(new FileReader(path.getName().toString()));
						String line = br.readLine();
						System.out.println("line:" + line);
						if (line.startsWith("count"))
						{
							String[] ls = line.split("\\s+");
							cmap = new HashMap<String, Integer>();
							cmap.put(ls[0], Integer.parseInt(ls[1].trim()));
						}
						br.close();
					}
					else if (uri.getPath().endsWith("part-r-00000"))
					{
						df = new HashMap<String, Integer>();
						Path path = new Path(uri.getPath());
						BufferedReader br = new BufferedReader(new FileReader(path.getName().toString()));
						String line;
						while ((line = br.readLine()) != null)
						{

							System.out.println("line: " + line);
							String[] ls = line.split("\\s+");
							df.put(ls[0], Integer.parseInt(ls[1].trim()));
						}
						br.close();
					}
				}
			}
			else
			{
				System.out.println("ss is null");
			}
		}
	}

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		FileSplit fs = (FileSplit) context.getInputSplit();
		
		if (!fs.getPath().getName().contains("part-r-00003"))
		{
			String[] v = value.toString().trim().split("\\s+");
			if (v.length >= 2)
			{
				int tf = Integer.parseInt(v[1].trim());
				String[] ss = v[0].split("_");
				if (ss.length >= 2)
				{
					String w = ss[0];
					String id = ss[1];
					
					double s = tf*Math.log(cmap.get("count"))/df.get(w);
					
					NumberFormat nf = NumberFormat.getInstance();
					nf.setMaximumFractionDigits(5);
					context.write(new Text(id), new Text(w + ":" + nf.format(s)));
				}
				else
				{
					System.out.println(value.toString() + "--------------------");
				}
			}
		}
	}
}
