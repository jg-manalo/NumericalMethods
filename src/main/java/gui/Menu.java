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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Menu() {
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
		
		//back button
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(-28, 11, 148, 80);
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
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		//bisection button
		JLabel bisect = new JLabel("Bisection");
		bisect.setBounds(42, 508, 129, 70);
		bisect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bisect.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        Bisection bct = new Bisection();
		        bct.setVisible(true);
		        dispose();
		    }
		});
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/bct.png"));
		bisect.setIcon(icon);
		contentPane.add(bisect);
		
		//moss button
		JLabel moss = new JLabel("");
		moss.setBounds(255, 495, 192, 96);
		moss.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moss.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MOSS moss = new MOSS();
		        moss.setVisible(true);
		        dispose();
			}
		});
		ImageIcon mossIcon = new ImageIcon(this.getClass().getResource("/moss.png"));
		moss.setIcon(mossIcon);
		contentPane.add(moss);
		
		//newton raphson button
		JLabel newraph = new JLabel("");
		newraph.setBounds(491, 482, 192, 109);
		newraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Newraphson newraph = new Newraphson();
		        newraph.setVisible(true);
		        dispose();
			}
		});
		ImageIcon newIcon = new ImageIcon(this.getClass().getResource("/newraph.png"));
		newraph.setIcon(newIcon);
		contentPane.add(newraph);
		
		//gaussian elims
		JLabel gaussian = new JLabel("");
		gaussian.setBounds(733, 482, 198, 131);
		gaussian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gaussian.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gaussian gaussian = new Gaussian();
		        gaussian.setVisible(true);
		        dispose();
			}
		});
		ImageIcon gaussianIcon = new ImageIcon(this.getClass().getResource("/gaussian.png"));
		gaussian.setIcon(gaussianIcon);
		contentPane.add(gaussian);
		
		//gauss jordan
		JLabel gaussjordan = new JLabel("");
		gaussjordan.setBounds(970, 508, 206, 103);
		gaussjordan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gaussjordan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gaussjordan gaussjordan = new Gaussjordan();
		        gaussjordan.setVisible(true);
		        dispose();
			}
		});
		ImageIcon gaussjordanIcon = new ImageIcon(this.getClass().getResource("/gaussjordan.png"));
		gaussjordan.setIcon(gaussjordanIcon);
		contentPane.add(gaussjordan);
		
		
		//simpsons
		JLabel simpsons = new JLabel("Simpsons");
		simpsons.setBounds(52, 597, 255, 109);
		simpsons.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		simpsons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Simpson simpsons = new Simpson();
				simpsons.setVisible(true);
				dispose();
			}
		});
		ImageIcon simpsonsIcon = new ImageIcon(this.getClass().getResource("/simpsonsBt.png"));
		simpsons.setIcon(simpsonsIcon);
		contentPane.add(simpsons);
		
		
		//Trapezoidal
		JLabel trapezoidal = new JLabel("trapezoidal");
		trapezoidal.setBounds(389, 607, 180, 89);
		trapezoidal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		trapezoidal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TrapezoidalRule trapezoidal = new TrapezoidalRule();
				trapezoidal.setVisible(true);
				dispose();
			}
		});
		ImageIcon trapezoidalIcon = new ImageIcon(this.getClass().getResource("/trapezoidalBtn.png"));
		trapezoidal.setIcon(trapezoidalIcon);
		contentPane.add(trapezoidal);
		
		
		//Non Linear
		JLabel nonLinear = new JLabel("nonLinear ");
		nonLinear.setBounds(647, 613, 192, 93);
		nonLinear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nonLinear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NonLinear nonLinear = new NonLinear();
				nonLinear.setVisible(true);
				dispose();
			}
		});
		ImageIcon nonLinearIcon = new ImageIcon(this.getClass().getResource("/nonLinearBt.png"));
		nonLinear.setIcon(nonLinearIcon);
		contentPane.add(nonLinear);

		
		//jacobi
		JLabel jacobi = new JLabel("");
		jacobi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jacobi.setBounds(933, 626, 129, 70);
		jacobi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Jacobi jacobi = new Jacobi();
				jacobi.setVisible(true);
				dispose();
			}
		});
		ImageIcon jacobiIcon = new ImageIcon(this.getClass().getResource("/jacobi.png"));
		jacobi.setIcon(jacobiIcon);
		contentPane.add(jacobi);
		
		
		//menu wall
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 1200, 720);
		ImageIcon labelNewIcon_1 = new ImageIcon(this.getClass().getResource("/menu.gif"));
		lblNewLabel_1.setIcon(labelNewIcon_1);
		contentPane.add(lblNewLabel_1);
	
	}
}
