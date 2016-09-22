目录简介：
--cn.thoughtworks.animal.snapshot存放坐标类和历史数据工具类
	--AnimalCoordinate动物坐标类
	--HistoryDataUtil历史数据工具类，提供静态的String getSnapShot(String historyData, String id)方法。

--cn.thoughtworks.animal.snapshot.test存放测试用例和测试数据
	--HistoryDataUtilTest历史数据工具类的测试用例
	--测试数据存放在对应的文件中。
 		--ErrorDataTest.txt中的历史数据的数据有误
 		--FormatDataTestID.txt中的id格式有误
 		--FormatDataTestTime.txt中的time格式有误
 		--FormatDataSnapshot.txt中的动物快照格式有误
 		--FormatDataTest.txt中的数据顺序有误
 		--FormatDataTestDeficiency.txt中有数据行缺失
 		--RightDataTest.txt正确的数据测试ErrorDataTest.txt
 		
 特点：代码整洁性 将数据和逻辑代码分开 显得干净