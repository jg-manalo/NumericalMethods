package gui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Info extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public Info() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		setUndecorated(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Image iconImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/appIcon.png"));
		setIconImage(iconImage);

		setContentPane(contentPane);
		

		JButton devBtn = new JButton(new ImageIcon(getClass().getResource("/devoraMsgBtn.png")));
		devBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String message ="<html><body width='%1s'><h1>Devora's Message</h1>"
						+ "<p>Ma'am Sir ipasa nyo po kami pls ty :))"
		                + "<br><br><br>";
				int  w = 400;
				int h = 800;
				
              JOptionPane.showMessageDialog(Info.this, String.format(message, w, h));
			}
		});
		devBtn.setBounds(23, 162, 267, 401);
		devBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(devBtn);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(21, 11, 128, 80);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home main;
				try {
					main = new Home();
					main.setVisible(true);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/back.png"));
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
			
		
		JButton joaquinBtn = new JButton(new ImageIcon(getClass().getResource("/joaquinMsgBtn.png")));
		joaquinBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String message ="<html><body width='%1s'><h1>Joaquin's Message</h1>"
						+ "<br><br>"
						+ "<p><h2>BEEP BEEP BOOP BOP...</h2>"
						+ "<br><br><br><br>";
				int  w = 400;
				int h = 800;
				
              JOptionPane.showMessageDialog(Info.this, String.format(message, w, h));
			}
		});
		joaquinBtn.setBounds(319, 162, 267, 401);
		joaquinBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(joaquinBtn);
		
		
		JButton lemBtn = new JButton(new ImageIcon(getClass().getResource("/lemMsgBtn.png")));
		lemBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String message ="<html><body width='%1s'><h1>Leimrei's Message</h1>"
		                + "k"
		                + "<br><br>";
				int  w = 400;
				int h = 800;
				
              JOptionPane.showMessageDialog(Info.this, String.format(message, w, h));
			}
		});
		lemBtn.setBounds(614, 162, 267, 401);
		lemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lemBtn);
		
		
		
		JButton charlesBtn = new JButton(new ImageIcon(getClass().getResource("/charlesMsgBtn.png")));
		charlesBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String message ="<html><body width='%1s'><h1>Charles' Message</h1>"
						+ "<br>"
						+ "<p>Just a quick recognition to our esteemed Professors for throwing us into the coding deep end with this project!✨<br>"
						+ "<br>"
		                + "Seriously though, almost everything on this project was a first-time experience for me, and let's just say it's been a wild ride. From deciphering cryptic requirements to learning Jswing from scratch, it's been like a crash course in \"How to Survive your 2nd year on BSCPE 101.\""
		                + " But hey, who needs a comfort zone anyway, am I right? Thanks to our professors' ingenious challenge, I've leveled up my skills faster than you can say.<br>"
		                + "<br>And to my team members! bro, y'all are the best! work ethics↑↑↑. <br>To Linguini, Bini Maloi, and Mikha Lem. Thank you and Cheers!!🥂<br><br>"
		                + "<p>From Your OA madaldal na Neighborhood FrontEnd Pixel-man."
		                + "<br>"
		                + "<h3>AD ASTRA PER ASPERA!🚀</h3>"
		                + "<br><br>";
				int  w = 400;
				int h = 800;
				
              JOptionPane.showMessageDialog(Info.this, String.format(message, w, h));
			}
		});
		charlesBtn.setBounds(909, 162, 267, 401);
		charlesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(charlesBtn);
		
		JLabel devInfoBg = new JLabel(new ImageIcon(getClass().getResource("/devInfoBg.png")));
		devInfoBg.setBounds(0, 0, 1200, 720);
		contentPane.add(devInfoBg);
	}
}

