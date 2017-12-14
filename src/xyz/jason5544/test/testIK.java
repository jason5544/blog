package xyz.jason5544.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.InputStream;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class testIK {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("blog.txt"), "UTF-8"));
		String value;
		while ((value = br.readLine()) != null)
		{
			String[] v = value.toString().trim().split("\\s+");
			if (v.length >= 2)
			{
				StringReader sr = new StringReader(v[1]);
				IKSegmenter ik = new IKSegmenter(sr, true);
				Lexeme word = null;
				while ((word = ik.next()) != null)
				{
					String w = word.getLexemeText();
					System.out.println(w);
				}

				
			}

		}
		br.close();
	}
}
