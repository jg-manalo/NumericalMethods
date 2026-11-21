package gui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import org.mariuszgromada.math.mxparser.License;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static BackgroundMusic bgMusic;
	private static ExitSound exitSound;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					License.iConfirmNonCommercialUse("confirm");
					bgMusic = new BackgroundMusic();
					bgMusic.playOnLoop();

		            try {
		                exitSound = new ExitSound();
		            } catch (Exception ex) {
		                ex.printStackTrace();   
		            }
					Home frame = new Home();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Home() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		setTitle("Numerical Methods");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 1200, 720);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
				
		Image iconImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/appIcon.png"));
		setIconImage(iconImage);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(""); //start
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartSound startSound;
				try {
					startSound = new StartSound();
					startSound.play();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setBounds(807, 418, 209, 108);
		
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/start.png"));
		
		
		lblNewLabel.setIcon(labelNewIcon);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblNewLabel);
		
		JLabel info = new JLabel("");
		info.setBounds(0, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Info info = new Info();
				info.setVisible(true);
				dispose();
			}
		});
		ImageIcon infoicon = new ImageIcon(this.getClass().getResource("/info.png"));
		info.setIcon(infoicon);
		contentPane.add(info);
		
		JLabel exit = new JLabel();
		exit.setBounds(0, 67, 72, 56);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
		        closeApplication();
			}
		});
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/exit.png"));
		exit.setIcon(icon);
		contentPane.add(exit);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(0, 0, 1200, 720);
		ImageIcon labelNewIcon_3 = new ImageIcon(this.getClass().getResource("/nms.gif"));
		lblNewLabel_3.setIcon(labelNewIcon_3);
		contentPane.add(lblNewLabel_3);
	}
	
	private static void closeApplication() {
	    Runnable quit = () -> {
	        Thread watchdog = new Thread(() -> {
	            try {
	                Thread.sleep(3000);
	            } catch (InterruptedException ignored) { }
	            Runtime.getRuntime().halt(0);
	        });
	        watchdog.setDaemon(true);
	        watchdog.start();

	        if (bgMusic != null) {
	            bgMusic.stop();
	        }
	        for (java.awt.Window w : java.awt.Window.getWindows()) {
	            w.dispose();
	        }
	        System.exit(0);
	    };

	    if (exitSound != null) {
	        exitSound.playThen(quit);
	    } else {
	        quit.run();
	    }
	}
	
	
}
