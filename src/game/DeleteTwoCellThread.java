package game;

public class DeleteTwoCellThread extends Thread {
	
	int[] p1 = new int[2];
	int[] p2 = new int[2];
	int[] p3 = new int[2];
	int[] p4 = new int[2];
	boolean useBomb;
	
	Cell fcell;
	Cell scell;
	
	public DeleteTwoCellThread(Cell firstCell, int[] fPoint, Cell secondCell, int[] sPoint,boolean useBomb) {
		p1[0] = UITool.getXLoc(firstCell) + (Const.btnWidth - Const.point.getIconWidth())/2;
		p1[1] = UITool.getYLoc(firstCell) + (Const.btnHeigth - Const.point.getIconHeight())/2;
		
		p2[0] = UITool.getXLoc(fPoint) + (Const.btnWidth - Const.point.getIconWidth())/2;
		p2[1] = UITool.getYLoc(fPoint) + (Const.btnHeigth - Const.point.getIconHeight())/2;
		
		p3[0] = UITool.getXLoc(sPoint) + (Const.btnWidth - Const.point.getIconWidth())/2;
		p3[1] = UITool.getYLoc(sPoint) + (Const.btnHeigth - Const.point.getIconHeight())/2;
		
		p4[0] = UITool.getXLoc(secondCell) + (Const.btnWidth - Const.point.getIconWidth())/2;
		p4[1] = UITool.getYLoc(secondCell) + (Const.btnHeigth - Const.point.getIconHeight())/2;
		
		this.useBomb = useBomb;
		this.fcell = firstCell;
		this.scell = secondCell;
	}

	@Override
	public void run() {
		UITool.paintLine(p1, p2);
		UITool.paintLine(p2, p3);
		UITool.paintLine(p3, p4);
		UITool.mySleep();
		
		if (useBomb) {
			MusicTool.playBomb();
		} else {
			MusicTool.playEarse();						
		}
		
		if (fcell.getIcon() == Cache.timeIcon) {
			Cache.timeHoldon = true;
			Cache.ui.addTime(20.0);
			Cache.timeHoldon = false;
		} else if (fcell.getIcon() == Cache.hintIcon) {
			Cache.ui.addHintNum(1);
		} else if (fcell.getIcon() == Cache.bombIcon) {
			Cache.ui.addBomb(1);
		} else if (fcell.getIcon() == Cache.refreshIcon) {
			Cache.ui.addRefresh(1);
		} else {
			Cache.timeHoldon = true;
			Cache.ui.addTime(2.0);
			Cache.timeHoldon = false;
		}
		Cache.ui.repaint();
		
		Cache.pointX = -1;
		Cache.pointY = -1;
		Cache.pointsForPaint.clear();
		
		if (Cache.firstCell != null) {
			Cache.firstCell.setVisible(false);
			Cache.cells[Cache.firstCell.getxPoint()][Cache.firstCell.getyPoint()] = null;
			Cache.firstCell.setBorder(null);
		}
		if (Cache.secondCell != null) {
			Cache.secondCell.setVisible(false);			
			Cache.cells[Cache.secondCell.getxPoint()][Cache.secondCell.getyPoint()] = null;
			Cache.secondCell.setBorder(null);
		}
		
		Cache.firstCell = null;
		Cache.secondCell = null;
		
		if (GameTool.checkSuccess()) {
			UITool.success();
		}
	}

}
