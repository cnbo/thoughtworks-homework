package cn.thoughtworks.animal.snapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * 历史数据工具类，提供一些静态方法用于查找有关数据
 * @author 胡博
 *
 */
public class HistoryDataUtil {
	/**
	 * 根据当前时刻ID在历史数据中查找对应动物的坐标。
	 * @param historyData 动物快照的历史数据
	 * @param id 当前时刻id
	 * @return 若历史数据的格式有误，则返回"Invalid format."
	 * 			若历史数据的数据有误，则返回"Conflict found at "+当前时刻id
	 * 			若历史数据格式和数据都没有错误，若id所对应的动物快照存在，则返回对应的所有动物的快照；否则返回"not found data"。
	 */
	public static String getSnapshot(String historyData, String id) {
		//datas中的每一个元素存放的是某一时刻的id及对应的时间和动物快照
		String[] datas = historyData.split("\n\n");
		
		if (isRightFormat(datas) == false) {
			return "Invalid format.";
		}
		
		String checkResult = checkRightData(datas);
		if (checkResult != null) {
			return "Conflict found at " + checkResult;
		}
		
		String result = findSnapshot(datas, id);
		
		if (result.length() == 0) {
			return "not found data.";
		} else {
			return result;
		}
	}
	
	/**
	 * 查找id所对应的动物快照
	 * @param datas 动物的快照历史数据
	 * @param id 所查找时刻的id
	 * @return 若id对应的动物快照存在，则返回动物的快照；否则返回长度 为0的字符串。
	 */
	private static String findSnapshot(String[] datas, String id) {
		StringBuffer result = new StringBuffer();
		
		for (String data : datas) {
			//数组snapshot的第一个元素是某一时刻的id,第二个元素是id所对应的时刻,第三个及后面的元素是动物的坐标变化
			String[] snapshot = data.split("\n");
			
			if (snapshot[0].equals(id)) {
				for (int i = 2; i < snapshot.length; i++) {
					result.append(snapshot[i]).append("\n");
				}
			}
		}
		
		return result.toString();
	}
	
	/**
	 * 检查历史数据的数据是否正确
	 * @param datas 历史数据
	 * @return 若历史数据的数据不正确，则返回第一个不正确数据的时刻id；否则返回null。
	 */
	private static String checkRightData(String[] datas) {
		//动物快照集合，key为动物的名字，值为动物的上一时刻的位置
		Map<String, AnimalCoordinate> animalCoordinateMap = 
				new HashMap<String, AnimalCoordinate>();
		
		for (String data : datas) {
			String[] snapshot = data.split("\n");
			String id = snapshot[0];
			
			for (int i = 2; i < snapshot.length; i++) {
				String[] messages = snapshot[i].split(" ");
				String animalName = messages[0];
				int lastX = Integer.parseInt(messages[1]);
				int lastY = Integer.parseInt(messages[2]);
				
				int offsetX = 0, offsetY = 0;
				if (messages.length > 3) {
					offsetX = Integer.parseInt(messages[3]);
				}
				
				if (messages.length > 4) {
					offsetY = Integer.parseInt(messages[4]);
				}
				
				AnimalCoordinate animalCoordinate = animalCoordinateMap.get(animalName);
				
				
				if (animalCoordinate == null) {
					//若动物是第一次出现，则将其数据添加到snaphotMap集合中
					animalCoordinate = new AnimalCoordinate();
					setAnimalCoordinate(animalCoordinate, lastX, lastY, offsetX, offsetY);
					animalCoordinateMap.put(animalName, animalCoordinate);
				} else {
					if ((animalCoordinate.getLastX()+animalCoordinate.getOffsetX()) != lastX ||
							(animalCoordinate.getLastY()+animalCoordinate.getOffsetY()) != lastY) {
						return id;
					} else {
						//若动物的坐标正确，则保存当前的坐标位置
						setAnimalCoordinate(animalCoordinate, lastX, lastY, offsetX, offsetY);
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * 设置动物的坐标
	 * @param animalCoordinate 动物坐标对象 
	 * @param lastX 上一时刻X坐标
	 * @param lastY 上一时刻Y坐标
	 * @param offsetX X坐标变化
	 * @param offsetY Y坐标变化
	 */
	private static void setAnimalCoordinate(AnimalCoordinate animalCoordinate, int lastX,
			int lastY, int offsetX, int offsetY) {
		animalCoordinate.setLastX(lastX);
		animalCoordinate.setLastY(lastY);
		animalCoordinate.setOffsetX(offsetX);
		animalCoordinate.setOffsetY(offsetY);
	}
	
	/**
	 * 检查历史数据的格式是否正确
	 * @param datas 历史数据
	 * @return 若数据正确返回true，否则返回false。
	 */
	private static boolean isRightFormat(String[] datas) {
		boolean result = true;
		
		String regexID = "([a-z_0-9]){8}(-){1}(([a-z_0-9]{4}(-)){3})([a-z_0-9]{12})";
		String regexDate = "(\\d){4}(/)(\\d){2}(/)(\\d){2}( ){1}((\\d){2}(:)){2}(\\d){2}";
		String regexSnapshot = "(\\w){1,}( )(\\d){1,}( )(\\d){1,}(( )(-){0,1}(\\d)){0,2}";
		for (String data : datas) {
			String[] snapshot = data.split("\n");
			//判断某个时刻动物的快照是否有三行以上的数据，若没有则直接返回false
			if (snapshot.length < 3) {
				return false;
			}
			//判断第一行是否是id
			if (snapshot[0].matches(regexID) == false) {
				return false;
			}
			//判断第二行是否是时间
			if (snapshot[1].matches(regexDate) == false) {
				return false;
			}
			//判断各动物的坐标变化的数据格式是否正确。
			for (int i = 2; i < snapshot.length; i++) {
				if (snapshot[i].matches(regexSnapshot) == false) {
					return false;
				}
			}
		}
		
		return result;
	}
}
