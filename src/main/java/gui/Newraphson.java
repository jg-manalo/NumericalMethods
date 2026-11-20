package gui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import rootFinding.NewtonRaphson;
import userIO.GUIHelper;
import java.awt.Font;
import java.awt.Color;

public class Newraphson extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField functionTbx;
	private JTextField assumptionTbx;
	private JTextField thresholdTbx;


	public Newraphson() {
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
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(23, -15, 164, 137);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/specialback.png"));
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(548, 165, 553, 425);
		contentPane.add(scrollPane);
		
		JTextPane resultsPane = new JTextPane();
		resultsPane.setEditable(false);
		scrollPane.setViewportView(resultsPane);
		
		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.addActionListener(e -> {
		    String function = functionTbx.getText().trim();
		    String initialGuess = assumptionTbx.getText().trim();
		    String thresholdStr = thresholdTbx.getText().trim();
		    
		    try {
		    	
		    	String displayString = "";
		    	NewtonRaphson nr = new NewtonRaphson(function, initialGuess, thresholdStr);
		    	nr.processOutput();
		    	while(!GUIHelper.isMessagesEmpty()) {
		    		displayString += GUIHelper.receive();
		    	}
		    	resultsPane.setText(displayString);
		    	
		    	JTextPane resultHeader = new JTextPane();
		    	resultHeader.setBackground(new Color(209, 209, 209));
		    	resultHeader.setText("RESULT");
		    	resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		    	resultHeader.setEditable(false);
		    	scrollPane.setColumnHeaderView(resultHeader);
		    } catch (Exception ex) {
		    	thresholdTbx.setText(ex.getMessage());
		    	assumptionTbx.setText(ex.getMessage());
		    	functionTbx.setText(ex.getMessage());
		    }
		    
		});
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBounds(221, 538, 139, 52);
		contentPane.add(solveBtn);
		
		thresholdTbx = new JTextField();
		thresholdTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		thresholdTbx.setColumns(10);
		thresholdTbx.setBounds(221, 476, 182, 30);
		contentPane.add(thresholdTbx);
		
		assumptionTbx = new JTextField();
		assumptionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		assumptionTbx.setColumns(10);
		assumptionTbx.setBounds(221, 368, 182, 30);
		contentPane.add(assumptionTbx);
		
		functionTbx = new JTextField();
		functionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		functionTbx.setBounds(221, 262, 182, 30);
		contentPane.add(functionTbx);
		functionTbx.setColumns(10);
		
		JLabel scrollPaneBg = new JLabel(new ImageIcon(getClass().getResource("/scrollPaneBg.png")));
		scrollPaneBg.setBounds(533, 150, 585, 456);
		contentPane.add(scrollPaneBg);
		
		JLabel thresholdLabel = new JLabel("Threshold:");
		thresholdLabel.setForeground(new Color(255, 255, 255));
		thresholdLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		thresholdLabel.setBounds(221, 455, 114, 14);
		contentPane.add(thresholdLabel);
		
		JLabel inputBox2 = new JLabel(new ImageIcon(getClass().getResource("/inputBox2.png")));
		inputBox2.setBounds(204, 439, 220, 82);
		contentPane.add(inputBox2);
		
		JLabel assumptionLabel = new JLabel("Assumption:");
		assumptionLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		assumptionLabel.setForeground(new Color(255, 255, 255));
		assumptionLabel.setBounds(221, 349, 164, 14);
		contentPane.add(assumptionLabel);
		
		JLabel inputBox3 = new JLabel(new ImageIcon(getClass().getResource("/inputBox3.png")));
		inputBox3.setBounds(204, 334, 220, 82);
		contentPane.add(inputBox3);
		
		JLabel functionLabel = new JLabel("Function:");
		functionLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		functionLabel.setBounds(221, 244, 114, 14);
		contentPane.add(functionLabel);
		
		JLabel inputBox = new JLabel(new ImageIcon(getClass().getResource("/inputBox.png")));
		inputBox.setBounds(204, 231, 220, 82);
		contentPane.add(inputBox);
		
		JLabel bgDim = new JLabel(new ImageIcon(getClass().getResource("/dim.png")));
		bgDim.setBounds(0, 0, 1200, 720);
		
		
		JLabel wall = new JLabel("");
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/newtonRaphsonWp.gif"));
		wall.setIcon(wp);
		
		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewtonRaphsonInfo info = new NewtonRaphsonInfo();
				info.setVisible(true);
				dispose();
			}
		});
		ImageIcon infoicon = new ImageIcon(this.getClass().getResource("/info.png"));
		info.setIcon(infoicon);
		contentPane.add(info);
		contentPane.add(bgDim);
		contentPane.add(wall);
		
		contentPane.add(wall);
	}

}
