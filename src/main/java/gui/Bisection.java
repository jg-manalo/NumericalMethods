package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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

import rootFinding.BisectionMethod;
import userIO.GUIHelper;

public class Bisection extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField functionTbx;
	private JTextField assumptionATbx;
	private JTextField assumptionBTbx;
	private JTextField thresholdTbx;


	public Bisection() {
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
		lblNewLabel.setBounds(0, 11, 128, 80);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		
		
		thresholdTbx = new JTextField();
		thresholdTbx.setBounds(77, 494, 182, 30);
		thresholdTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		thresholdTbx.setColumns(10);
		contentPane.add(thresholdTbx);
		
		assumptionBTbx = new JTextField();
		assumptionBTbx.setBounds(309, 399, 182, 30);
		assumptionBTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		assumptionBTbx.setColumns(10);
		contentPane.add(assumptionBTbx);
		
		assumptionATbx = new JTextField();
		assumptionATbx.setBounds(77, 399, 182, 30);
		assumptionATbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		assumptionATbx.setColumns(10);
		contentPane.add(assumptionATbx);
		
		functionTbx = new JTextField();
		functionTbx.setBounds(77, 298, 182, 30);
		functionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		contentPane.add(functionTbx);
		functionTbx.setColumns(10);
		

		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBackground(new Color(240, 240, 240));
		solveBtn.setBounds(330, 470, 139, 52);
		contentPane.add(solveBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(553, 179, 553, 425);
		contentPane.add(scrollPane);
		
		JTextPane resultsPane = new JTextPane();
		resultsPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		resultsPane.setEditable(false);
		scrollPane.setViewportView(resultsPane);
		
		solveBtn.addActionListener(e -> {
		    String function = functionTbx.getText().trim();
		    String initialGuessA = assumptionATbx.getText().trim();
		    String initialGuessB = assumptionBTbx.getText().trim();
		    String thresholdStr = thresholdTbx.getText().trim();
		    
		    try {
		    	
		    	String displayString = "";
		    	BisectionMethod bm = new BisectionMethod(function, initialGuessA,initialGuessB ,thresholdStr);
		    	bm.processOutput();
		    	while(!GUIHelper.isMessagesEmpty()) {
		    		displayString += GUIHelper.receive();
		    	}
		    	resultsPane.setText(displayString);
		    	
		    	JTextPane resultHeader = new JTextPane();
		    	resultHeader.setBackground(new Color(218, 218, 218));
		    	resultHeader.setText("RESULT");
		    	resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		    	resultHeader.setEditable(false);
		    	scrollPane.setColumnHeaderView(resultHeader);
		    } catch (Exception ex) {
		    	thresholdTbx.setText(ex.getMessage());
		    	assumptionATbx.setText(ex.getMessage());
		    	assumptionBTbx.setText(ex.getMessage());
		    	functionTbx.setText(ex.getMessage());
		    }
		    
		});
		
		
		JLabel assumptionALbl = new JLabel("Assumption a:");
		assumptionALbl.setForeground(Color.WHITE);
		assumptionALbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		assumptionALbl.setBounds(77, 369, 182, 30);
		contentPane.add(assumptionALbl);
		JLabel thresholdLabel = new JLabel("Threshold:");
		thresholdLabel.setForeground(Color.WHITE);
		thresholdLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		thresholdLabel.setBounds(77, 469, 168, 23);
		contentPane.add(thresholdLabel);
		
		JLabel assumptionBLbl = new JLabel("Assumption b:");
		assumptionBLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		assumptionBLbl.setBounds(309, 369, 182, 30);
		contentPane.add(assumptionBLbl);
		
		JLabel functionLabel = new JLabel("Function:");
		functionLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		functionLabel.setBounds(77, 268, 144, 27);
		contentPane.add(functionLabel);
		
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/back.png"));
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		JLabel scrollPaneBg = new JLabel(new ImageIcon(getClass().getResource("/scrollPaneBg.png")));
		scrollPaneBg.setBounds(537, 164, 585, 456);
		contentPane.add(scrollPaneBg);
		
		JLabel inputBox = new JLabel(new ImageIcon(getClass().getResource("/inputBox.png")));
		inputBox.setBounds(59, 262, 220, 82);
		contentPane.add(inputBox);
		
		JLabel inputBox2 = new JLabel(new ImageIcon(getClass().getResource("/inputBox2.png")));
		inputBox2.setBounds(59, 457, 220, 82);
		contentPane.add(inputBox2);
		
		JLabel inputBox3 = new JLabel(new ImageIcon(getClass().getResource("/inputBox3.png")));
		inputBox3.setBounds(59, 360, 220, 82);
		contentPane.add(inputBox3);
		
		JLabel inputBox4 = new JLabel(new ImageIcon(getClass().getResource("/inputBox4.png")));
		inputBox4.setBounds(290, 360,  220, 82);
		contentPane.add(inputBox4);
		
		JLabel wall = new JLabel("");
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/bisectionWp.gif"));
		wall.setIcon(wp);
		
		JLabel bgDim = new JLabel(new ImageIcon(getClass().getResource("/dim.png")));
		bgDim.setBounds(0, 0, 1200, 720);
		
		
		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BisectionInfo bisectionInfo = new BisectionInfo();
				bisectionInfo.setVisible(true);
				dispose();
			}
		});
		ImageIcon infoicon = new ImageIcon(this.getClass().getResource("/info.png"));
		info.setIcon(infoicon);
		contentPane.add(info);
		contentPane.add(bgDim);
		contentPane.add(wall);

	}
}
