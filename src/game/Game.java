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
		
		this.setTitle("������v1.0 - Michael Wing");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

	}
	
	public void initMenu() {
		JMenuBar bar=new JMenuBar();
		JMenu game=new JMenu("��Ϸ");
		JMenu select = new JMenu("ѡ��");
		JMenu help=new JMenu("����");
		JMenuItem item;
		game.add(item=new JMenuItem("����"));item.addActionListener(this);
		game.addSeparator();
		ButtonGroup bg=new ButtonGroup();
		game.add(item=new JCheckBoxMenuItem("����"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("�м�"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("�߼�"));bg.add(item);item.addActionListener(this);
		game.add(item=new JCheckBoxMenuItem("�ظ�")).setSelected(true);bg.add(item);item.addActionListener(this);
		game.addSeparator();
		game.add(item=new JMenuItem("�˳�"));item.addActionListener(this);
		select.add(item=new JMenuItem("��ʾ"));item.addActionListener(this);
		select.add(item=new JMenuItem("ը��"));item.addActionListener(this);
		select.add(item=new JMenuItem("����ϴ��"));item.addActionListener(this);
		select.add(item=new JMenuItem("��ͣ"));item.addActionListener(this);
		select.addSeparator();
//		JMenu musicBg = new JMenu("��������");
//		ButtonGroup bg2=new ButtonGroup();
//		musicBg.add(item=new JCheckBoxMenuItem("���ֿ�")).setSelected(true);bg2.add(item);item.addActionListener(this);
//		musicBg.add(item=new JCheckBoxMenuItem("���ֹ�"));bg2.add(item);item.addActionListener(this);
//		select.add(musicBg);
		JMenu musicXg = new JMenu("��Ч����");
		ButtonGroup bg3=new ButtonGroup();
		musicXg.add(item=new JCheckBoxMenuItem("��Ч��")).setSelected(true);bg3.add(item);item.addActionListener(this);
		musicXg.add(item=new JCheckBoxMenuItem("��Ч��"));bg3.add(item);item.addActionListener(this);
		select.add(musicXg);
		help.add(item=new JMenuItem("����"));item.addActionListener(this);
		
		bar.add(game);
		bar.add(select);
		bar.add(help);
		
		this.setJMenuBar(bar);
	}
	
	/**
	 * ��ʼ������λ��
	 */
	public void initLocation() {
		int windowWidth = this.getWidth(); // ��ô��ڿ�
		int windowHeight = this.getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// ���ô��ھ�����ʾ
	}
	
	public void reStart() {
		MusicPlay.stop();
		Cache.timeRunning = false;// ��ֹԭ��ʱ������
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Cache.timeRunning = true;// �����¼�ʱ������
		
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
		if (s.equals("����")) {
			reStart();
		} else if (s.equals("����")) {
			Const.mapX = 6;
			Const.mapY = 6;
			reStart();
		} else if (s.equals("�м�")) {
			Const.mapX = 10;
			Const.mapY = 10;
			reStart();
		} else if (s.equals("�߼�")) {
			Const.mapX = 13;
			Const.mapY = 18;
			reStart();
		} else if (s.equals("�ظ�")) {
			Const.mapX = 15;
			Const.mapY = 20;
			this.setSize(900, 685);
			reStart();
		} else if (s.equals("�˳�")) {
			System.exit(0);
		} else if (s.equals("���ֿ�")) {
			if (!Cache.musicBgPlaying) {
				MusicPlay.play();
				Cache.musicBgPlaying = true;
			}
		} else if (s.equals("���ֹ�")) {
			if (Cache.musicBgPlaying) {
				MusicPlay.stop();
				Cache.musicBgPlaying = false;
			}
		} else if (s.equals("��Ч��")) {
			Cache.musicXgPlaying = true;
		} else if (s.equals("��Ч��")) {
			Cache.musicXgPlaying = false;
		} else if (s.equals("��ʾ")) {
			if (ui.getHintNum() <= 0) {
				return;
			}
			UITool.showHint();
			ui.lostHint(1);
		} else if (s.equals("ը��")) {
			if (ui.getBombNum() <= 0) {
				return;
			}
			UITool.useBomb();
			ui.lostBomb(1);
		} else if (s.equals("����ϴ��")) {
			if (ui.getRefreshNum() <= 0) {
				return;
			}
			UITool.refresh();
			ui.lostRefresh(1);
		} else if (s.equals("��ͣ")) {
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
