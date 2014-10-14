package game;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Game extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8448608675471660238L;
	
	UI ui;
	public Game() {
		ui = new UI();
		init();
	}
	
	/**
	 * 
	 */
	public void init() {

//		this.add(ui);
		
		initMenu();
		this.setSize(900, 685);
		initLocation();
		
		this.setTitle("连连看v1.0 - Michael Wing");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

	}
	
	public void initMenu() {
		JMenuBar bar=new JMenuBar();
		JMenu game=new JMenu("游戏");
		JMenu select = new JMenu("选项");
		JMenu help=new JMenu("帮助");
		JMenuItem item;
		game.add(item=new JMenuItem("开局"));item.addActionListener(this);
		game.addSeparator();
		ButtonGroup bg=new ButtonGroup();
		game.add(item=new JCheckBoxMenuItem("初级"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("中级"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("高级"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("特高")).setSelected(true);bg.add(item);item.addActionListener(this);
		game.addSeparator();
		game.add(item=new JMenuItem("退出"));item.addActionListener(this);
		select.add(item=new JMenuItem("提示"));item.addActionListener(this);
		select.add(item=new JMenuItem("炸弹"));item.addActionListener(this);
		select.add(item=new JMenuItem("重新洗牌"));item.addActionListener(this);
		select.add(item=new JMenuItem("暂停"));item.addActionListener(this);
		select.addSeparator();
//		JMenu musicBg = new JMenu("背景音乐");
//		ButtonGroup bg2=new ButtonGroup();
//		musicBg.add(item=new JCheckBoxMenuItem("音乐开")).setSelected(true);bg2.add(item);item.addActionListener(this);
//		musicBg.add(item=new JCheckBoxMenuItem("音乐关"));bg2.add(item);item.addActionListener(this);
//		select.add(musicBg);
		JMenu musicXg = new JMenu("音效声音");
		ButtonGroup bg3=new ButtonGroup();
		musicXg.add(item=new JCheckBoxMenuItem("音效开")).setSelected(true);bg3.add(item);item.addActionListener(this);
		musicXg.add(item=new JCheckBoxMenuItem("音效关"));bg3.add(item);item.addActionListener(this);
		select.add(musicXg);
		help.add(item=new JMenuItem("关于"));item.addActionListener(this);
		
		bar.add(game);
		bar.add(select);
		bar.add(help);
		
		this.setJMenuBar(bar);
	}
	
	/**
	 * 初始化窗口位置
	 */
	public void initLocation() {
		int windowWidth = this.getWidth(); // 获得窗口宽
		int windowHeight = this.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// 设置窗口居中显示
	}
	
	public void reStart() {
		MusicPlay.stop();
		Cache.timeRunning = false;// 禁止原计时器运行
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cache.timeRunning = true;// 允许新计时器运行
		
//		if (Cache.musicBgPlaying) {
//			MusicPlay.play();
//		}
		this.remove(Cache.ui);
		Cache.reSet();
		MusicTool.playStart();
		ui = new UI();
		this.add(ui);
		ui.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("开局")) {
			reStart();
		} else if (s.equals("初级")) {
			Const.mapX = 6;
			Const.mapY = 6;
			reStart();
		} else if (s.equals("中级")) {
			Const.mapX = 10;
			Const.mapY = 10;
			reStart();
		} else if (s.equals("高级")) {
			Const.mapX = 13;
			Const.mapY = 18;
			reStart();
		} else if (s.equals("特高")) {
			Const.mapX = 15;
			Const.mapY = 20;
			this.setSize(900, 685);
			reStart();
		} else if (s.equals("退出")) {
			System.exit(0);
		} else if (s.equals("音乐开")) {
			if (!Cache.musicBgPlaying) {
				MusicPlay.play();
				Cache.musicBgPlaying = true;
			}
		} else if (s.equals("音乐关")) {
			if (Cache.musicBgPlaying) {
				MusicPlay.stop();
				Cache.musicBgPlaying = false;
			}
		} else if (s.equals("音效开")) {
			Cache.musicXgPlaying = true;
		} else if (s.equals("音效关")) {
			Cache.musicXgPlaying = false;
		} else if (s.equals("提示")) {
			if (ui.getHintNum() <= 0) {
				return;
			}
			UITool.showHint();
			ui.lostHint(1);
		} else if (s.equals("炸弹")) {
			if (ui.getBombNum() <= 0) {
				return;
			}
			UITool.useBomb();
			ui.lostBomb(1);
		} else if (s.equals("重新洗牌")) {
			if (ui.getRefreshNum() <= 0) {
				return;
			}
			UITool.refresh();
			ui.lostRefresh(1);
		} else if (s.equals("暂停")) {
			UITool.holdon();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();

	}

}
