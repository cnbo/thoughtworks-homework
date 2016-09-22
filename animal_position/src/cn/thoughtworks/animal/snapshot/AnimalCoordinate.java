package cn.thoughtworks.animal.snapshot;

/**
 * 动物的坐标类，记录动物的出现的位置
 * @author 胡博
 *
 */
public class AnimalCoordinate {
	private int lastX;		//上一个时刻动物的X坐标
	private int lastY;		//上一个时刻动物的Y坐标
	private int offsetX;	//X坐标变化量
	private int offsetY;	//Y坐标变化量

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
