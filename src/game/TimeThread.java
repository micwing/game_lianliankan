package game;

public class TimeThread extends Thread {

	@Override
	public void run() {
		Cache.time = Const.time;
		while (Cache.timeRunning) {
			if (Cache.timeHoldon) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			if (Cache.time > Const.time) {
				Cache.time = Const.time;
			}
			if (Cache.time < 10 && Cache.time > 8) {
				if (!Cache.timeAlamePlayed) {
					MusicTool.playNotime();
					Cache.timeAlamePlayed = true;					
				}
			}
			Cache.ui.setTimeLabel(Cache.time);
			if (Cache.time <= 0) {
				UITool.fail();
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Cache.time = Cache.time - 0.1;
		}
	}
}
