package xyz.jason5544.test;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class testIK {
	public static void main(String[] args) throws IOException
	{
		StringReader sr = new StringReader("�����������һ���������ճ�����1898��������������������߱������޹�˾��1898���ŦԼ�����޵Ķ�³�������˵�һ���������ա����ǳ������Լ��İ����ᱻ���ײ��");
		IKSegmenter ik = new IKSegmenter(sr, true);
		Lexeme word = null;
		while ((word = ik.next()) != null)
		{
			String w = word.getLexemeText();
			System.out.println(w);
		}
	}
}
