package application;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.LimitExceededException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.ws.handler.MessageContext;

import resources.Const;

public class VarianceView {

	public VarianceModel varianceModel;
	public VarianceController varianceController;
	
	private JFrame fenetreApp;
	private JPanel panelPrincipal;
	private JPanel panelCheckBox;
	private JPanel panelTextBox;
	private JFileChooser fchDonnees;
	private JButton btnOuvrirFChooser;
	private JButton btnCalculAvecX;
	private JButton btnCalculAvecY;
	private JCheckBox chkListeSimple;
	private JCheckBox chkListeDouble;
	private JTextField txtCalculAvecX;
	private JTextField txtCalculAvecY;
	private JLabel lblFichierChoisi;
	private JLabel lblFonctionRegression;
	private JLabel lblResultatX;
	private JLabel lblResultatY;
	private JLabel lblVariance;
	private JLabel lblEcartType;
	private JLabel lblCorrelation;
	private JLabel lblIntervalle;
	private JLabel lblLimSup;
	private JLabel lblLimInf;
	private JLabel lblNbrDonneesRecues;
	
	
	public static void main(String[] args) {
		VarianceView varianceView = new VarianceView();
		
		varianceView.varianceModel = new VarianceModel();
		varianceView.varianceController = new VarianceController(varianceView.varianceModel);
		
		varianceView.initComposantes();
	}
	
	/**
	 * Met a jour les labels variance, ecart-type et correlation
	 * selon la checkbox choisie.
	 */
	public void updateDonnees(){
		
		if (chkListeSimple.isSelected()){
			lblVariance.setText("Variance : " + varianceModel.getVariance());
			lblEcartType.setText("ecart Type : " + varianceModel.getEcartType());
			lblCorrelation.setText("Correlation : ");
			lblIntervalle.setText("Intervalle : ");
			lblLimSup.setText("Limite superieure : ");
			lblLimInf.setText("Limite inferieure : ");
		} else if (chkListeDouble.isSelected()){
			lblVariance.setText("Variance : ");
			lblEcartType.setText("ecart Type : ");
			lblCorrelation.setText("Correlation : " + varianceModel.getCorrelation());
			lblIntervalle.setText("Intervalle : " + varianceModel.getIntervalle());
			lblLimSup.setText("Limite superieure : " + (900 - varianceModel.getIntervalle()));
			lblLimInf.setText("Limite inferieure : " + (900 + varianceModel.getIntervalle()));
			lblFonctionRegression.setText(varianceModel.getRegression());
		}
		
	}
	
	/**
	 * Initialise les composantes Swing
	 */
	private void initComposantes(){
		
		fenetreApp = new JFrame("Calcul de variance");
		panelPrincipal = new JPanel();
		panelCheckBox = new JPanel();
		panelTextBox = new JPanel();
		fchDonnees = new JFileChooser();
		btnOuvrirFChooser = new JButton("Choisir fichier");
		btnCalculAvecY = new JButton("Calculer avec Y");
		btnCalculAvecX = new JButton("Calculer avec X");
		chkListeSimple = new JCheckBox("Liste Simple");
		chkListeDouble = new JCheckBox("Liste de type X;Y");
		txtCalculAvecX = new JTextField("X");
		txtCalculAvecY = new JTextField("Y");
		lblFichierChoisi = new JLabel("Aucun fichier choisi");
		lblVariance = new JLabel("Variance : ");
		lblEcartType = new JLabel("ecart Type : ");
		lblCorrelation = new JLabel("Correlation : ");
		lblFonctionRegression = new JLabel("Fonction : ");
		lblResultatX = new JLabel("y = ");
		lblResultatY = new JLabel("x = ");
		lblIntervalle = new JLabel("Intervalle : ");
		lblLimInf = new JLabel("Limite inferieure : ");
		lblLimSup = new JLabel("Limite superieure : ");
		lblNbrDonneesRecues = new JLabel("Donnees reçues : ");
		
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.PAGE_AXIS));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier CSV", "csv");
		fchDonnees.setFileFilter(filter);
		
		fenetreApp.setSize(800, 400);
		panelPrincipal.setSize(new Dimension(800, 400));
		panelCheckBox.setSize(new Dimension(30, 400));
		panelTextBox.setSize(new Dimension(30, 400));
		
		txtCalculAvecX.setPreferredSize(new Dimension(60, 20));
		txtCalculAvecY.setPreferredSize(new Dimension(60, 20));
		
		panelTextBox.add(txtCalculAvecX);
		panelTextBox.add(btnCalculAvecX);
		panelTextBox.add(txtCalculAvecY);
		panelTextBox.add(btnCalculAvecY);
		
		panelCheckBox.add(chkListeSimple);
		panelCheckBox.add(chkListeDouble);
		
		panelPrincipal.add(lblFichierChoisi);
		panelPrincipal.add(btnOuvrirFChooser);
		panelPrincipal.add(panelCheckBox);
		panelPrincipal.add(lblNbrDonneesRecues);
		panelPrincipal.add(lblVariance);
		panelPrincipal.add(lblEcartType);
		panelPrincipal.add(lblCorrelation);
		panelPrincipal.add(lblIntervalle);
		panelPrincipal.add(lblLimInf);
		panelPrincipal.add(lblLimSup);
		panelPrincipal.add(panelTextBox);
		panelPrincipal.add(lblResultatX);
		panelPrincipal.add(lblResultatY);
		panelPrincipal.add(lblFonctionRegression);
		
		chkListeSimple.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chkListeSimple.isSelected()){
					chkListeDouble.setSelected(false);
				}
				
			}
		});
		
		chkListeDouble.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chkListeDouble.isSelected()){
					chkListeSimple.setSelected(false);
				}
				
			}
		});
		
		btnOuvrirFChooser.addActionListener(new java.awt.event.ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				extraireDonnees();
			}
			
		});
		
		btnCalculAvecX.addActionListener(new java.awt.event.ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				varianceController.calculAvecX(Float.parseFloat(txtCalculAvecX.getText()));
				lblResultatX.setText("y = " + varianceModel.getYSelonX());
			}
			
		});
		
		btnCalculAvecY.addActionListener(new java.awt.event.ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				varianceController.calculAvecY(Float.parseFloat(txtCalculAvecY.getText()));
				lblResultatY.setText("x = " + varianceModel.getXSelonY());
			}
			
		});
		
		fenetreApp.add(panelPrincipal);
		
		fenetreApp.setVisible(true);
	}
	
	/**
	 * Vide les labels variance, ecart-type et correlation
	 */
	private void viderComposantes(){
		
		lblVariance.setText("Variance : ");
		lblEcartType.setText("ecart Type : ");
		lblNbrDonneesRecues.setText("Donnees reçues : ");
		lblCorrelation.setText("Correlation : ");
		lblIntervalle.setText("Intervalle : ");
		lblLimInf.setText("Limite inferieure : ");
		lblLimSup.setText("Limite superieure : ");
	}
	
	/**
	 * Extrait les donnees d'un fichier et les inseres dans le ArrayList lstDonnees
	 * puis met a jour les valeurs des labels variance, ecart-type et correlation.
	 * @throws IOException 
	 */
	private void extraireDonnees(){
		
		int returnVal = fchDonnees.showOpenDialog(fenetreApp);
		boolean erreur = false;
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
			File fichier = fchDonnees.getSelectedFile();
			try {
				if(chkListeSimple.isSelected()){
					
					varianceController.extraireDonnees(fichier, Const.TYPE_X);
				} else if (chkListeDouble.isSelected()) {
					
					varianceController.extraireDonnees(fichier, Const.TYPE_XY);
				}
			} catch(NumberFormatException nfe){
						
				JOptionPane.showMessageDialog(fenetreApp, "Le fichier choisi contient une valeur invalide.");
				erreur = true;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				erreur = true;
				
			} finally {
				
			}
			
			if (!erreur){
				
				viderComposantes();
				
				lblFichierChoisi.setText(fichier.getAbsolutePath());
				lblNbrDonneesRecues.setText("Donnees reçues : ");
				
				updateDonnees();
			}
		}
	}
}
