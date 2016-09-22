package cn.thoughtworks.animal.snapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * ��ʷ���ݹ����࣬�ṩһЩ��̬�������ڲ����й�����
 * @author ����
 *
 */
public class HistoryDataUtil {
	/**
	 * ���ݵ�ǰʱ��ID����ʷ�����в��Ҷ�Ӧ��������ꡣ
	 * @param historyData ������յ���ʷ����
	 * @param id ��ǰʱ��id
	 * @return ����ʷ���ݵĸ�ʽ�����򷵻�"Invalid format."
	 * 			����ʷ���ݵ����������򷵻�"Conflict found at "+��ǰʱ��id
	 * 			����ʷ���ݸ�ʽ�����ݶ�û�д�����id����Ӧ�Ķ�����մ��ڣ��򷵻ض�Ӧ�����ж���Ŀ��գ����򷵻�"not found data"��
	 */
	public static String getSnapshot(String historyData, String id) {
		//datas�е�ÿһ��Ԫ�ش�ŵ���ĳһʱ�̵�id����Ӧ��ʱ��Ͷ������
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
	 * ����id����Ӧ�Ķ������
	 * @param datas ����Ŀ�����ʷ����
	 * @param id ������ʱ�̵�id
	 * @return ��id��Ӧ�Ķ�����մ��ڣ��򷵻ض���Ŀ��գ����򷵻س��� Ϊ0���ַ�����
	 */
	private static String findSnapshot(String[] datas, String id) {
		StringBuffer result = new StringBuffer();
		
		for (String data : datas) {
			//����snapshot�ĵ�һ��Ԫ����ĳһʱ�̵�id,�ڶ���Ԫ����id����Ӧ��ʱ��,�������������Ԫ���Ƕ��������仯
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
	 * �����ʷ���ݵ������Ƿ���ȷ
	 * @param datas ��ʷ����
	 * @return ����ʷ���ݵ����ݲ���ȷ���򷵻ص�һ������ȷ���ݵ�ʱ��id�����򷵻�null��
	 */
	private static String checkRightData(String[] datas) {
		//������ռ��ϣ�keyΪ��������֣�ֵΪ�������һʱ�̵�λ��
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
					//�������ǵ�һ�γ��֣�����������ӵ�snaphotMap������
					animalCoordinate = new AnimalCoordinate();
					setAnimalCoordinate(animalCoordinate, lastX, lastY, offsetX, offsetY);
					animalCoordinateMap.put(animalName, animalCoordinate);
				} else {
					if ((animalCoordinate.getLastX()+animalCoordinate.getOffsetX()) != lastX ||
							(animalCoordinate.getLastY()+animalCoordinate.getOffsetY()) != lastY) {
						return id;
					} else {
						//�������������ȷ���򱣴浱ǰ������λ��
						setAnimalCoordinate(animalCoordinate, lastX, lastY, offsetX, offsetY);
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * ���ö��������
	 * @param animalCoordinate ����������� 
	 * @param lastX ��һʱ��X����
	 * @param lastY ��һʱ��Y����
	 * @param offsetX X����仯
	 * @param offsetY Y����仯
	 */
	private static void setAnimalCoordinate(AnimalCoordinate animalCoordinate, int lastX,
			int lastY, int offsetX, int offsetY) {
		animalCoordinate.setLastX(lastX);
		animalCoordinate.setLastY(lastY);
		animalCoordinate.setOffsetX(offsetX);
		animalCoordinate.setOffsetY(offsetY);
	}
	
	/**
	 * �����ʷ���ݵĸ�ʽ�Ƿ���ȷ
	 * @param datas ��ʷ����
	 * @return ��������ȷ����true�����򷵻�false��
	 */
	private static boolean isRightFormat(String[] datas) {
		boolean result = true;
		
		String regexID = "([a-z_0-9]){8}(-){1}(([a-z_0-9]{4}(-)){3})([a-z_0-9]{12})";
		String regexDate = "(\\d){4}(/)(\\d){2}(/)(\\d){2}( ){1}((\\d){2}(:)){2}(\\d){2}";
		String regexSnapshot = "(\\w){1,}( )(\\d){1,}( )(\\d){1,}(( )(-){0,1}(\\d)){0,2}";
		for (String data : datas) {
			String[] snapshot = data.split("\n");
			//�ж�ĳ��ʱ�̶���Ŀ����Ƿ����������ϵ����ݣ���û����ֱ�ӷ���false
			if (snapshot.length < 3) {
				return false;
			}
			//�жϵ�һ���Ƿ���id
			if (snapshot[0].matches(regexID) == false) {
				return false;
			}
			//�жϵڶ����Ƿ���ʱ��
			if (snapshot[1].matches(regexDate) == false) {
				return false;
			}
			//�жϸ����������仯�����ݸ�ʽ�Ƿ���ȷ��
			for (int i = 2; i < snapshot.length; i++) {
				if (snapshot[i].matches(regexSnapshot) == false) {
					return false;
				}
			}
		}
		
		return result;
	}
}
