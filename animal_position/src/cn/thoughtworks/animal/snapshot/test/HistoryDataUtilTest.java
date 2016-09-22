package cn.thoughtworks.animal.snapshot.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.junit.Test;

import cn.thoughtworks.animal.snapshot.HistoryDataUtil;

/**
 * HistoryDataUtil�Ĳ����ࡣ
 * �������ݴ���ڶ�Ӧ���ļ��С�
 * ErrorDataTest.txt�е���ʷ���ݵ���������
 * FormatDataTestID.txt�е�id��ʽ����
 * FormatDataTestTime.txt�е�time��ʽ����
 * FormatDataSnapshot.txt�еĶ�����ո�ʽ����
 * FormatDataTest.txt�е�����˳������
 * FormatDataTestDeficiency.txt����������ȱʧ
 * RightDataTest.txt��ȷ�����ݲ���ErrorDataTest.txt
 * @author ����
 *
 */
public class HistoryDataUtilTest {

	@Test
	public void testGetSnapshot() throws IOException {
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/ErrorDataTest.txt");
		
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/FormatDataTestID.txt");
		
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/FormatDataTestTime.txt");
		
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/FormatDataSnapshot.txt");
		
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/FormatDataTest.txt");
		
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/FormatDataTestDeficiency.txt");
		
//		String historyData = 
//				getHistoryData("E:/cnbo_review/animal_position/src" +
//						"/cn/thoughtworks/animal/snapshot/test/RightDataTest.txt");
//		System.out.println(HistoryDataUtil.getSnapshot(historyData, "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155"));
		
		//id�����ڵ����
		String historyData = 
				getHistoryData("E:/cnbo_review/animal_position/src" +
						"/cn/thoughtworks/animal/snapshot/test/RightDataTest.txt");
		System.out.println(HistoryDataUtil.getSnapshot(historyData, "dcfa0c7a-5855-4ed2-bc8c-4accae8bd166"));
	}
	
	private String getHistoryData(String fileName) throws IOException {
		StringBuilder historyData = new StringBuilder();
		
		File file = new File(fileName);
		BufferedReader reader = 
				new BufferedReader(new FileReader(file));
		
		String text = null;
		while ((text = reader.readLine()) != null) {
			historyData.append(text).append("\n");
		}
		
		return historyData.toString();
	}

}
