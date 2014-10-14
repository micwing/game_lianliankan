package game;

import java.awt.Color;

import javax.swing.JButton;

public class Cell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2558491969593969627L;
	
	int xPoint;
	int yPoint;
	
	Color cellColor;
	
	public Cell(int x, int y) {
		this.xPoint = x;
		this.yPoint = y;
		
		this.cellColor = Const.cellClr;
		
		this.setSize(Const.btnWidth, Const.btnHeigth);
	}

	/**
	 * @return the xPoint
	 */
	public int getxPoint() {
		return xPoint;
	}

	/**
	 * @param xPoint the xPoint to set
	 */
	public void setxPoint(int xPoint) {
		this.xPoint = xPoint;
	}

	/**
	 * @return the yPoint
	 */
	public int getyPoint() {
		return yPoint;
	}

	/**
	 * @param yPoint the yPoint to set
	 */
	public void setyPoint(int yPoint) {
		this.yPoint = yPoint;
	}

	/**
	 * @return the cellColor
	 */
	public Color getCellColor() {
		return cellColor;
	}

	/**
	 * @param cellColor the cellColor to set
	 */
	public void setCellColor(Color cellColor) {
		this.cellColor = cellColor;
	}
	
	
}
