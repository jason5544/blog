package xyz.jason5544.test;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class testIK {
	public static void main(String[] args) throws IOException
	{
		StringReader sr = new StringReader("世界上最早的一份汽车保险出现在1898年的美国。美国的旅行者保险有限公司在1898年给纽约布法罗的杜鲁门马丁上了第一份汽车保险。马丁非常担心自己的爱车会被马冲撞。");
		IKSegmenter ik = new IKSegmenter(sr, true);
		Lexeme word = null;
		while ((word = ik.next()) != null)
		{
			String w = word.getLexemeText();
			System.out.println(w);
		}
	}
}
