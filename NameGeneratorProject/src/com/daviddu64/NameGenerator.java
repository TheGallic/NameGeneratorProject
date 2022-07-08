package com.daviddu64;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NameGenerator extends JFrame {

	private JPanel contentPane;
	private JTextField txtInfo;
	private JTextField txtName;
	private JTextField txtUse;
	private JTextField txtLetter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NameGenerator frame = new NameGenerator();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NameGenerator() {
		setFont(new Font("Arial", Font.PLAIN, 14));
		setResizable(false);
		setTitle("Générateur de prénoms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTypes = new JLabel("Choisissez le sexe:");
		lblTypes.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTypes.setBounds(10, 11, 231, 25);
		contentPane.add(lblTypes);

		JLabel lblLenght = new JLabel("Choisissez la longueur du prénom:");
		lblLenght.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLenght.setBounds(10, 43, 231, 25);
		contentPane.add(lblLenght);

		JLabel lblLetter = new JLabel("(Optionnel) Première lettre:");
		lblLetter.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLetter.setBounds(10, 79, 231, 25);
		contentPane.add(lblLetter);

		JLabel lblMode = new JLabel("Choisissez le mode de recherche:");
		lblMode.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMode.setBounds(10, 115, 231, 25);
		contentPane.add(lblMode);

		txtInfo = new JTextField();
		txtInfo.setText("En attente d'une recherche...");
		txtInfo.setForeground(Color.BLUE);
		txtInfo.setFont(new Font("Arial", Font.PLAIN, 14));
		txtInfo.setEditable(false);
		txtInfo.setBounds(10, 235, 231, 20);
		contentPane.add(txtInfo);
		txtInfo.setColumns(10);

		JLabel lblName = new JLabel("Prénom:");
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBounds(10, 266, 65, 25);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setEditable(false);
		txtName.setText("David");
		txtName.setForeground(Color.RED);
		txtName.setFont(new Font("Arial", Font.PLAIN, 14));
		txtName.setBounds(10, 291, 151, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtUse = new JTextField();
		txtUse.setText("0");
		txtUse.setHorizontalAlignment(SwingConstants.CENTER);
		txtUse.setForeground(Color.RED);
		txtUse.setFont(new Font("Arial", Font.PLAIN, 14));
		txtUse.setEditable(false);
		txtUse.setColumns(10);
		txtUse.setBounds(171, 291, 128, 20);
		contentPane.add(txtUse);

		JLabel lblUse = new JLabel("Utilisé depuis 2006");
		lblUse.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUse.setBounds(171, 266, 128, 25);
		contentPane.add(lblUse);

		JSpinner spinnerSex = new JSpinner();
		spinnerSex.setModel(new SpinnerListModel(new String[] { "Fille", "Gar\u00E7on" }));
		spinnerSex.setBounds(251, 14, 65, 20);
		spinnerSex.setEditor(new JSpinner.DefaultEditor(spinnerSex));
		contentPane.add(spinnerSex);

		JSpinner spinnerLenght = new JSpinner();
		spinnerLenght.setModel(new SpinnerNumberModel(3, 3, 8, 1));
		spinnerLenght.setBounds(251, 46, 65, 20);
		spinnerLenght.setEditor(new JSpinner.DefaultEditor(spinnerLenght));
		contentPane.add(spinnerLenght);

		txtLetter = new JTextField();
		txtLetter.setFont(new Font("Arial", Font.BOLD, 14));
		txtLetter.setBounds(251, 82, 65, 20);
		contentPane.add(txtLetter);
		txtLetter.setColumns(10);

		ButtonGroup bgroup = new ButtonGroup();

		JRadioButton rdbtnRandom = new JRadioButton("Aléatoire");
		rdbtnRandom.setSelected(true);
		rdbtnRandom.setBounds(247, 117, 151, 23);
		contentPane.add(rdbtnRandom);
		bgroup.add(rdbtnRandom);

		JRadioButton rdbtnMin = new JRadioButton("Le moins utilisé");
		rdbtnMin.setBounds(247, 143, 151, 23);
		contentPane.add(rdbtnMin);
		bgroup.add(rdbtnMin);

		JRadioButton rdbtnMax = new JRadioButton("Le plus utilisé");
		rdbtnMax.setBounds(247, 169, 151, 23);
		contentPane.add(rdbtnMax);
		bgroup.add(rdbtnMax);

		JButton btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameSex = spinnerSex.getValue().toString();
				String nameLenght = spinnerLenght.getValue().toString();
				String nameLetter = txtLetter.getText().toUpperCase();

				// On lit le fichier texte en relation avec le sexe choisie
				String nameFile = "";
				int NbrLine = 0;
				if (nameSex == "Fille") {
					nameFile = "/Fille.txt";
					NbrLine = 1410;
				} else {
					nameFile = "/Garcon.txt";
					NbrLine = 1317;
				}
				InputStream stream = NameGenerator.class.getResourceAsStream(nameFile);
				if (stream == null)
					JOptionPane.showMessageDialog(null, "Resource not located.");

				Scanner input = null;
				try {
					input = new Scanner(stream, "UTF-8");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Scanner error");
				}

				// On lit le fichier resource
				String line;
				List<String> tempList = new ArrayList<String>();
				for (int i = 0; i < NbrLine; i++) {

					line = input.nextLine();
					String[] splitLine = line.split(";");
					// Si le prénoms fait 8
					if (nameLenght == "8") {
						if (line.length() >= 8) {
							// Si le charactere de recherche est vide
							if (nameLetter.isEmpty()) {
								tempList.add(line);
							} else {
								if (nameLetter.charAt(0) == line.charAt(0)) {
									tempList.add(line);
								}
							}

						}
					} else {

						// Si le prénom fait moins de 8
						if (Integer.parseInt(nameLenght) == splitLine[0].length()) {
							// Si le charactere de recherche est vide
							if (nameLetter.isEmpty()) {
								tempList.add(line);

							} else {
								if (nameLetter.charAt(0) == line.charAt(0)) {
									tempList.add(line);
								}
							}
						}
					}
				}
				String result = "";
				int resultCount = tempList.size();
				if (resultCount == 0) {
					result = "Acun;0";
				} else {
					if (rdbtnRandom.isSelected() == true) {
						// On genere un nombre aléatoire pour un prénom dans la liste
						Random random = new Random();
						int randomValue = random.nextInt(resultCount + 0) + 0;
						result = tempList.get(randomValue);

					} else if (rdbtnMin.isSelected() == true) {
						int minValue = 3000;
						for (String value : tempList) {
							String[] tempResult = value.split(";");
							if (Integer.parseInt(tempResult[1]) < minValue) {
								result = value;
								minValue = Integer.parseInt(tempResult[1]);
							}
						}

					} else if (rdbtnMax.isSelected() == true) {
						int maxValue = 0;
						for (String value : tempList) {
							String[] tempResult = value.split(";");
							if (Integer.parseInt(tempResult[1]) > maxValue) {
								result = value;
								maxValue = Integer.parseInt(tempResult[1]);
							}
						}
					}
				}
				String[] finalResult = result.split(";");
				txtInfo.setText(resultCount + " prénoms on été trouvé!!");
				txtName.setText(finalResult[0]);
				txtUse.setText(finalResult[1] + " fois.");

			}
		});
		btnSearch.setBounds(309, 288, 116, 23);
		contentPane.add(btnSearch);
	}
}
