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

import numericalIntegration.Trapezoidal;
import userIO.GUIHelper;

public class TrapezoidalRule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField functionTbx;
	private JTextField uLimitTbx;
	private JTextField lLimitTbx;
	private JTextField intervalTbx;

	public TrapezoidalRule() {
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(32, 11, 128, 64);
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
		intervalTbx.setBounds(243, 377, 80, 30);
		contentPane.add(intervalTbx);
		intervalTbx.setColumns(10);
		
		functionTbx = new JTextField();
		functionTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		functionTbx.setBounds(252, 214, 182, 30);
		contentPane.add(functionTbx);
		functionTbx.setColumns(10);
		
		uLimitTbx = new JTextField();
		uLimitTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		uLimitTbx.setBounds(243, 301, 80, 30);
		contentPane.add(uLimitTbx);
		uLimitTbx.setColumns(10);
		
		lLimitTbx = new JTextField();
		lLimitTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lLimitTbx.setBounds(353, 301, 80, 30);
		contentPane.add(lLimitTbx);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(565, 168, 350, 300);
		contentPane.add(scrollPane);
		
		JTextPane resultsPane = new JTextPane();
		resultsPane.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		resultsPane.setText((String) null);
		resultsPane.setEditable(false);
		scrollPane.setViewportView(resultsPane);
		
		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBackground(new Color(240, 240, 240));
		solveBtn.setBounds(233, 427, 139, 52);
		contentPane.add(solveBtn);
		
		solveBtn.addActionListener(e -> {
		    String function = functionTbx.getText().trim();
		    String uLimit = uLimitTbx.getText().trim();
		    String lLimit = lLimitTbx.getText().trim();
		    String interval = intervalTbx.getText().trim();
		    try {
		    	JTextPane resultHeader = new JTextPane();
		    	resultHeader.setBackground(new Color(206, 206, 206));
				resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 25));
				resultHeader.setText("RESULT");
				resultHeader.setEditable(false);
				scrollPane.setColumnHeaderView(resultHeader);
		    	String displayString = "";
		    	Trapezoidal trapezoidal = new Trapezoidal(function, uLimit, lLimit, interval);
		    	trapezoidal.processOutput();
		    	while(!GUIHelper.isMessagesEmpty()) {
		    		displayString += GUIHelper.receive();
		    	}
		    	resultsPane.setText(displayString);
		    } catch (Exception ex) {
		    	resultsPane.setText("Please check your input: "+ ex.getMessage());
		    }
		    
		});
		
		JLabel functionLbl = new JLabel("Function:");
		functionLbl.setForeground(new Color(255, 255, 255));
		functionLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		functionLbl.setBounds(252, 194, 121, 14);
		contentPane.add(functionLbl);
		
		JLabel intervalLbl = new JLabel("INTVL");
		intervalLbl.setForeground(new Color(255, 255, 255));
		intervalLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 14));
		intervalLbl.setBounds(243, 355, 47, 14);
		contentPane.add(intervalLbl);
		
		JLabel scrollPaneBg = new JLabel(new ImageIcon(getClass().getResource("/simpsonFrame.png")));
		scrollPaneBg.setBounds(554, 160, 372, 323);
		contentPane.add(scrollPaneBg);
		
		JLabel lLimitLbl = new JLabel("Lower ");
		lLimitLbl.setForeground(new Color(255, 255, 255));
		lLimitLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 14));
		lLimitLbl.setBounds(349, 281, 47, 14);
		contentPane.add(lLimitLbl);
		
		JLabel uLimitLbl = new JLabel("Upper");
		uLimitLbl.setForeground(new Color(255, 255, 255));
		uLimitLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 14));
		uLimitLbl.setBounds(241, 283, 52, 11);
		contentPane.add(uLimitLbl);
		
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		
		JLabel inputBox3 = new JLabel(new ImageIcon(getClass().getResource("/inputBox3.png")));
		inputBox3.setBounds(233, 180, 220, 82);
		contentPane.add(inputBox3);
		
		JLabel limitBox4 = new JLabel(new ImageIcon(getClass().getResource("/limitBox4.png")));
		limitBox4.setBounds(343, 279, 100, 63);
		contentPane.add(limitBox4);
		
		JLabel limitBox5 = new JLabel(new ImageIcon(getClass().getResource("/limitBox5.png")));
		limitBox5.setBounds(233, 353, 100, 63);
		contentPane.add(limitBox5);
		
		JLabel limitBox6 = new JLabel(new ImageIcon(getClass().getResource("/limitBox6.png")));
		limitBox6.setBounds(233, 279, 100, 63);
		contentPane.add(limitBox6);
		
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/back.png"));
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);
		
		
		JLabel wall = new JLabel("");
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/trapezoidalWp.gif"));
		wall.setIcon(wp);
		
		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TrapezoidalInfo info = new TrapezoidalInfo();
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