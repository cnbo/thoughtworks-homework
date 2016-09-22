package cn.thoughtworks.animal.snapshot.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.junit.Test;

import cn.thoughtworks.animal.snapshot.HistoryDataUtil;

/**
 * HistoryDataUtil的测试类。
 * 测试数据存放在对应的文件中。
 * ErrorDataTest.txt中的历史数据的数据有误
 * FormatDataTestID.txt中的id格式有误
 * FormatDataTestTime.txt中的time格式有误
 * FormatDataSnapshot.txt中的动物快照格式有误
 * FormatDataTest.txt中的数据顺序有误
 * FormatDataTestDeficiency.txt中有数据行缺失
 * RightDataTest.txt正确的数据测试ErrorDataTest.txt
 * @author 胡博
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
		
		//id不存在的情况
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
