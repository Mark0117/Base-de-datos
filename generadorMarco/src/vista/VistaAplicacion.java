package vista;

import java.awt.ScrollPane;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

public class VistaAplicacion extends JFrame{
	public JPanel panel = new JPanel();
	
	public JRadioButton radioCsv = new JRadioButton("Csv",true);
	public JRadioButton radioSql = new JRadioButton("Sql");
	
	public ButtonGroup grupo = new ButtonGroup();
	
	public JLabel lbNomAtributo = new JLabel("Nombre de atributo");
	public JLabel lbNomBD = new JLabel("Nombre de la base");
	public JLabel lbNomFile = new JLabel("Nombre del archivo");
	public JLabel lbNomTable = new JLabel("Nombre de la tabla");
	public JLabel lbCombo = new JLabel("Tipo de dato");

	public JTextField txtNomAtributo = new JTextField();
	public JTextField txtNomBD = new JTextField();
	public JTextField txtNomFile = new JTextField();
	public JTextField txtNomTable = new JTextField();
	
	public JComboBox<String> comboTipo = new JComboBox<String>();
	
	public JScrollPane scroll = new JScrollPane();
	public JTable tabla = new JTable();
	
	public JButton btnAdd = new JButton("Insertar");
	public JButton btnDelete = new JButton("Eliminar");
	public JButton btnNewFile = new JButton("Generar");
	
	public VistaAplicacion(){
		setTitle("Base de datos");
		setSize(450, 430);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setBackground(Color.GRAY);
		setContentPane(panel);
		panel.setLayout(null);
	
		
		radioCsv.setBounds(88, 342, 50, 20);
		radioSql.setBounds(170, 342, 50, 20);
		grupo.add(radioCsv);
		grupo.add(radioSql);
		panel.add(radioCsv);
		panel.add(radioSql);

		lbNomAtributo.setBounds(158, 103, 120, 20);
		txtNomAtributo.setBounds(158, 134, 120, 20);
		panel.add(lbNomAtributo);
		panel.add(txtNomAtributo);
		
		lbNomBD.setBounds(28, 41, 120, 20);
		lbNomFile.setBounds(28, 103, 120, 20);
		lbNomTable.setBounds(158, 41, 120, 20);
		lbCombo.setBounds(288, 41, 120, 20);
		lbCombo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbNomBD);
		panel.add(lbNomFile);
		panel.add(lbNomTable);
		panel.add(lbCombo);
		
		txtNomBD.setBounds(28, 72, 120, 20);
		txtNomBD.enable(false);
		txtNomFile.setBounds(30, 134, 120, 20);
		txtNomTable.setBounds(156, 72, 120, 20);
		txtNomTable.enable(false);
		comboTipo.setBounds(301, 72, 110, 20);
		
		panel.add(txtNomBD);
		panel.add(txtNomFile);
		panel.add(txtNomTable);
		panel.add(comboTipo);
		
		scroll.setBounds(28,173, 250, 155);
		scroll.add(tabla);
		tabla.setModel(new DefaultTableModel( 
				new Object[][]{
						{null,null},
				}, new String[]{
						"Nombre de atributo", "Tipo de atributo"
				}));
		
		scroll.setViewportView(tabla);
		panel.add(scroll);
		
		btnAdd.setBounds(301, 244, 100, 30);
		btnDelete.setBounds(301, 285, 100, 30);
		btnNewFile.setBounds(301, 337, 100, 30);
		panel.add(btnAdd);
		panel.add(btnDelete);
		panel.add(btnNewFile);
		
		JLabel lblConjuntoDeDatos = new JLabel("CONJUNTO DE DATOS");
		lblConjuntoDeDatos.setFont(new Font("Arial", Font.BOLD, 15));
		lblConjuntoDeDatos.setBounds(140, 10, 184, 20);
		panel.add(lblConjuntoDeDatos);
		
		
		comboTipo.addItem("Nombre (M)");
		comboTipo.addItem("Nombre (F)");
		comboTipo.addItem("Nombres (F,M)");
		comboTipo.addItem("Apellido");
		comboTipo.addItem("País");
		comboTipo.addItem("Ciudad");
		comboTipo.addItem("Codigo postal");
		comboTipo.addItem("Celular");
		comboTipo.addItem("E-Mail");
		
		comboTipo.addItem("Calificaciones");
		comboTipo.addItem("Materias");
		comboTipo.addItem("Asistencias");
		comboTipo.addItem("");
		
		comboTipo.addItem("Incrementable");
		comboTipo.addItem("Fecha");
		comboTipo.addItem("Hora");
		
		
    	
		
		
	}	
}
