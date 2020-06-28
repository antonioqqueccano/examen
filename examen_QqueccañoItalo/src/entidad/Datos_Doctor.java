package entidad;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Doctormodel;
import util.Conversiones;
import util.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Datos_Doctor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtFecNac;
	private JTextField txtSueldo;
	private JTextField txtCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Datos_Doctor frame = new Datos_Doctor();
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
	public Datos_Doctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro doctor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(156, 11, 134, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(23, 58, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI: ");
		lblNewLabel_2.setBounds(23, 89, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_3.setBounds(23, 123, 113, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sueldo");
		lblNewLabel_4.setBounds(23, 152, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Correo: ");
		lblNewLabel_5.setBounds(23, 183, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			Doctor dr= new Doctor();
			
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIngresar.setBounds(320, 54, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnActulizar = new JButton("Actualizar");
		btnActulizar.addActionListener(new ActionListener() {{
			if (idDoctor == -1) {
				mensaje("Se debe seleccionar una fila");
			} else {
				String nom = txtNombre.getText();
				String dni = txtDni.getText();
				String fec= txtFecNac.getText();
				Double sue= Double.parseDouble(txtSueldo.getText());
				String cor= txtCorreo.getText();

				if(nom.matches(Validaciones.NOMBRE) == false) {
					mensaje("El nombre es de 3 a 30 caracteres");
				}else if(dni.matches(Validaciones.DNI) == false) {
					mensaje("El DNI son de 8 dígitos");
				}else if(fec.matches(Validaciones.FECHA) == false) {
					mensaje("La fecha es el siguiente formato:yyyy-MM-dd ");
				}else if(sue.matches(Validaciones.SUELDO) == false) {
					mensaje("El sueldo debe contener decimal");
				}else if(cor.matches(Validaciones.CORREO) == false) {
					mensaje("correo es el siguiente formato:____@_____._____ ");
				}else {
					
					Doctor obj= new Doctor();
					obj.setNombre(nom);
					obj.setDni(dni);
					obj.setFecha(Conversiones.toFecha(fec));
					obj.setSueldo(sue);
					obj.setCorreo(cor);


					Doctormodel d= new Doctormodel();
					int s= d.insertaDoctor(obj);
					if (s > 0) {
						mensaje("Se actualizó correctamente");
						listaDoctor();
						limpiarCajasTexto();
					} else {
						mensaje("Error en el actualizar");
					}
				}
			}
		}
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnActulizar.setBounds(320, 85, 89, 23);
		contentPane.add(btnActulizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			
			
			{
				if (idDoctor == -1) {
					mensaje("Seleccione una fila");
				} else {
					Doctormodel m = new Doctormodel();
					int s = m.eliminaDoctor(idDoctor);
					if (s > 0) {
						mensaje("Se eliminó correctamente");
						listaDoctor();
						limpiarCajasTexto();
					} else {
						mensaje("Error al eliminar");
					}
				}
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		btnEliminar.setBounds(320, 119, 89, 23);
		contentPane.add(btnEliminar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(146, 51, 144, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(146, 86, 144, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtFecNac = new JTextField();
		txtFecNac.setBounds(146, 120, 144, 20);
		contentPane.add(txtFecNac);
		txtFecNac.setColumns(10);
		
		txtSueldo = new JTextField();
		txtSueldo.setBounds(146, 149, 144, 20);
		contentPane.add(txtSueldo);
		txtSueldo.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(146, 180, 144, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
	}
	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtDni.setText("");
		txtFecNac.setText("");
		txtSueldo.setText("");
		txtCorreo.setText("");
		txtNombre.requestFocus();
	}

	void listaDoctor() {
		Doctormodel m = new Doctormodel();
		List<Doctor> data = m.listaDoctor();



		DefaultTableModel dtm = (DefaultTableModel) table.getModel();


		dtm.setRowCount(0);



		for (Doctor aux : data) {
			Object[] fila = { aux.getIdDoctor()(), aux.getNombre(), aux.getDni(), aux.getFecha, aux.getSueldo, aux.getCorreo };
			dtm.addRow(fila);
		}
	}
		
	
	public void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
}
