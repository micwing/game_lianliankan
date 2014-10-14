package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

public class CellActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MusicTool.playSelect();
		Cell cell = (Cell) e.getSource();
		cell.setBorder(new LineBorder(Color.RED, 3));

		if (Cache.firstCell == null || Cache.firstCell == cell) {
			Cache.firstCell = cell;
		} else {
			Cache.secondCell = cell;
			if (GameTool.isCouldDelete(Cache.firstCell, Cache.secondCell)) {
				UITool.paintFollowPoint(Cache.firstCell, Cache.firstFollowPoint, Cache.secondCell, Cache.secondFollowPoint, false);
			} else {
				Cache.firstCell.setBorder(null);
				Cache.secondCell.setBorder(null);
				Cache.firstCell = null;
			}
			
		}
	}

}
