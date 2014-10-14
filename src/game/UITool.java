package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UITool {
	
	public static void holdon() {
		Cache.timeHoldon = true;
		JOptionPane.showMessageDialog(Cache.ui, "                暂停中...\n点击“确定”继续游戏");
		Cache.timeHoldon = false;
	}
	
	public static void success() {
		Cache.timeRunning = false;
		MusicPlay.stop();
		MusicTool.playSuccess();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cache.ui.removeAll();
		Cache.ui.updateUI();
		
		JLabel j = new JLabel();
		j.setIcon(new ImageIcon("img/success.jpg"));
		j.setBounds(150, 130, 605, 377);
		Cache.ui.add(j);
		
		Cache.ui.updateUI();
	}
	
	public static void fail() {
		MusicPlay.stop();
		MusicTool.playEnd();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cache.ui.removeAll();
		Cache.ui.updateUI();
		
		JLabel j = new JLabel();
		j.setIcon(new ImageIcon("img/fail.jpg"));
		j.setBounds(150, 130, 604, 377);
		Cache.ui.add(j);
		
		Cache.ui.updateUI();
	}
	
	public static void refresh() {
		Cache.tempCells.clear();
		
		Cell[][] cells = Cache.cells;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] == null) {
					continue;
				}
				Cache.tempCells.add(cells[i][j]);
			}
		}
		initRandomCells();
		MusicTool.playRefresh();
		Cache.ui.updateUI();
	}
	
	public static void useBomb() {
		List<Cell> list = GameTool.getCouldDeleteCell();
		if (list.get(0) == null || list.get(1) == null) {
			JOptionPane.showMessageDialog(Cache.ui, "没有可以消除的啦");
		} else {
			Cache.firstCell = list.get(0);
			Cache.secondCell = list.get(1);
			UITool.paintFollowPoint(Cache.firstCell, Cache.firstFollowPoint, Cache.secondCell, Cache.secondFollowPoint, true);
		}
	}
	
	public static void showHint() {
		List<Cell> list = GameTool.getCouldDeleteCell();
		if (list.get(0) == null || list.get(1) == null) {
			JOptionPane.showMessageDialog(Cache.ui, "没有可以消除的啦");
		} else {
			Thread t = new HintShowThread(list.get(0), list.get(1));
			t.start();
		}
	}

	public static void initGameCells() {
		
		int x = Const.mapLeftBorder;
		int y = Const.mapUpBorder;
		for (int i = 0; i < Cache.cells.length; i++) {
			for (int j = 0; j < Cache.cells[i].length; j++) {
				if (i == 0 || i == Cache.cells.length - 1 || j == 0 || j == Cache.cells[i].length - 1) {
					Cache.cells[i][j] = null;
				} else {
					Cell cell = new Cell(i, j);
					Cache.cells[i][j] = cell;
					Cache.tempCells.add(cell);
					cell.addActionListener(new CellActionListener());
					cell.setBounds(x, y, Const.btnWidth, Const.btnHeigth);
					Cache.ui.add(cell);
				}
				x = x + Const.btnWidth;
			}
			x = Const.mapLeftBorder;
			y = y + Const.btnHeigth;
		}
		
		initRandomCells();
	}
	
	public static void initRandomCells() {
		List<Cell> cells = Cache.tempCells;
		List<ImageIcon> allImages = getAllImage();
		while(cells.size() != 0) {
			Random r = new Random();
			Cell c1 = cells.get(0);
			Cell c2 = null;
			while (c2 == null || c1 == c2) {
				c2 = cells.get(r.nextInt(cells.size()));
			}
			Icon ic = allImages.get(r.nextInt(Const.imageFileSum));
			c1.setIcon(ic);
			c2.setIcon(ic);
			Cache.tempCells.remove(c1);
			Cache.tempCells.remove(c2);
		}
	}
	
	public static List<ImageIcon> getAllImage() {
		List<ImageIcon> list = new ArrayList<ImageIcon>();
		for (int i = 0; i < Const.imageFileSum; i++) {
			ImageIcon ii = new ImageIcon("img/" + i + "." + Const.imageFileType);
			list.add(ii);
			if (i == 0) {
				Cache.hintIcon = ii;
			} else if (i == 1) {
				Cache.timeIcon = ii;
			} else if (i == 2) {
				Cache.refreshIcon = ii;
			} else if (i == 3) {
				Cache.bombIcon = ii;
			}
		}
		
		return list;
	}
	
	public static void paintFollowPoint(Cell firstCell, int[] fPoint, Cell secondCell, int[] sPoint, boolean useBomb) {
		Thread t = new DeleteTwoCellThread(firstCell, fPoint, secondCell, sPoint, useBomb);
		t.start();
	}
	
	public static void paintLine(int[] a, int[] b) {
		if (a[0] == b[0]) {
			Cache.pointX = a[0];
			if (a[1] < b[1]) {
				for (int i = 0; i < b[1] - a[1]; i = i + 4) {
					Cache.pointY = a[1] + i;
					int[] arr = {Cache.pointX,Cache.pointY};
					Cache.pointsForPaint.add(arr);
				}
			} else {
				for (int i = 0; i < a[1] - b[1]; i = i + 4) {
					Cache.pointY = a[1] - i;
					int[] arr = {Cache.pointX,Cache.pointY};
					Cache.pointsForPaint.add(arr);
				}
			}
		} else if(a[1] == b[1]) {
			Cache.pointY = a[1];
			if (a[0] < b[0]) {
				for (int i = 0; i < b[0] - a[0]; i = i + 4) {
					Cache.pointX = a[0] + i;
					int[] arr = {Cache.pointX,Cache.pointY};
					Cache.pointsForPaint.add(arr);
				}
			} else {
				for (int i = 0; i < a[0] - b[0]; i = i + 4) {
					Cache.pointX = a[0] - i;
					int[] arr = {Cache.pointX,Cache.pointY};
					Cache.pointsForPaint.add(arr);
				}
			}
		}
	}
	
	public static void mySleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getXLoc(Cell c) {
		int n = c.getyPoint();
		return Const.mapLeftBorder + Const.btnWidth * n;
	}
	
	public static int getYLoc(Cell c) {
		int n = c.getxPoint();
		return Const.mapUpBorder + Const.btnHeigth * n;
	}
	
	public static int getXLoc(int[] a) {
		int n = a[1];
		return Const.mapLeftBorder + Const.btnWidth * n;
	}
	
	public static int getYLoc(int[] a) {
		int n = a[0];
		return Const.mapUpBorder + Const.btnHeigth * n;
	}
}
