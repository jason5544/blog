package xyz.jason5544.test;

import java.io.IOException;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LastReducer extends Reducer<Text, Text, Text, Text>{
	protected void reduce(Text key, Iterable<Text> arg1, Context context) throws IOException, InterruptedException
	{
		StringBuffer sb = new StringBuffer();
		
		for (Text i: arg1)
		{
			sb.append(i.toString() + "\t");
		}
		context.write(key, new Text(sb.toString()));
	}
		
}
