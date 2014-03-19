package gsb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;

public class Infos extends JFrame {
	public Infos() {
		setTitle("A Propos");
		setBounds(435, 250, 300, 180);
		getContentPane().setLayout(null);
		
		String[] infosRunSystem = InfosRunSystem.InfosRunSystem();
		
		JSeparator separatorTopInfo = new JSeparator();
		separatorTopInfo.setBounds(0, 23, 284, 16);
		getContentPane().add(separatorTopInfo);
		
		JLabel titlePageSystem = new JLabel("Informations de votre Syst\u00E8me");
		titlePageSystem.setBounds(6, 6, 252, 16);
		getContentPane().add(titlePageSystem);
		
		JLabel SystemName = new JLabel(" Système :");
		SystemName.setBounds(75, 34, 61, 16);
		getContentPane().add(SystemName);
		
		JLabel SystemArch = new JLabel("Architecture : ");
		SystemArch.setBounds(64, 61, 72, 18);
		getContentPane().add(SystemArch);
		
		JLabel SystemVersion = new JLabel("   Version du Syst\u00E8me : ");
		SystemVersion.setBounds(20, 90, 116, 16);
		getContentPane().add(SystemVersion);
		
		JLabel SystemJavaVersion = new JLabel("Java Version :");
		SystemJavaVersion.setBounds(64, 117, 72, 16);
		getContentPane().add(SystemJavaVersion);
		
		JLabel textSystemName = new JLabel(infosRunSystem[2]);
		textSystemName.setBounds(148, 33, 77, 16);
		getContentPane().add(textSystemName);
		
		JLabel textSystemArch = new JLabel(infosRunSystem[1]);
		textSystemArch.setBounds(148, 61, 61, 16);
		getContentPane().add(textSystemArch);
		
		JLabel textSystemVersion = new JLabel(infosRunSystem[3]);
		textSystemVersion.setBounds(148, 89, 35, 16);
		getContentPane().add(textSystemVersion);
		
		JLabel textSystemJavaVersion = new JLabel(infosRunSystem[4]);
		textSystemJavaVersion.setBounds(148, 117, 61, 16);
		getContentPane().add(textSystemJavaVersion);
	}
}
