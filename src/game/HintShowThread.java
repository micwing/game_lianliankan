package game;

import java.awt.Color;

import javax.swing.border.LineBorder;

public class HintShowThread extends Thread {
	Cell fcell;
	Cell scell;
	
	public HintShowThread(Cell fcell, Cell scell) {
		this.fcell = fcell;
		this.scell = scell;
	}

	@Override
	public void run() {
		MusicTool.playHint();
		for (int i = 0; i < 6; i++) {
			fcell.setBorder(new LineBorder(Color.GREEN, 3));
			scell.setBorder(new LineBorder(Color.GREEN, 3));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fcell.setBorder(new LineBorder(Color.BLUE, 3));
			scell.setBorder(new LineBorder(Color.BLUE, 3));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fcell.setBorder(null);
		scell.setBorder(null);		
	}
}
