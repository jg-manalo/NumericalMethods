package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import linearalgebra.JacobiMethod;
import userIO.GUIHelper;
import userIO.GUIHelperExt;

public class Jacobi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dosPorDosA11;
	private JTextField dosPorDosA12;
	private JTextField dosPorDosA21;
	private JTextField dosPorDosA22;
	private JTextField dosPorDosB1;
	private JTextField dosPorDosB2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tresPorTresA12;
	private JTextField tresPorTresA13;
	private JTextField tresPorTresA21;
	private JTextField tresPorTresA22;
	private JTextField tresPorTresA23;
	private JTextField tresPorTresA31;
	private JTextField tresPorTresA32;
	private JTextField tresPorTresA33;
	private JTextField tresPorTresB1;
	private JTextField tresPorTresB2;
	private JTextField tresPorTresB3;
	private JTextField tresPorTresA11;
	private JTextField cuatroPorCuatroA11;
	private JTextField cuatroPorCuatroA12;
	private JTextField cuatroPorCuatroA13;
	private JTextField cuatroPorCuatroA14;
	private JTextField cuatroPorCuatroA21;
	private JTextField cuatroPorCuatroA22;
	private JTextField cuatroPorCuatroA23;
	private JTextField cuatroPorCuatroA24;
	private JTextField cuatroPorCuatroA31;
	private JTextField cuatroPorCuatroA32;
	private JTextField cuatroPorCuatroA33;
	private JTextField cuatroPorCuatroA34;
	private JTextField cuatroPorCuatroA41;
	private JTextField cuatroPorCuatroA42;
	private JTextField cuatroPorCuatroA43;
	private JTextField cuatroPorCuatroA44;
	private JTextField cuatroPorCuatroB1;
	private JTextField cuatroPorCuatroB2;
	private JTextField cuatroPorCuatroB3;
	private JTextField cuatroPorCuatroB4;
	private JTextField tresPorTresAssume1;
	private JTextField thresholdTbx;
	private JTextField tresPorTresAssume2;
	private JTextField tresPorTresAssume3;
	private JTextField cuatroPorCuatroAssume1;
	private JTextField cuatroPorCuatroAssume2;
	private JTextField cuatroPorCuatroAssume3;
	private JTextField cuatroPorCuatroAssume4;
	private JTextField dosPorDosAssume1;
	private JTextField dosPorDosAssume2;
	
	public Jacobi() {
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

		this.init2x2Matrix();
		this.init3x3Matrix();
		this.init4x4Matrix();

		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(24, 11, 128, 80);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		ImageIcon labelNewIcon = new ImageIcon(this.getClass().getResource("/jacobiback.png"));
		contentPane.setLayout(null);
		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);

		JLabel thresholdLbl = new JLabel("Threshold:");
		thresholdLbl.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		thresholdLbl.setForeground(new Color(255, 255, 255));
		thresholdLbl.setBounds(80, 393, 128, 14);
		contentPane.add(thresholdLbl);

		thresholdTbx = new JTextField();
		thresholdTbx.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		thresholdTbx.setBounds(80, 412, 144, 20);
		thresholdTbx.setColumns(10);
		contentPane.add(thresholdTbx);

		JScrollPane ErrorTableSP = new JScrollPane();
		ErrorTableSP.setBorder(null);
		ErrorTableSP.setBounds(767, 171, 365, 342);
		contentPane.add(ErrorTableSP);

		JTextPane errorTablePane = new JTextPane();
		errorTablePane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		ErrorTableSP.setBorder(null);
		errorTablePane.setEditable(false);
		ErrorTableSP.setViewportView(errorTablePane);

		JTextPane errorLvlHeader = new JTextPane();
		errorLvlHeader.setBackground(new Color(206, 206, 206));
		errorLvlHeader.setFont(new Font("OCR A Extended", Font.BOLD, 24));
		errorLvlHeader.setEditable(false);
		ErrorTableSP.setColumnHeaderView(errorLvlHeader);

		lblNewLabel.setIcon(labelNewIcon);
		contentPane.add(lblNewLabel);

		JButton solveBtn = new JButton(new ImageIcon(getClass().getResource("/solve.png")));
		solveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		solveBtn.setBackground(new Color(240, 240, 240));
		solveBtn.setBounds(395, 542, 139, 52);
		contentPane.add(solveBtn);

		JScrollPane ResultSP = new JScrollPane();
		ResultSP.setBorder(null);
		ResultSP.setBounds(402, 171, 365, 342);
		contentPane.add(ResultSP);

		JTextPane resultsPane = new JTextPane();
		resultsPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		resultsPane.setBorder(null);
		resultsPane.setEditable(false);
		ResultSP.setViewportView(resultsPane);

		JTextPane resultHeader = new JTextPane();
		resultHeader.setBackground(new Color(206, 206, 206));
		resultHeader.setText("RESULT");
		resultHeader.setFont(new Font("OCR A Extended", Font.BOLD, 24));
		resultHeader.setEditable(false);
		resultHeader.setVisible(false);
		ResultSP.setColumnHeaderView(resultHeader);
		resultHeader.setVisible(false);

		JRadioButton dosPorDos = new JRadioButton("2x2");
		dosPorDos.setOpaque(false);
		dosPorDos.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		dosPorDos.setBounds(80, 495, 109, 23);
		dosPorDos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				show2x2Matrix();
				hide3x3Matrix();
				hide4x4Matrix();
				solveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (dosPorDosA11.isVisible()) {
							JTextPane errorLvlHeader = new JTextPane();
							errorLvlHeader.setFont(new Font("OCR A Extended", Font.BOLD, 16));
							errorLvlHeader.setEditable(false);
							ErrorTableSP.setColumnHeaderView(errorLvlHeader);
							try {
								String[][] coefficients = {
										{ dosPorDosA11.getText().trim(), dosPorDosA12.getText().trim() },
										{ dosPorDosA21.getText().trim(), dosPorDosA22.getText().trim() }

								};

								String[] constants = { dosPorDosB1.getText().trim(), dosPorDosB2.getText().trim() };
								String[] assumption = { tresPorTresAssume1.getText().trim(),
										tresPorTresAssume2.getText().trim() };

								String threshold = thresholdTbx.getText().trim();

								JacobiMethod jacobi = new JacobiMethod(coefficients, constants, assumption, threshold);
								jacobi.processOutput();

								String displayAnswer = "";
								String displayRelativeErrors = "";
								String errorThresholdDisplay = getThresholdLvlForHeader();
								while (!GUIHelper.isMessagesEmpty()) {
									displayAnswer += GUIHelper.receive();
								}
								while (!GUIHelperExt.isMessagesEmpty()) {
									displayRelativeErrors += GUIHelperExt.receive();
								}
								resultsPane.setText(displayAnswer);
								errorTablePane.setText(displayRelativeErrors);
								errorLvlHeader.setText(errorThresholdDisplay);

							} catch (Exception ex) {
								resultsPane.setText("Error: " + ex.getMessage());
								errorTablePane.setText("Error: " + ex.getMessage());
							}
						}
					}
				});
			}
		});

		buttonGroup.add(dosPorDos);
		contentPane.add(dosPorDos);

		JRadioButton tresPorTres = new JRadioButton("3x3");
		tresPorTres.setOpaque(false);
		tresPorTres.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		tresPorTres.setBounds(80, 521, 109, 23);
		tresPorTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show3x3Matrix();
				hide2x2Matrix();
				hide4x4Matrix();

				solveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resultHeader.setVisible(true);
						if (tresPorTresA11.isVisible()) {
							JTextPane errorLvlHeader = new JTextPane();
							errorLvlHeader.setFont(new Font("OCR A Extended", Font.BOLD, 16));
							errorLvlHeader.setEditable(false);
							ErrorTableSP.setColumnHeaderView(errorLvlHeader);
							try {

								String[][] coefficients = {
										{ tresPorTresA11.getText().trim(), tresPorTresA12.getText().trim(),
												tresPorTresA13.getText().trim() },
										{ tresPorTresA21.getText().trim(), tresPorTresA22.getText().trim(),
												tresPorTresA23.getText().trim() },
										{ tresPorTresA31.getText().trim(), tresPorTresA32.getText().trim(),
												tresPorTresA33.getText().trim() } };

								String[] constants = { tresPorTresB1.getText().trim(), tresPorTresB2.getText().trim(),
										tresPorTresB3.getText().trim() };

								String assumption[] = { tresPorTresAssume1.getText().trim(),
										tresPorTresAssume2.getText().trim(), tresPorTresAssume3.getText().trim() };
								String threshold = thresholdTbx.getText().trim();
								JacobiMethod jacobi = new JacobiMethod(coefficients, constants, assumption, threshold);
								jacobi.processOutput();

								String displayAnswer = "";
								String displayRelativeErrors = "";
								String errorThresholdDisplay = getThresholdLvlForHeader();
								while (!GUIHelper.isMessagesEmpty()) {
									displayAnswer += GUIHelper.receive();
								}
								while (!GUIHelperExt.isMessagesEmpty()) {
									displayRelativeErrors += GUIHelperExt.receive();
								}
								resultsPane.setText(displayAnswer);
								errorTablePane.setText(displayRelativeErrors);
								errorLvlHeader.setText(errorThresholdDisplay);
							} catch (Exception ex) {
								resultsPane.setText("Error: " + ex.getMessage());
								errorTablePane.setText("Error: " + ex.getMessage());
							}
						}

					}
				});

			}
		});
		buttonGroup.add(tresPorTres);
		contentPane.add(tresPorTres);

		JRadioButton cuatroPorCuatro = new JRadioButton("4x4");
		cuatroPorCuatro.setOpaque(false);
		cuatroPorCuatro.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 24));
		cuatroPorCuatro.setBounds(80, 547, 109, 23);
		cuatroPorCuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show4x4Matrix();
				hide2x2Matrix();
				hide3x3Matrix();
				solveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resultHeader.setVisible(true);
						if (cuatroPorCuatroA11.isVisible()) {
							JTextPane errorLvlHeader = new JTextPane();
							errorLvlHeader.setFont(new Font("OCR A Extended", Font.BOLD, 16));
							errorLvlHeader.setEditable(false);
							ErrorTableSP.setColumnHeaderView(errorLvlHeader);
							try {
								String[][] coefficients = {
										{ cuatroPorCuatroA11.getText().trim(), cuatroPorCuatroA12.getText().trim(),
												cuatroPorCuatroA13.getText().trim(),
												cuatroPorCuatroA14.getText().trim() },
										{ cuatroPorCuatroA21.getText().trim(), cuatroPorCuatroA22.getText().trim(),
												cuatroPorCuatroA23.getText().trim(),
												cuatroPorCuatroA24.getText().trim() },
										{ cuatroPorCuatroA31.getText().trim(), cuatroPorCuatroA32.getText().trim(),
												cuatroPorCuatroA33.getText().trim(),
												cuatroPorCuatroA34.getText().trim() },
										{ cuatroPorCuatroA41.getText().trim(), cuatroPorCuatroA42.getText().trim(),
												cuatroPorCuatroA43.getText().trim(),
												cuatroPorCuatroA44.getText().trim() } };

								String[] constants = { cuatroPorCuatroB1.getText().trim(),
										cuatroPorCuatroB2.getText().trim(), cuatroPorCuatroB3.getText().trim(),
										cuatroPorCuatroB4.getText().trim() };

								String[] assumption = { cuatroPorCuatroAssume1.getText().trim(),
										cuatroPorCuatroAssume2.getText().trim(),
										cuatroPorCuatroAssume3.getText().trim(),
										cuatroPorCuatroAssume4.getText().trim() };
								String threshold = thresholdTbx.getText().trim();
								JacobiMethod jacobi = new JacobiMethod(coefficients, constants, assumption, threshold);
								jacobi.processOutput();

								String displayAnswer = "";
								String displayRelativeErrors = "";
								String errorThresholdDisplay = getThresholdLvlForHeader();
								while (!GUIHelper.isMessagesEmpty()) {
									displayAnswer += GUIHelper.receive();
								}
								while (!GUIHelperExt.isMessagesEmpty()) {
									displayRelativeErrors += GUIHelperExt.receive();
								}
								resultsPane.setText(displayAnswer);
								errorTablePane.setText(displayRelativeErrors);
								errorLvlHeader.setText(errorThresholdDisplay);
							} catch (Exception ex) {
								resultsPane.setText("Error: " + ex.getMessage());
								errorTablePane.setText("Error: " + ex.getMessage());
							}
						}
					}
				});

			}
		});
		buttonGroup.add(cuatroPorCuatro);
		contentPane.add(cuatroPorCuatro);
		
		JLabel resultsBg = new JLabel(new ImageIcon(getClass().getResource("/resultsBg.png")));
		resultsBg.setBounds(385, 141, 766, 399);
		contentPane.add(resultsBg);
		
		JLabel lblNewLabel_1 = new JLabel("Choose...");
		lblNewLabel_1.setForeground(new Color(64, 128, 128));
		lblNewLabel_1.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(80, 465, 146, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel matrixBg_1 = new JLabel(new ImageIcon(getClass().getResource("/matrixBg.png")));
		matrixBg_1.setBounds(60, 452, 300, 141);
		contentPane.add(matrixBg_1);

		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/thresholdBg.png")));
		lblNewLabel_4.setBounds(60, 387, 295, 56);
		contentPane.add(lblNewLabel_4);
		
		
		JLabel lblNewLabel_3_1 = new JLabel("Input:");
		lblNewLabel_3_1.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(75, 165, 182, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel matBg = new JLabel(new ImageIcon(getClass().getResource("/matrixBg.png")));
		matBg.setBounds(60, 159, 300, 141);
		contentPane.add(matBg);
		
		JLabel lblNewLabel_3 = new JLabel("Assumptions:");
		lblNewLabel_3.setFont(new Font("Upheaval TT (BRK)", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(75, 330, 182, 14);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_2 = new JLabel(new ImageIcon(getClass().getResource("/assumptionBg.png")));
		lblNewLabel_2.setBounds(60, 320, 295, 56);
		contentPane.add(lblNewLabel_2);

		JLabel wall = new JLabel("");
		wall.setBounds(0, 0, 1200, 720);
		ImageIcon wp = new ImageIcon(this.getClass().getResource("/jacobiWp.gif"));
		wall.setIcon(wp);

		JLabel info = new JLabel("");
		info.setBounds(1128, 11, 72, 56);
		info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JacobiInfo info = new JacobiInfo();
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

	private String getThresholdLvlForHeader() {
		return GUIHelperExt.receive();
	}

	private void init2x2Matrix() {
		dosPorDosA11 = new JTextField();
		dosPorDosA11.setColumns(10);
		dosPorDosA11.setBounds(81, 180, 38, 20);

		dosPorDosA12 = new JTextField();
		dosPorDosA12.setColumns(10);
		dosPorDosA12.setBounds(129, 180, 38, 20);

		dosPorDosA21 = new JTextField();
		dosPorDosA21.setColumns(10);
		dosPorDosA21.setBounds(81, 207, 38, 20);

		dosPorDosA22 = new JTextField();
		dosPorDosA22.setColumns(10);
		dosPorDosA22.setBounds(129, 207, 38, 20);

		dosPorDosB1 = new JTextField();
		dosPorDosB1.setColumns(10);
		dosPorDosB1.setBounds(210, 180, 38, 20);

		dosPorDosB2 = new JTextField();
		dosPorDosB2.setColumns(10);
		dosPorDosB2.setBounds(210, 207, 38, 20);

		dosPorDosAssume1 = new JTextField();
		dosPorDosAssume1.setBounds(71, 346, 62, 20);
		dosPorDosAssume1.setColumns(10);

		dosPorDosAssume2 = new JTextField();
		dosPorDosAssume2.setColumns(10);
		dosPorDosAssume2.setBounds(141, 346, 62, 20);

		this.hide2x2Matrix();

		contentPane.add(dosPorDosA11, 0);
		contentPane.add(dosPorDosA12, 0);
		contentPane.add(dosPorDosA21, 0);
		contentPane.add(dosPorDosA22, 0);
		contentPane.add(dosPorDosB1, 0);
		contentPane.add(dosPorDosB2, 0);
		contentPane.add(dosPorDosAssume1, 0);
		contentPane.add(dosPorDosAssume2, 0);

		contentPane.revalidate();
		contentPane.repaint();
	}

	private void show2x2Matrix() {
		dosPorDosA11.setVisible(true);
		dosPorDosA12.setVisible(true);
		dosPorDosA21.setVisible(true);
		dosPorDosA22.setVisible(true);
		dosPorDosB1.setVisible(true);
		dosPorDosB2.setVisible(true);
		dosPorDosAssume1.setVisible(true);
		dosPorDosAssume2.setVisible(true);
	}

	//private void jlabelsForEdit() {
		// matrix editor start

		// for 2x2

//		contentPane.add(dosPorDosA11);
//		contentPane.add(dosPorDosA12);
//		contentPane.add(dosPorDosA21);
//		contentPane.add(dosPorDosA22);
//		contentPane.add(dosPorDosB1);
//		contentPane.add(dosPorDosB2);
//		contentPane.add(dosPorDosAssume1);
//		contentPane.add(dosPorDosAssume2);

		// for 3x3

//		contentPane.add(tresPorTresA11);
//		contentPane.add(tresPorTresA12);
//		contentPane.add(tresPorTresA13);
//		contentPane.add(tresPorTresA21);
//		contentPane.add(tresPorTresA22);
//		contentPane.add(tresPorTresA23);
//		contentPane.add(tresPorTresA31);
//		contentPane.add(tresPorTresA32);
//		contentPane.add(tresPorTresA33);
//		contentPane.add(tresPorTresB1);
//		contentPane.add(tresPorTresB2);
//		contentPane.add(tresPorTresB3);
//		contentPane.add(tresPorTresAssume1);
//		contentPane.add(tresPorTresAssume2);
//		contentPane.add(tresPorTresAssume3);
		// for 4x4

//		 contentPane.add(cuatroPorCuatroA11); contentPane.add(cuatroPorCuatroA12);
//		 contentPane.add(cuatroPorCuatroA13); contentPane.add(cuatroPorCuatroA14);
//		 contentPane.add(cuatroPorCuatroA21); contentPane.add(cuatroPorCuatroA22);
//		 contentPane.add(cuatroPorCuatroA23); contentPane.add(cuatroPorCuatroA24);
//		 contentPane.add(cuatroPorCuatroA31); contentPane.add(cuatroPorCuatroA32);
//		 contentPane.add(cuatroPorCuatroA33); contentPane.add(cuatroPorCuatroA34);
//		 contentPane.add(cuatroPorCuatroA41); contentPane.add(cuatroPorCuatroA42);
//		 contentPane.add(cuatroPorCuatroA43); contentPane.add(cuatroPorCuatroA44);
//		 contentPane.add(cuatroPorCuatroB1); contentPane.add(cuatroPorCuatroB2);
//		 contentPane.add(cuatroPorCuatroB3); contentPane.add(cuatroPorCuatroB4);
//		 contentPane.add(cuatroPorCuatroAssume1);
//		 contentPane.add(cuatroPorCuatroAssume2);
//		 contentPane.add(cuatroPorCuatroAssume3);
//		 contentPane.add(cuatroPorCuatroAssume4);
		// matrix editor end

//	}

	private void init3x3Matrix() {
		tresPorTresA11 = new JTextField();
		tresPorTresA11.setColumns(10);
		tresPorTresA11.setBounds(81, 180, 38, 20);

		tresPorTresA12 = new JTextField();
		tresPorTresA12.setColumns(10);
		tresPorTresA12.setBounds(129, 180, 38, 20);

		tresPorTresA13 = new JTextField();
		tresPorTresA13.setColumns(10);
		tresPorTresA13.setBounds(177, 180, 38, 20);

		tresPorTresA21 = new JTextField();
		tresPorTresA21.setColumns(10);
		tresPorTresA21.setBounds(81, 207, 38, 20);

		tresPorTresA22 = new JTextField();
		tresPorTresA22.setColumns(10);
		tresPorTresA22.setBounds(129, 207, 38, 20);

		tresPorTresA23 = new JTextField();
		tresPorTresA23.setColumns(10);
		tresPorTresA23.setBounds(177, 207, 38, 20);

		tresPorTresA31 = new JTextField();
		tresPorTresA31.setColumns(10);
		tresPorTresA31.setBounds(81, 234, 38, 20);

		tresPorTresA32 = new JTextField();
		tresPorTresA32.setColumns(10);
		tresPorTresA32.setBounds(129, 234, 38, 20);

		tresPorTresA33 = new JTextField();
		tresPorTresA33.setColumns(10);
		tresPorTresA33.setBounds(177, 234, 38, 20);

		tresPorTresB1 = new JTextField();
		tresPorTresB1.setColumns(10);
		tresPorTresB1.setBounds(251, 180, 38, 20);

		tresPorTresB2 = new JTextField();
		tresPorTresB2.setColumns(10);
		tresPorTresB2.setBounds(251, 207, 38, 20);

		tresPorTresB3 = new JTextField();
		tresPorTresB3.setColumns(10);
		tresPorTresB3.setBounds(251, 234, 38, 20);

		tresPorTresAssume1 = new JTextField();
		tresPorTresAssume1.setBounds(71, 346, 62, 20);
		tresPorTresAssume1.setColumns(10);

		tresPorTresAssume2 = new JTextField();
		tresPorTresAssume2.setColumns(10);
		tresPorTresAssume2.setBounds(141, 346, 62, 20);

		tresPorTresAssume3 = new JTextField();
		tresPorTresAssume3.setColumns(10);
		tresPorTresAssume3.setBounds(212, 346, 62, 20);

		this.hide3x3Matrix();

		contentPane.add(tresPorTresA11, 0);
		contentPane.add(tresPorTresA12, 0);
		contentPane.add(tresPorTresA13, 0);
		contentPane.add(tresPorTresA21, 0);
		contentPane.add(tresPorTresA22, 0);
		contentPane.add(tresPorTresA23, 0);
		contentPane.add(tresPorTresA31, 0);
		contentPane.add(tresPorTresA32, 0);
		contentPane.add(tresPorTresA33, 0);
		contentPane.add(tresPorTresB1, 0);
		contentPane.add(tresPorTresB2, 0);
		contentPane.add(tresPorTresB3, 0);
		contentPane.add(tresPorTresAssume1, 0);
		contentPane.add(tresPorTresAssume2, 0);
		contentPane.add(tresPorTresAssume3, 0);

		contentPane.revalidate();
		contentPane.repaint();
	}

	private void show3x3Matrix() {
		tresPorTresA11.setVisible(true);
		tresPorTresA12.setVisible(true);
		tresPorTresA13.setVisible(true);
		tresPorTresA21.setVisible(true);
		tresPorTresA22.setVisible(true);
		tresPorTresA23.setVisible(true);
		tresPorTresA31.setVisible(true);
		tresPorTresA32.setVisible(true);
		tresPorTresA33.setVisible(true);
		tresPorTresB1.setVisible(true);
		tresPorTresB2.setVisible(true);
		tresPorTresB3.setVisible(true);
		tresPorTresAssume1.setVisible(true);
		tresPorTresAssume2.setVisible(true);
		tresPorTresAssume3.setVisible(true);
	}

	private void hide2x2Matrix() {

		dosPorDosA11.setVisible(false);
		dosPorDosA12.setVisible(false);
		dosPorDosA21.setVisible(false);
		dosPorDosA22.setVisible(false);
		dosPorDosB1.setVisible(false);
		dosPorDosB2.setVisible(false);
		dosPorDosAssume1.setVisible(false);
		dosPorDosAssume2.setVisible(false);
	}

	private void init4x4Matrix() {
		cuatroPorCuatroA11 = new JTextField();
		cuatroPorCuatroA11.setBounds(81, 180, 38, 20);
		cuatroPorCuatroA11.setColumns(10);

		cuatroPorCuatroA12 = new JTextField();
		cuatroPorCuatroA12.setColumns(10);
		cuatroPorCuatroA12.setBounds(129, 180, 38, 20);

		cuatroPorCuatroA13 = new JTextField();
		cuatroPorCuatroA13.setColumns(10);
		cuatroPorCuatroA13.setBounds(177, 180, 38, 20);

		cuatroPorCuatroA14 = new JTextField();
		cuatroPorCuatroA14.setColumns(10);
		cuatroPorCuatroA14.setBounds(225, 180, 38, 20);

		cuatroPorCuatroA21 = new JTextField();
		cuatroPorCuatroA21.setColumns(10);
		cuatroPorCuatroA21.setBounds(81, 207, 38, 20);

		cuatroPorCuatroA22 = new JTextField();
		cuatroPorCuatroA22.setColumns(10);
		cuatroPorCuatroA22.setBounds(129, 207, 38, 20);

		cuatroPorCuatroA23 = new JTextField();
		cuatroPorCuatroA23.setColumns(10);
		cuatroPorCuatroA23.setBounds(177, 207, 38, 20);

		cuatroPorCuatroA24 = new JTextField();
		cuatroPorCuatroA24.setColumns(10);
		cuatroPorCuatroA24.setBounds(225, 207, 38, 20);

		cuatroPorCuatroA31 = new JTextField();
		cuatroPorCuatroA31.setColumns(10);
		cuatroPorCuatroA31.setBounds(81, 234, 38, 20);

		cuatroPorCuatroA32 = new JTextField();
		cuatroPorCuatroA32.setColumns(10);
		cuatroPorCuatroA32.setBounds(129, 234, 38, 20);

		cuatroPorCuatroA33 = new JTextField();
		cuatroPorCuatroA33.setColumns(10);
		cuatroPorCuatroA33.setBounds(177, 234, 38, 20);

		cuatroPorCuatroA34 = new JTextField();
		cuatroPorCuatroA34.setColumns(10);
		cuatroPorCuatroA34.setBounds(225, 234, 38, 20);

		cuatroPorCuatroA41 = new JTextField();
		cuatroPorCuatroA41.setColumns(10);
		cuatroPorCuatroA41.setBounds(81, 261, 38, 20);

		cuatroPorCuatroA42 = new JTextField();
		cuatroPorCuatroA42.setColumns(10);
		cuatroPorCuatroA42.setBounds(129, 261, 38, 20);

		cuatroPorCuatroA43 = new JTextField();
		cuatroPorCuatroA43.setColumns(10);
		cuatroPorCuatroA43.setBounds(177, 261, 38, 20);

		cuatroPorCuatroA44 = new JTextField();
		cuatroPorCuatroA44.setColumns(10);
		cuatroPorCuatroA44.setBounds(225, 261, 38, 20);

		cuatroPorCuatroB1 = new JTextField();
		cuatroPorCuatroB1.setColumns(10);
		cuatroPorCuatroB1.setBounds(300, 180, 38, 20);

		cuatroPorCuatroB2 = new JTextField();
		cuatroPorCuatroB2.setColumns(10);
		cuatroPorCuatroB2.setBounds(300, 207, 38, 20);

		cuatroPorCuatroB3 = new JTextField();
		cuatroPorCuatroB3.setColumns(10);
		cuatroPorCuatroB3.setBounds(300, 234, 38, 20);

		cuatroPorCuatroB4 = new JTextField();
		cuatroPorCuatroB4.setColumns(10);
		cuatroPorCuatroB4.setBounds(300, 261, 38, 20);

		cuatroPorCuatroAssume1 = new JTextField();
		cuatroPorCuatroAssume1.setBounds(71, 346, 62, 20);
		cuatroPorCuatroAssume1.setColumns(10);

		cuatroPorCuatroAssume2 = new JTextField();
		cuatroPorCuatroAssume2.setColumns(10);
		cuatroPorCuatroAssume2.setBounds(141, 346, 62, 20);

		cuatroPorCuatroAssume3 = new JTextField();
		cuatroPorCuatroAssume3.setColumns(10);
		cuatroPorCuatroAssume3.setBounds(212, 346, 62, 20);

		cuatroPorCuatroAssume4 = new JTextField();
		cuatroPorCuatroAssume4.setColumns(10);
		cuatroPorCuatroAssume4.setBounds(283, 346, 62, 20);
		this.hide4x4Matrix();

		contentPane.add(cuatroPorCuatroA11, 0);
		contentPane.add(cuatroPorCuatroA12, 0);
		contentPane.add(cuatroPorCuatroA13, 0);
		contentPane.add(cuatroPorCuatroA14, 0);
		contentPane.add(cuatroPorCuatroA21, 0);
		contentPane.add(cuatroPorCuatroA22, 0);
		contentPane.add(cuatroPorCuatroA23, 0);
		contentPane.add(cuatroPorCuatroA24, 0);
		contentPane.add(cuatroPorCuatroA31, 0);
		contentPane.add(cuatroPorCuatroA32, 0);
		contentPane.add(cuatroPorCuatroA33, 0);
		contentPane.add(cuatroPorCuatroA34, 0);
		contentPane.add(cuatroPorCuatroA41, 0);
		contentPane.add(cuatroPorCuatroA42, 0);
		contentPane.add(cuatroPorCuatroA43, 0);
		contentPane.add(cuatroPorCuatroA44, 0);
		contentPane.add(cuatroPorCuatroB1, 0);
		contentPane.add(cuatroPorCuatroB2, 0);
		contentPane.add(cuatroPorCuatroB3, 0);
		contentPane.add(cuatroPorCuatroB4, 0);
		contentPane.add(cuatroPorCuatroAssume1, 0);
		contentPane.add(cuatroPorCuatroAssume2, 0);
		contentPane.add(cuatroPorCuatroAssume3, 0);
		contentPane.add(cuatroPorCuatroAssume4, 0);

	}

	private void hide3x3Matrix() {

		tresPorTresA11.setVisible(false);
		tresPorTresA12.setVisible(false);
		tresPorTresA13.setVisible(false);
		tresPorTresA21.setVisible(false);
		tresPorTresA22.setVisible(false);
		tresPorTresA23.setVisible(false);
		tresPorTresA31.setVisible(false);
		tresPorTresA32.setVisible(false);
		tresPorTresA33.setVisible(false);
		tresPorTresB1.setVisible(false);
		tresPorTresB2.setVisible(false);
		tresPorTresB3.setVisible(false);
		tresPorTresAssume1.setVisible(false);
		tresPorTresAssume2.setVisible(false);
		tresPorTresAssume3.setVisible(false);
	}

	private void show4x4Matrix() {
		cuatroPorCuatroA11.setVisible(true);
		cuatroPorCuatroA12.setVisible(true);
		cuatroPorCuatroA13.setVisible(true);
		cuatroPorCuatroA14.setVisible(true);
		cuatroPorCuatroA21.setVisible(true);
		cuatroPorCuatroA22.setVisible(true);
		cuatroPorCuatroA23.setVisible(true);
		cuatroPorCuatroA24.setVisible(true);
		cuatroPorCuatroA31.setVisible(true);
		cuatroPorCuatroA32.setVisible(true);
		cuatroPorCuatroA33.setVisible(true);
		cuatroPorCuatroA34.setVisible(true);
		cuatroPorCuatroA41.setVisible(true);
		cuatroPorCuatroA42.setVisible(true);
		cuatroPorCuatroA43.setVisible(true);
		cuatroPorCuatroA44.setVisible(true);
		cuatroPorCuatroB1.setVisible(true);
		cuatroPorCuatroB2.setVisible(true);
		cuatroPorCuatroB3.setVisible(true);
		cuatroPorCuatroB4.setVisible(true);
		cuatroPorCuatroAssume1.setVisible(true);
		cuatroPorCuatroAssume2.setVisible(true);
		cuatroPorCuatroAssume3.setVisible(true);
		cuatroPorCuatroAssume4.setVisible(true);
	}

	private void hide4x4Matrix() {
		cuatroPorCuatroA11.setVisible(false);
		cuatroPorCuatroA12.setVisible(false);
		cuatroPorCuatroA13.setVisible(false);
		cuatroPorCuatroA14.setVisible(false);
		cuatroPorCuatroA21.setVisible(false);
		cuatroPorCuatroA22.setVisible(false);
		cuatroPorCuatroA23.setVisible(false);
		cuatroPorCuatroA24.setVisible(false);
		cuatroPorCuatroA31.setVisible(false);
		cuatroPorCuatroA32.setVisible(false);
		cuatroPorCuatroA33.setVisible(false);
		cuatroPorCuatroA34.setVisible(false);
		cuatroPorCuatroA41.setVisible(false);
		cuatroPorCuatroA42.setVisible(false);
		cuatroPorCuatroA43.setVisible(false);
		cuatroPorCuatroA44.setVisible(false);
		cuatroPorCuatroB1.setVisible(false);
		cuatroPorCuatroB2.setVisible(false);
		cuatroPorCuatroB3.setVisible(false);
		cuatroPorCuatroB4.setVisible(false);
		cuatroPorCuatroAssume1.setVisible(false);
		cuatroPorCuatroAssume2.setVisible(false);
		cuatroPorCuatroAssume3.setVisible(false);
		cuatroPorCuatroAssume4.setVisible(false);
	}
}