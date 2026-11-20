package gui;

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

import rootFinding.FunctionCollection;
import rootFinding.NonlinearNR;
import userIO.GUIHelper;
import userIO.GUIHelperExt;
import javax.swing.JTextArea;
import java.awt.Color;

public class NonLinear extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField function1Tbx;
	private JTextField function2Tbx;
	private JTextField assumptionXTbx;
	private JTextField assumptionYTbx;
	private JTextField thresholdTbx;

	public NonLinear() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		
		JLabel thresholdLbl = new JLabel("Threshold:");
		thresholdLbl.setForeground(new Color(255, 255, 255));
		thresholdLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 16));
		thresholdLbl.setBounds(83, 436, 115, 14);
		contentPane.add(thresholdLbl);
		
		thresholdTbx = new JTextField();
		thresholdTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		thresholdTbx.setColumns(10);
		thresholdTbx.setBounds(83, 452, 265, 20);
		contentPane.add(thresholdTbx);
		
		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBackground(new Color(240, 240, 240));
		solveBtn.setBounds(71, 494, 139, 52);
		contentPane.add(solveBtn);
		
		JLabel assumpitonXTbx = new JLabel("X :");
		assumpitonXTbx.setForeground(new Color(255, 255, 255));
		assumpitonXTbx.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 15));
		assumpitonXTbx.setBounds(83, 348, 17, 14);
		contentPane.add(assumpitonXTbx);
		
		JLabel assumpitonYTbx = new JLabel("Y:");
		assumpitonYTbx.setForeground(new Color(255, 255, 255));
		assumpitonYTbx.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 16));
		assumpitonYTbx.setBounds(201, 348, 17, 14);
		contentPane.add(assumpitonYTbx);
		
		assumptionXTbx = new JTextField();
		assumptionXTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		assumptionXTbx.setColumns(10);
		assumptionXTbx.setBounds(81, 368, 80, 30);
		contentPane.add(assumptionXTbx);
		
		assumptionYTbx = new JTextField();
		assumptionYTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		assumptionYTbx.setColumns(10);
		assumptionYTbx.setBounds(201, 368, 80, 30);
		contentPane.add(assumptionYTbx);
		
		function1Tbx = new JTextField();
		function1Tbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		function1Tbx.setBounds(83, 293, 265, 20);
		contentPane.add(function1Tbx);
		function1Tbx.setColumns(10);
		
		function2Tbx = new JTextField();
		function2Tbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		function2Tbx.setColumns(10);
		function2Tbx.setBounds(83, 227, 265, 20);
		contentPane.add(function2Tbx);
		
		JLabel function1Lbl = new JLabel("Function 1 :");
		function1Lbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 17));
		function1Lbl.setBounds(83, 214, 153, 14);
		contentPane.add(function1Lbl);
		
		JLabel function2Lbl = new JLabel("Function 2 :");
		function2Lbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 16));
		function2Lbl.setBounds(83, 278, 115, 14);
		contentPane.add(function2Lbl);
		
		JScrollPane resultSP = new JScrollPane();
		resultSP.setBorder(null);
		resultSP.setBounds(423, 201, 365, 342);
		contentPane.add(resultSP);
		
		JTextPane resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultSP.setViewportView(resultPane);
		
		JScrollPane errorTableSP = new JScrollPane();
		errorTableSP.setBorder(null);
		errorTableSP.setBounds(788, 201, 365, 342);
		contentPane.add(errorTableSP);
		
		JTextPane errorTablePane = new JTextPane();
		errorTablePane.setText((String) null);
		errorTablePane.setEditable(false);
		errorTableSP.setViewportView(errorTablePane);
		lblNewLabel.setBounds(10, 11, 128, 64);
		contentPane.add(lblNewLabel);
		
		
		solveBtn.addActionListener(e -> {
		    String function1 = function1Tbx.getText().trim();
		    String function2 = function2Tbx.getText().trim();
		    String assumptionX = assumptionXTbx.getText().trim();
		    String assumptionY = assumptionYTbx.getText().trim();
		    String thresholdStr = thresholdTbx.getText().trim();
		    
		    try {
		    	
		    	FunctionCollection functionCollection = new FunctionCollection(function1, function2);
		    	String[] assumptions = {assumptionX, assumptionY};
		    	String displayStringAnswer = "";
		    	String displayStringErrorLvl = "";
		    	NonlinearNR nl = new NonlinearNR(functionCollection, assumptions, thresholdStr);
		    	nl.processOutput();
		    	while(!GUIHelper.isMessagesEmpty()) {
		    		displayStringAnswer += GUIHelper.receive();
		    	}
		    	
		    	String thresholdDisplay = GUIHelperExt.receive();
		    	while(!GUIHelperExt.isMessagesEmpty()) {
		    		displayStringErrorLvl += GUIHelperExt.receive();
		    	}
		    	resultPane.setText(displayStringAnswer);
		    	
		    	JTextArea resultHeader = new JTextArea();
		    	resultHeader.setBackground(new Color(192, 192, 192));
		    	resultHeader.setEditable(false);
		    	resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		    	resultHeader.setText("RESULT WITH RESPECT TO THRESHOLD LEVEL");
		    	resultSP.setColumnHeaderView(resultHeader);
		    	errorTablePane.setText(displayStringErrorLvl);
		    	
		    	JTextArea txtrErrorTable = new JTextArea();
		    	txtrErrorTable.setBackground(new Color(214, 214, 214));
		    	txtrErrorTable.setEditable(false);
		    	txtrErrorTable.setText(thresholdDisplay);
		    	txtrErrorTable.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		    	errorTableSP.setColumnHeaderView(txtrErrorTable);
		    } catch (Exception ex) {
		    	function1Tbx.setText(ex.getMessage());
		    	function2Tbx.setText(ex.getMessage());
		    	assumptionXTbx.setText(ex.getMessage());
		    	assumptionYTbx.setText(ex.getMessage());
		    	thresholdTbx.setText(ex.getMessage());
		    }
		    
		});
		
		JLabel resultsBg = new JLabel(new ImageIcon(getClass().getResource("/resultsBg.png")));
		resultsBg.setBounds(404, 173, 766, 399);
		contentPane.add(resultsBg);
		
		JLabel lblNewLabel_2 = new JLabel(new ImageIcon(getClass().getResource("/assumptionBg.png")));
		lblNewLabel_2.setBounds(71, 205, 295, 56);
		contentPane.add(lblNewLabel_2);
		
		JLabel assump = new JLabel(new ImageIcon(getClass().getResource("/assumptionBg.png")));
		assump.setBounds(71, 272, 295, 56);
		contentPane.add(assump);
		
		JLabel limitBox2 = new JLabel(new ImageIcon(getClass().getResource("/limitBox2.png")));
		limitBox2.setBounds(71, 346, 100, 63);
		contentPane.add(limitBox2);
		
		JLabel limBox2 = new JLabel(new ImageIcon(getClass().getResource("/limitBox2.png")));
		limBox2.setBounds(192, 346, 100, 63);
		contentPane.add(limBox2);
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/thresholdBg.png")));
		lblNewLabel_4.setBounds(71, 427, 295, 56);
		contentPane.add(lblNewLabel_4);

		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/back.png"));
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		
		JLabel wall = new JLabel("");
		wall.setFont(new Font("Tahoma", Font.BOLD, 11));
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/nonLinearWp.gif"));
		wall.setIcon(wp);
		
		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NonLinearInfo info = new NonLinearInfo();
				info.setVisible(true);
				dispose();
			}
		});
		ImageIcon infoicon = new ImageIcon(this.getClass().getResource("/info.png"));
		info.setIcon(infoicon);
		contentPane.add(info);
		contentPane.add(wall);
		
		contentPane.add(wall);
		

	}
}
