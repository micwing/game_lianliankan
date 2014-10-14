package game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Cache {
	
	static UI ui = null;

	static Cell[][] cells = new Cell[Const.mapX + 2][Const.mapY + 2];
	static List<Cell> tempCells = new ArrayList<Cell>();
	
	static Cell firstCell = null;
	static Cell secondCell = null;
	
	static int[] firstFollowPoint = null;
	static int[] secondFollowPoint = null;
	
	static int pointX = -1;
	static int pointY = -1;

	static List<int[]> pointsForPaint = new ArrayList<int[]>();
	
	static boolean musicBgPlaying = true;
	static boolean musicXgPlaying = true;
	
	static double time = 0;
	static boolean timeRunning = true;
	static boolean timeHoldon = false;
	static boolean timeAlamePlayed = false;
	
	static ImageIcon hintIcon = null;
	static ImageIcon bombIcon = null;
	static ImageIcon refreshIcon = null;
	static ImageIcon timeIcon = null;
	
	public static void reSet() {
		cells = new Cell[Const.mapX + 2][Const.mapY + 2];
		tempCells = new ArrayList<Cell>();
		
		firstCell = null;
		secondCell = null;
		
		firstFollowPoint = null;
		secondFollowPoint = null;
		
		timeAlamePlayed = false;
	}
}
