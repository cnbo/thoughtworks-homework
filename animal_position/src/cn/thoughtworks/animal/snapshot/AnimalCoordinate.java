package cn.thoughtworks.animal.snapshot;

/**
 * ����������࣬��¼����ĳ��ֵ�λ��
 * @author ����
 *
 */
public class AnimalCoordinate {
	private int lastX;		//��һ��ʱ�̶����X����
	private int lastY;		//��һ��ʱ�̶����Y����
	private int offsetX;	//X����仯��
	private int offsetY;	//Y����仯��

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

}
