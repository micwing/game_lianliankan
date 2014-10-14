package game;

import java.applet.Applet;
import java.io.FileInputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MusicTool extends Applet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7837280345214215426L;
	
	static AudioStream bomb;
	static AudioStream earse;
	static AudioStream hint;
	static AudioStream refresh;
	static AudioStream select;
	static AudioStream start;
	static AudioStream end;
	static AudioStream notime;
	static AudioStream success;
	
	public static void playSuccess() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/success.wav", end);
	}
	
	public static void playNotime() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/notime.wav", end);
	}
	
	public static void playEnd() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/end.wav", end);
	}
	
	public static void playStart() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/start.wav", start);
	}
	
	public static void playBomb() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/bomb.wav", bomb);
	}
	
	public static void playEarse() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/earse.wav", earse);
	}
	
	public static void playHint() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/hint.wav", hint);
	}
	
	public static void playRefresh() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/refresh.wav", refresh);
	}
	
	public static void playSelect() {
		if (!Cache.musicXgPlaying) {return;}
		play("sound/select.wav", select);
	}

	public static void play(String path, AudioStream as) {
		try {
			FileInputStream fileau = new FileInputStream(path);
			as = new AudioStream(fileau);
			AudioPlayer.player.start(as);
			
		} catch (Exception e) {}
	}
}
