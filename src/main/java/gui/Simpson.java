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

import numericalIntegration.Simpsons;
import userIO.GUIHelper;

public class Simpson extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField functionTbx;
	private JTextField uLimitTbx;
	private JTextField lLimitTbx;
	private JTextField intervalTbx;
	
	public Simpson() {
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
		lblNewLabel.setBounds(10, 11, 128, 64);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		
		intervalTbx = new JTextField();
		intervalTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		intervalTbx.setBounds(273, 363, 80, 30);
		contentPane.add(intervalTbx);
		intervalTbx.setColumns(10);
		
		functionTbx = new JTextField();
		functionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		functionTbx.setBounds(279, 200, 182, 30);
		contentPane.add(functionTbx);
		functionTbx.setColumns(10);
		
		uLimitTbx = new JTextField();
		uLimitTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		uLimitTbx.setBounds(271, 292, 80, 30);
		contentPane.add(uLimitTbx);
		uLimitTbx.setColumns(10);
		
		lLimitTbx = new JTextField();
		lLimitTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lLimitTbx.setBounds(390, 292, 80, 30);
		contentPane.add(lLimitTbx);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(610, 171, 350, 300);
		contentPane.add(scrollPane);
		
		JTextPane resultsPane = new JTextPane();
		resultsPane.setBackground(new Color(255, 255, 255));
		resultsPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultsPane.setText((String) null);
		resultsPane.setEditable(false);
		scrollPane.setViewportView(resultsPane);
		
		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBackground(new Color(240, 240, 240));
		solveBtn.setBounds(261, 473, 139, 52);
		contentPane.add(solveBtn);
		
		JLabel scrollPaneBg = new JLabel(new ImageIcon(getClass().getResource("/simpsonFrame.png")));
		scrollPaneBg.setBounds(599, 162, 372, 323);
		contentPane.add(scrollPaneBg);
		
		solveBtn.addActionListener(e -> {
		    String function = functionTbx.getText().trim();
		    String uLimit = uLimitTbx.getText().trim();
		    String lLimit = lLimitTbx.getText().trim();
		    String interval = intervalTbx.getText().trim();
		    try {
		    	JTextPane resultHeader = new JTextPane();
		    	resultHeader.setBackground(new Color(212, 212, 212));
				resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 25));
				resultHeader.setText("RESULT");
				resultHeader.setEditable(false);
				scrollPane.setColumnHeaderView(resultHeader);
		    	String displayString = "";
		    	Simpsons simp = new Simpsons(function, uLimit, lLimit, interval);
		    	simp.processOutput();
		    	while(!GUIHelper.isMessagesEmpty()) {
		    		displayString += GUIHelper.receive();
		    	}
		    	resultsPane.setText(displayString);
		    } catch (Exception ex) {
		    	resultsPane.setText("Please check your input: "+ ex.getMessage());
		    }
		    
		});
		
		JLabel functionLbl = new JLabel("Function:");
		functionLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		functionLbl.setBounds(279, 180, 129, 14);
		contentPane.add(functionLbl);
		
		JLabel lLimitLbl = new JLabel("Lower ");
		lLimitLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 14));
		lLimitLbl.setBounds(388, 273, 47, 14);
		contentPane.add(lLimitLbl);
		
		JLabel uLimitLbl = new JLabel("Upper ");
		uLimitLbl.setForeground(new Color(255, 255, 255));
		uLimitLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 14));
		uLimitLbl.setBounds(267, 273, 113, 11);
		contentPane.add(uLimitLbl);
		
		JLabel intervalLbl = new JLabel("INTVL");
		intervalLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 15));
		intervalLbl.setBounds(270, 343, 79, 14);
		contentPane.add(intervalLbl);
		
		JLabel limitBox3 = new JLabel(new ImageIcon(getClass().getResource("/limitBox3.png")));
		limitBox3.setBounds(264, 341, 100, 63);
		contentPane.add(limitBox3);
		
		JLabel inputBox = new JLabel(new ImageIcon(getClass().getResource("/inputBox.png")));
		inputBox.setBounds(261, 165, 220, 82);
		contentPane.add(inputBox);
		
		
		JLabel limitBox = new JLabel(new ImageIcon(getClass().getResource("/limitBox.png")));
		limitBox.setBounds(381, 268, 100, 63);
		contentPane.add(limitBox);
		
		JLabel limitBox2 = new JLabel(new ImageIcon(getClass().getResource("/limitBox2.png")));
		limitBox2.setBounds(261, 268, 100, 63);
		contentPane.add(limitBox2);
		
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/back.png"));
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		JLabel bgDim = new JLabel(new ImageIcon(getClass().getResource("/dim.png")));
		bgDim.setBounds(0, 0, 1200, 720);
		
		
		JLabel wall = new JLabel("");
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/simpsonsWp.gif"));
		wall.setIcon(wp);
		
		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpsonsInfo info = new SimpsonsInfo();
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
