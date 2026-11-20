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

import rootFinding.MOSSMethod;
import userIO.GUIHelper;
import java.awt.Font;
import java.awt.Color;

public class MOSS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField givenFunctionTbx;
	private JTextField functionTbx;
	private JTextField assumptionTbx;
	private JTextField thresholdTbx;



	public MOSS() {
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
		lblNewLabel.setBounds(10, 11, 128, 80);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/mossback.png"));
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		
		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBackground(new Color(240, 240, 240));
		solveBtn.setBounds(112, 548, 139, 52);
		contentPane.add(solveBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(575, 176, 553, 425);
		contentPane.add(scrollPane);
		
		JTextPane resultsPane = new JTextPane();
		resultsPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		resultsPane.setEditable(false);
		scrollPane.setViewportView(resultsPane);
		
		solveBtn.addActionListener(e -> {
		    String equation = functionTbx.getText().trim();
		    String initialGuess = assumptionTbx.getText().trim();
		    String thresholdStr = thresholdTbx.getText().trim();
		    
		    try {
		    	
		    	String displayString = "";
		    	MOSSMethod moss = new MOSSMethod(equation, initialGuess, thresholdStr);
		    	moss.processOutput();
		    	while(!GUIHelper.isMessagesEmpty()) {
		    		displayString += GUIHelper.receive();
		    	}
		    	resultsPane.setText(displayString);
		    	
		    	JTextPane resultHeader = new JTextPane();
		    	resultHeader.setBackground(new Color(219, 219, 219));
		    	resultHeader.setText("RESULT");
		    	resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 24));
		    	resultHeader.setEditable(false);
		    	scrollPane.setColumnHeaderView(resultHeader);
		    } catch (Exception ex) {
		    	thresholdTbx.setText(ex.getMessage());
		    	assumptionTbx.setText(ex.getMessage());
		    	functionTbx.setText(ex.getMessage());
		    }
		    
		});
		
		thresholdTbx = new JTextField();
		thresholdTbx.setForeground(new Color(0, 0, 0));
		thresholdTbx.setBackground(new Color(255, 255, 255));
		thresholdTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		thresholdTbx.setColumns(10);
		thresholdTbx.setBounds(117, 492, 260, 22);
		contentPane.add(thresholdTbx);
		
		assumptionTbx = new JTextField();
		assumptionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		assumptionTbx.setColumns(10);
		assumptionTbx.setBounds(119, 412, 182, 30);
		contentPane.add(assumptionTbx);
		
		functionTbx = new JTextField();
		functionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		functionTbx.setColumns(10);
		functionTbx.setBounds(119, 320, 182, 30);
		contentPane.add(functionTbx);
		
		givenFunctionTbx = new JTextField();
		givenFunctionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		givenFunctionTbx.setBounds(119, 226, 182, 30);
		contentPane.add(givenFunctionTbx);
		givenFunctionTbx.setColumns(10);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		JLabel givenFunction = new JLabel("[Optional] Function:");
		givenFunction.setForeground(new Color(255, 255, 255));
		givenFunction.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 15));
		givenFunction.setBounds(117, 204, 184, 14);
		contentPane.add(givenFunction);
		
		
		JLabel thresholdLabel = new JLabel("Threshold:");
		thresholdLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 16));
		thresholdLabel.setForeground(new Color(255, 255, 255));
		thresholdLabel.setBounds(117, 477, 121, 14);
		contentPane.add(thresholdLabel);
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/thresholdBg.png")));
		lblNewLabel_4.setBounds(102, 471, 295, 56);
		contentPane.add(lblNewLabel_4);
		
		JLabel assumptionLabel = new JLabel("Assumption:");
		assumptionLabel.setForeground(new Color(255, 255, 255));
		assumptionLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		assumptionLabel.setBounds(117, 391, 139, 14);
		contentPane.add(assumptionLabel);
		
		JLabel inputBox3 = new JLabel(new ImageIcon(getClass().getResource("/inputBox3.png")));
		inputBox3.setBounds(102, 377, 220, 82);
		contentPane.add(inputBox3);
		
		JLabel functionLabel = new JLabel("Simplified Function:");
		functionLabel.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 17));
		functionLabel.setBounds(117, 303, 198, 11);
		contentPane.add(functionLabel);
		
		JLabel inputBox = new JLabel(new ImageIcon(getClass().getResource("/inputBox.png")));
		inputBox.setBounds(102, 284, 220, 82);
		contentPane.add(inputBox);

		JLabel inputBox5 = new JLabel(new ImageIcon(getClass().getResource("/inputBox5.png")));
		inputBox5.setBounds(102, 190, 220, 82);
		contentPane.add(inputBox5);
		
		JLabel wall = new JLabel("");
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/mossWp.gif"));
		wall.setIcon(wp);
		
		JLabel scrollPaneBg = new JLabel(new ImageIcon(getClass().getResource("/scrollPaneBg.png")));
		scrollPaneBg.setBounds(559, 160, 585, 456);
		contentPane.add(scrollPaneBg);
		
		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MossInfo info = new MossInfo();
				info.setVisible(true);
				dispose();
			}
		});
		ImageIcon infoicon = new ImageIcon(this.getClass().getResource("/info.png"));
		info.setIcon(infoicon);
		contentPane.add(info);
		
		JLabel bgDim = new JLabel(new ImageIcon(getClass().getResource("/dim.png")));
		bgDim.setBounds(0, 0, 1200, 720);
		contentPane.add(bgDim);
		contentPane.add(wall);
		
		contentPane.add(wall);
	}
}

