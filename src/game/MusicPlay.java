package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sun.audio.AudioPlayer;

public class MusicPlay {

	private static InputStream inputStream = null;

	public MusicPlay() {
	}

	public static void play() {
		try {
			inputStream = new FileInputStream(new File(".\\sound\\bg.mid"));
			AudioPlayer.player.start(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void stop() {
		System.out.println(inputStream);
		if (inputStream == null) {return;}
		AudioPlayer.player.stop(inputStream);
	}

}
