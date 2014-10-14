package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5829995984171437321L;

	JButton hint = null;
	JButton bomb = null;
	JButton refresh = null;
	JLabel time = null;
	JLabel timeBarBg = null;
	JLabel timeBar = null;
	
	JLabel hintTip2 = null;
	JLabel bombTip2 = null;
	JLabel refreshTip2 = null;
	public UI() {
		Cache.ui = this;
		this.setBackground(new Color(200060));
		this.setLayout(null);

		UITool.initGameCells();
		
		Font font = new Font("宋体",Font.BOLD,14);
		
		JLabel timeTip = new JLabel();
		timeTip.setIcon(new ImageIcon("img/1.gif"));
		timeTip.setBounds(750, 29, 28, 28);
		this.add(timeTip);
		JLabel timeTip2 = new JLabel("时间");
		timeTip2.setForeground(Color.WHITE);
		timeTip2.setFont(font);
		timeTip2.setBounds(788, 25, 30, 50);
		this.add(timeTip2);
		time = new JLabel(Const.time + "");
		time.setForeground(Color.WHITE);
		time.setFont(font);
		time.setBounds(830, 25, 60, 50);
		this.add(time);
		
		timeBarBg = new JLabel();
		timeBarBg.setIcon(new ImageIcon("img/timeBarGg.gif"));
		timeBarBg.setBounds(750, 60, 124, 20);
		timeBar = new JLabel();
		timeBar.setIcon(new ImageIcon("img/timeBar.gif"));
		timeBar.setBounds(752, 62, 120, 16);
		this.add(timeBar);
		this.add(timeBarBg);
		
		Font font2 = new Font("宋体",Font.BOLD,12);
		JLabel hintTip = new JLabel();
		hintTip.setIcon(new ImageIcon("img/0.gif"));
		hintTip.setBounds(750, 180, 28, 28);
		this.add(hintTip);
		hintTip2 = new JLabel("2");
		hintTip2.setForeground(Color.WHITE);
		hintTip2.setBounds(788, 180, 28, 28);
		this.add(hintTip2);
		hint = new JButton("提  示");
		hint.setFont(font2);
		hint.setBounds(750, 211, Const.userBtnWidth, Const.userBtnHeigth);
		this.add(hint);
		
		JLabel bombTip = new JLabel();
		bombTip.setIcon(new ImageIcon("img/3.gif"));
		bombTip.setBounds(750, 280, 28, 28);
		this.add(bombTip);
		bombTip2 = new JLabel("2");
		bombTip2.setForeground(Color.WHITE);
		bombTip2.setBounds(788, 280, 28, 28);
		this.add(bombTip2);
		bomb = new JButton("炸  弹");
		bomb.setFont(font2);
		bomb.setBounds(750, 311, Const.userBtnWidth, Const.userBtnHeigth);
		this.add(bomb);
		
		JLabel refreshTip = new JLabel();
		refreshTip.setIcon(new ImageIcon("img/2.gif"));
		refreshTip.setBounds(750, 380, 28, 28);
		this.add(refreshTip);
		refreshTip2 = new JLabel("2");
		refreshTip2.setForeground(Color.WHITE);
		refreshTip2.setBounds(788, 380, 28, 28);
		this.add(refreshTip2);
		refresh = new JButton("重新洗牌");
		refresh.setFont(font2);
		refresh.setBounds(750, 411, Const.userBtnWidth, Const.userBtnHeigth);
		this.add(refresh);
		
		hint.addActionListener(this);
		bomb.addActionListener(this);
		refresh.addActionListener(this);
		
		this.updateUI();
		Thread t = new TimeThread();
		t.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < Cache.pointsForPaint.size(); i++) {
			g.drawImage(Const.point.getImage(), Cache.pointsForPaint.get(i)[0], Cache.pointsForPaint.get(i)[1], null);			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if (s.equals("提  示")) {
			if (this.getHintNum() <= 0) {
				return;
			}
			UITool.showHint();
			this.lostHint(1);
		} else if (s.equals("炸  弹")) {
			if (this.getBombNum() <= 0) {
				return;
			}
			UITool.useBomb();
			this.lostBomb(1);
		} else if (s.equals("重新洗牌")) {
			if (this.getRefreshNum() <= 0) {
				return;
			}
			UITool.refresh();
			this.lostRefresh(1);
		}
	}
	
	public void setTimeLabel(double n) {
		n = Math.abs(n);
		DecimalFormat df = new DecimalFormat("0");
		time.setText(df.format(n));
		timeBar.setBounds(752, 62, (int)((n * 120)/Const.time), 16);
		this.add(timeBar);
		this.add(timeBarBg);
		this.updateUI();
	}
	
	public int getTimeNum() {
		return Integer.parseInt(time.getText());
	}
	
	public void addTime(double d) {
		double c = Cache.time;
		c = c + d;
		Cache.time = c;
	}
	
	public void addHintNum(int n) {
		int m = Integer.parseInt(hintTip2.getText());
		m = m + n;
		hintTip2.setText(m + "");
	}
	
	public void addBomb(int n) {
		int m = Integer.parseInt(bombTip2.getText());
		m = m + n;
		bombTip2.setText(m + "");
	}
	
	public void addRefresh(int n) {
		int m = Integer.parseInt(refreshTip2.getText());
		m = m + n;
		refreshTip2.setText(m + "");
	}
	
	public void lostHint(int n) {
		int m = Integer.parseInt(hintTip2.getText());
		m = m - n;
		hintTip2.setText(m + "");
	}
	
	public void lostBomb(int n) {
		int m = Integer.parseInt(bombTip2.getText());
		m = m - n;
		bombTip2.setText(m + "");
	}
	
	public void lostRefresh(int n) {
		int m = Integer.parseInt(refreshTip2.getText());
		m = m - n;
		refreshTip2.setText(m + "");
	}
	
	public int getHintNum() {
		return Integer.parseInt(hintTip2.getText());
	}
	
	public int getBombNum() {
		return Integer.parseInt(bombTip2.getText());
	}
	
	public int getRefreshNum() {
		return Integer.parseInt(refreshTip2.getText());
	}
}
