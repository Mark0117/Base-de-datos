package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.ModeloAplicacion;
import vista.VistaAplicacion;

public class ControladorAplicacion implements ActionListener{
	private VistaAplicacion vista = new VistaAplicacion();
	private ModeloAplicacion modelo = new ModeloAplicacion();
	
	public ControladorAplicacion(VistaAplicacion vista, ModeloAplicacion modelo){
		this.vista = vista;
		this.modelo = modelo;
		
		vista.btnAdd.addActionListener(this);
		vista.btnDelete.addActionListener(this);
		vista.btnNewFile.addActionListener(this);
		
		vista.radioCsv.addActionListener(this);
		vista.radioSql.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object boton = e.getSource();
		
		 if (boton == vista.radioCsv){
			if (vista.radioCsv.isSelected()) {
				vista.txtNomFile.enable(true);
				vista.txtNomBD.enable(false);
				vista.txtNomTable.enable(false);
			}
		} else if (boton == vista.radioSql){
			if (vista.radioSql.isSelected()) {
				vista.txtNomBD.enable(true);
				vista.txtNomTable.enable(true);
				vista.txtNomFile.enable(true);
			}
		} else if (boton == vista.btnAdd) {
			if (vista.txtNomAtributo.getText().equals("")) {
				JOptionPane.showMessageDialog(vista, "Nombre de atributo vacio");
			}else {
				modelo.addDate((String)vista.txtNomAtributo.getText(), (String)vista.comboTipo.getSelectedItem(), String.valueOf(vista.comboTipo.getSelectedIndex()) );
				llenarTabla();
			}
			
		} else if (boton == vista.btnDelete){
			modelo.deleteDate();
			llenarTabla();
		} else if (boton == vista.btnNewFile){
			//modelo.prueba();
			if (vista.radioCsv.isSelected()) {
				String nomFile = vista.txtNomFile.getText();
				if (nomFile.equals("")) {
					JOptionPane.showMessageDialog(vista, "Campo vacio");
				} else {
					modelo.fileCsv(nomFile);
					JOptionPane.showMessageDialog(vista, "Proceso terminado");
				}
				
			} else if (vista.radioSql.isSelected()) {
				String nomBd = vista.txtNomBD.getText();
				String nomFile = vista.txtNomFile.getText();
				String nomTable = vista.txtNomTable.getText();
				
				if (nomBd.equals("") || nomFile.equals("") || nomTable.equals("")) {
					JOptionPane.showMessageDialog(vista, "Campos vacios");
				} else {
					modelo.fileSql(nomFile, nomBd, nomTable);
					JOptionPane.showMessageDialog(vista, "Proceso terminado");
				}
			}
		}
		
	}
	
	
	public void llenarTabla() {
		limpiarTabla();
		DefaultTableModel modelo =(DefaultTableModel) vista.tabla.getModel();
		ArrayList<String> nameAtributo = this.modelo.nameAtributo;
		ArrayList<String> nameTipoAtributo = this.modelo.nametypeAtributo;
		try {
			for (int i = 0; i < nameAtributo.size(); i++) {
				modelo.addRow(new Object[1]);
				modelo.setValueAt(nameAtributo.get(i), i, 0);
				modelo.setValueAt(nameTipoAtributo.get(i), i, 1);
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void limpiarTabla(){
		DefaultTableModel modelo=(DefaultTableModel) vista.tabla.getModel();
		int totalFilas=vista.tabla.getRowCount();
		for (int f = totalFilas; f>0; f--) {	
			modelo.removeRow(0);
		}
	}
}
