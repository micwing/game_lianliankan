package game;

import java.util.ArrayList;
import java.util.List;

public class GameTool {
	
	public static boolean checkSuccess() {
		Cell[][] cells = Cache.cells;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static List<Cell> getCouldDeleteCell() {
		List<Cell> list = new ArrayList<Cell>();
		
		Cell fcell = null;
		Cell scell = null;
		Cell[][] cells = Cache.cells;
		Loop:for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] == null) {
					continue;
				}
				fcell = cells[i][j];
				for (int m = 0; m < cells.length; m++) {
					for (int n = 0; n < cells[0].length; n++) {
						if (cells[m][n] == null) {
							continue;
						}
						if (cells[m][n].getIcon() != fcell.getIcon()) {
							continue;
						}
						if (cells[m][n] == fcell) {
							continue;
						}
						if (isCouldDelete(fcell, cells[m][n])) {
							scell = cells[m][n];
							break Loop;
						}
					}
				}
			}
		}
		
		list.add(fcell);
		list.add(scell);
		return list;
	}

	public static boolean isCouldDelete(Cell firstCell, Cell secondCell) {
		if (firstCell.getIcon() != secondCell.getIcon()) {
			return false;
		}
		for (Cell c : getFourRoundCell(firstCell)) {
			if (c == secondCell) {
				int[] fArray = {firstCell.getxPoint(), firstCell.getyPoint()};
				int[] sArray = {secondCell.getxPoint(), secondCell.getyPoint()};
				Cache.firstFollowPoint = fArray;
				Cache.secondFollowPoint = sArray;
				return true;
			}
		}
		List<int[]> fRound = getRoundCell(firstCell);
		List<int[]> sRound = getRoundCell(secondCell);
		for (int[] fArray : fRound) {
			for (int[] sArray : sRound) {
				if (onReachLine(fArray, sArray)) {
					Cache.firstFollowPoint = fArray;
					Cache.secondFollowPoint = sArray;
					
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<Cell> getFourRoundCell(Cell cell) {
		List<Cell> cells = new ArrayList<Cell>();
		int x = cell.getxPoint();
		int y = cell.getyPoint();
		if (x - 1 > 0) {
			cells.add(Cache.cells[x - 1][y]);
		}
		if (x + 1 < Cache.cells.length) {
			cells.add(Cache.cells[x + 1][y]);
		}
		if (y - 1 > 0) {
			cells.add(Cache.cells[x][y - 1]);
		}
		if (y + 1 < Cache.cells[0].length) {
			cells.add(Cache.cells[x][y + 1]);
		}
		return cells;
	}
	
	public static boolean onReachLine(int[] fArray, int[] sArray) {
		// 两点在同一行
		if (fArray[0] == sArray[0]) {
			// 也在同一列，那么是同一个点
			if (fArray[1] == sArray[1]) {
				return true;
			}
			int small = fArray[1];
			int big =  sArray[1];
			if (fArray[1] > sArray[1]) {
				big = fArray[1];
				small = sArray[1];
			}
			for (int i = 1; i < big - small; i++) {
				if (Cache.cells[fArray[0]][small + i] != null) {
					return false;
				}
			}
			return true;
		}
		// 两点在同一列
		if (fArray[1] == sArray[1]) {
			int small = fArray[0];
			int big =  sArray[0];
			if (fArray[0] > sArray[0]) {
				big = fArray[0];
				small = sArray[0];
			}
			for (int i = 1; i < big - small; i++) {
				if (Cache.cells[small + i][fArray[1]] != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static List<int[]> getRoundCell(Cell cell) {
		List<int[]> list = new ArrayList<int[]>();
		int x = cell.getxPoint();
		int y = cell.getyPoint();

		// 向上找
		for (int i = 1; i < Cache.cells.length; i++) {
			if (x - i >= 0 && Cache.cells[x - i][y] == null) {
				int [] arr = new int[2];
				arr[0] = x - i;
				arr[1] = y;
				list.add(arr);
			} else {
				break;
			}
		}
		// 向下找
		for (int i = 1; i < Cache.cells.length; i++) {
			if (x + i < Cache.cells.length && Cache.cells[x + i][y] == null) {
				int [] arr = new int[2];
				arr[0] = x + i;
				arr[1] = y;
				list.add(arr);
			} else {
				break;
			}
		}
		// 向左找
		for (int i = 1; i < Cache.cells[0].length; i++) {
			if (y - i >= 0 && Cache.cells[x][y - i] == null) {
				int [] arr = new int[2];
				arr[0] = x;
				arr[1] = y - i;
				list.add(arr);
			} else {
				break;
			}
		}
		// 向右找
		for (int i = 1; i < Cache.cells.length; i++) {
			if (y + i < Cache.cells[0].length && Cache.cells[x][y + i] == null) {
				int [] arr = new int[2];
				arr[0] = x;
				arr[1] = y + i;
				list.add(arr);
			} else {
				break;
			}
		}
		return list;
	}
}
