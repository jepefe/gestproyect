package pkGesproject;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class pnlAlta_staff extends JPanel {

	JButton jbtnCrear = new JButton("Crear");
	JButton jbtnSalir = new JButton("Salir");
	JButton jbtnExaminar = new JButton("Examinar");
	JTextField jtxtIDstaff = new JTextField(20);
	JTextField jtxtDNI = new JTextField(20);
	JTextField jtxtNombre = new JTextField(20);
	JTextField jtxtCategoria = new JTextField(20);
	JTextField jtxtFec_nac = new JTextField(20);
	//JTextField jtxt6 = new JTextField(20);
	JComboBox jcmbPais = new JComboBox();
	JTextField jtxt7 = new JTextField(20);
	JTextField jtxt8 = new JTextField(20);
	JTextField jtxt9 = new JTextField(20);
	JTextField jtxt10 = new JTextField(20);
	JTextField jtxt11 = new JTextField(20);
	JTextField jtxt12 = new JTextField(20);
	JTextField jtxt13 = new JTextField(20);
	JPasswordField jpswContraseña = new JPasswordField(20); //JPasswordField se usa especialmente para campos de contraseña
	JTextField jtxt15 = new JTextField(20);
	JTextField jtxt16 = new JTextField(20);
	JCheckBox jcb1 = new JCheckBox();
	JLabel jlblstaff = new JLabel(
			"<html><b><font color=#81BEF7 size=5>Dar de alta STAFF</font></b></html>"); //metemos codigo html para dar formato a label
	JLabel jlblempty = new JLabel("    "); // creamos labels vacios para hacer
											// espacios entre los controles
	JLabel jlblempty2 = new JLabel("    ");
	JLabel jlblIDstaff = new JLabel("ID STAFF:");
	JLabel jlblDNI = new JLabel("DNI:");
	JLabel jlblNombre = new JLabel("Nombre:");
	JLabel jlblCategoria = new JLabel("Categoria:");
	JLabel jlblFec_nac = new JLabel("Fecha de nacimiento: ");
	JLabel jlblPais = new JLabel("País:");
	JLabel jlblRegion = new JLabel("Región:");
	JLabel jlblCiudad = new JLabel("Ciudad:");
	JLabel jlblDireccion = new JLabel("Dirección:");
	JLabel jlblCod_post = new JLabel("Código postal: ");
	JLabel jlblTelefono = new JLabel("Telefono:");
	JLabel jlblFoto = new JLabel("Foto:");
	JLabel jlblNick = new JLabel("Nick:");
	JLabel jlblContrasenia = new JLabel("Contraseña:");
	JLabel jlblPermisos = new JLabel("Permisos: ");
	JLabel jlblCod_partner = new JLabel("Codigo partner:");
	JLabel jlblRepresentante = new JLabel("Representante:");

	public pnlAlta_staff() {

		this.setOpaque(true);
		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblstaff = new GridBagConstraints();
		poslblstaff.gridx = 1;
		poslblstaff.gridy = 0;
		poslblstaff.gridwidth = 3;
		poslblstaff.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblempty = new GridBagConstraints();
		poslblempty.gridx = 1;
		poslblempty.gridy = 1;
		poslblempty.gridwidth = 1;
		poslblempty.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblidstaff = new GridBagConstraints();
		poslblidstaff.gridx = 0;
		poslblidstaff.gridy = 2;
		poslblidstaff.gridwidth = 2;
		poslblidstaff.gridheight = 1;
		poslblidstaff.weighty = 1.0;
		poslblidstaff.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxtIDstaff = new GridBagConstraints();
		postxtIDstaff.gridx = 2;
		postxtIDstaff.gridy = 2;
		postxtIDstaff.gridwidth = 2;
		postxtIDstaff.gridheight = 1;

		GridBagConstraints poslbldni = new GridBagConstraints();
		poslbldni.gridx = 0;
		poslbldni.gridy = 3;
		poslbldni.gridwidth = 2;
		poslbldni.gridheight = 1;
		poslblidstaff.weighty = 0.0;
		poslbldni.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxtDNI = new GridBagConstraints();
		postxtDNI.gridx = 2;
		postxtDNI.gridy = 3;
		postxtDNI.gridwidth = 2;
		postxtDNI.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblnombre = new GridBagConstraints();
		poslblnombre.gridx = 0;
		poslblnombre.gridy = 4;
		poslblnombre.gridwidth = 2;
		poslblnombre.gridheight = 1;
		poslblnombre.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxtNombre = new GridBagConstraints();
		postxtNombre.gridx = 2;
		postxtNombre.gridy = 4;
		postxtNombre.gridwidth = 2;
		postxtNombre.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblCategoria = new GridBagConstraints();
		poslblCategoria.gridx = 0;
		poslblCategoria.gridy = 5;
		poslblCategoria.gridwidth = 2;
		poslblCategoria.gridheight = 1;
		poslblCategoria.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxtCategoria = new GridBagConstraints();
		postxtCategoria.gridx = 2;
		postxtCategoria.gridy = 5;
		postxtCategoria.gridwidth = 2;
		postxtCategoria.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblFec_nac = new GridBagConstraints();
		poslblFec_nac.gridx = 0;
		poslblFec_nac.gridy = 6;
		poslblFec_nac.gridwidth = 2;
		poslblFec_nac.gridheight = 1;
		poslblFec_nac.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxtFec_nac = new GridBagConstraints();
		postxtFec_nac.gridx = 2;
		postxtFec_nac.gridy = 6;
		postxtFec_nac.gridwidth = 2;
		postxtFec_nac.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblPais = new GridBagConstraints();
		poslblPais.gridx = 0;
		poslblPais.gridy = 7;
		poslblPais.gridwidth = 2;
		poslblPais.gridheight = 1;
		poslblPais.anchor = GridBagConstraints.WEST;

		GridBagConstraints posjcmbPais = new GridBagConstraints();
		posjcmbPais.gridx = 2;
		posjcmbPais.gridy = 7;
		posjcmbPais.gridwidth = 2;
		posjcmbPais.gridheight = 1;
		

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblRegion = new GridBagConstraints();
		poslblRegion.gridx = 0;
		poslblRegion.gridy = 8;
		poslblRegion.gridwidth = 2;
		poslblRegion.gridheight = 1;
		poslblRegion.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt7 = new GridBagConstraints();
		postxt7.gridx = 2;
		postxt7.gridy = 8;
		postxt7.gridwidth = 2;
		postxt7.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblCiudad = new GridBagConstraints();
		poslblCiudad.gridx = 0;
		poslblCiudad.gridy = 9;
		poslblCiudad.gridwidth = 2;
		poslblCiudad.gridheight = 1;
		poslblCiudad.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt8 = new GridBagConstraints();
		postxt8.gridx = 2;
		postxt8.gridy = 9;
		postxt8.gridwidth = 2;
		postxt8.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblDireccion = new GridBagConstraints();
		poslblDireccion.gridx = 0;
		poslblDireccion.gridy = 10;
		poslblDireccion.gridwidth = 2;
		poslblDireccion.gridheight = 1;
		poslblDireccion.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt9 = new GridBagConstraints();
		postxt9.gridx = 2;
		postxt9.gridy = 10;
		postxt9.gridwidth = 2;
		postxt9.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblCod_post = new GridBagConstraints();
		poslblCod_post.gridx = 0;
		poslblCod_post.gridy = 11;
		poslblCod_post.gridwidth = 2;
		poslblCod_post.gridheight = 1;
		poslblCod_post.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt10 = new GridBagConstraints();
		postxt10.gridx = 2;
		postxt10.gridy = 11;
		postxt10.gridwidth = 2;
		postxt10.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblTelefono = new GridBagConstraints();
		poslblTelefono.gridx = 0;
		poslblTelefono.gridy = 12;
		poslblTelefono.gridwidth = 2;
		poslblTelefono.gridheight = 1;
		poslblTelefono.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt11 = new GridBagConstraints();
		postxt11.gridx = 2;
		postxt11.gridy = 12;
		postxt11.gridwidth = 2;
		postxt11.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblFoto = new GridBagConstraints();
		poslblFoto.gridx = 0;
		poslblFoto.gridy = 13;
		poslblFoto.gridwidth = 2;
		poslblFoto.gridheight = 1;
		poslblFoto.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt12 = new GridBagConstraints();
		postxt12.gridx = 2;
		postxt12.gridy = 13;
		postxt12.gridwidth = 2;
		postxt12.gridheight = 1;

		GridBagConstraints posBtnExaminar = new GridBagConstraints();
		posBtnExaminar.gridx = 5;
		posBtnExaminar.gridy = 13;
		posBtnExaminar.gridwidth = 1;
		posBtnExaminar.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblNick = new GridBagConstraints();
		poslblNick.gridx = 0;
		poslblNick.gridy = 14;
		poslblNick.gridwidth = 2;
		poslblNick.gridheight = 1;
		poslblNick.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt13 = new GridBagConstraints();
		postxt13.gridx = 2;
		postxt13.gridy = 14;
		postxt13.gridwidth = 2;
		postxt13.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblContrasemia = new GridBagConstraints();
		poslblContrasemia.gridx = 0;
		poslblContrasemia.gridy = 15;
		poslblContrasemia.gridwidth = 2;
		poslblContrasemia.gridheight = 1;
		poslblContrasemia.anchor = GridBagConstraints.WEST;

		GridBagConstraints posjpswContraseña = new GridBagConstraints();
		posjpswContraseña.gridx = 2;
		posjpswContraseña.gridy = 15;
		posjpswContraseña.gridwidth = 2;
		posjpswContraseña.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblPermisos = new GridBagConstraints();
		poslblPermisos.gridx = 0;
		poslblPermisos.gridy = 16;
		poslblPermisos.gridwidth = 2;
		poslblPermisos.gridheight = 1;
		poslblPermisos.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt15 = new GridBagConstraints();
		postxt15.gridx = 2;
		postxt15.gridy = 16;
		postxt15.gridwidth = 2;
		postxt15.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblCod_part = new GridBagConstraints();
		poslblCod_part.gridx = 0;
		poslblCod_part.gridy = 17;
		poslblCod_part.gridwidth = 2;
		poslblCod_part.gridheight = 1;
		poslblCod_part.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt16 = new GridBagConstraints();
		postxt16.gridx = 2;
		postxt16.gridy = 17;
		postxt16.gridwidth = 2;
		postxt16.gridheight = 1;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblRepresentante = new GridBagConstraints();
		poslblRepresentante.gridx = 0;
		poslblRepresentante.gridy = 18;
		poslblRepresentante.gridwidth = 2;
		poslblRepresentante.gridheight = 1;
		poslblRepresentante.anchor = GridBagConstraints.WEST;

		GridBagConstraints poscb1 = new GridBagConstraints();
		poscb1.gridx = 2;
		poscb1.gridy = 18;
		poscb1.gridwidth = 2;
		poscb1.gridheight = 1;
		poscb1.anchor = GridBagConstraints.WEST;

		this.setLayout(new GridBagLayout());
		GridBagConstraints poslblempty2 = new GridBagConstraints();
		poslblempty2.gridx = 0;
		poslblempty2.gridy = 19;
		poslblempty2.gridwidth = 1;
		poslblempty2.gridheight = 1;

		GridBagConstraints posBtnaceptar = new GridBagConstraints();
		posBtnaceptar.gridx = 2;
		posBtnaceptar.gridy = 20;
		posBtnaceptar.gridwidth = 1;
		posBtnaceptar.gridheight = 1;

		GridBagConstraints posBtncancelar = new GridBagConstraints();
		posBtncancelar.gridx = 3;
		posBtncancelar.gridy = 20;
		posBtncancelar.gridwidth = 1;
		posBtncancelar.gridheight = 1;
		posBtncancelar.anchor = GridBagConstraints.WEST;
		
		
		this.add(jlblstaff, poslblstaff);
		this.add(jlblempty, poslblempty);
		this.add(jlblempty2, poslblempty2);
		this.add(jlblIDstaff, poslblidstaff);
		poslblidstaff.weighty = 0.0;
		this.add(jtxtIDstaff, postxtIDstaff);
		jtxtIDstaff.setEditable(false); //impide la modificación del campo ID STAFF
		this.add(jlblDNI, poslbldni);
		this.add(jtxtDNI, postxtDNI);
		this.add(jlblNombre, poslblnombre);
		this.add(jtxtNombre, postxtNombre);
		this.add(jlblCategoria, poslblCategoria);
		this.add(jtxtCategoria, postxtCategoria);
		this.add(jlblFec_nac, poslblFec_nac);
		this.add(jtxtFec_nac, postxtFec_nac);

		//this.add(jtxt6, postxt6);
		this.add(jcmbPais,posjcmbPais);
		jcmbPais.addItem("España                                   ");//metemos espacios en blanco para que combobox ocupe el mismo tamaño que los campos de texto (hay que buscar otra solución)
		jcmbPais.addItem("Inglaterra");
		jcmbPais.addItem("Alemania");
		jcmbPais.addItem("Francia");
		jcmbPais.addItem("Italia");
		this.add(jlblPais, poslblPais);
		this.add(jtxt7, postxt7);
		this.add(jlblRegion, poslblRegion);
		this.add(jtxt8, postxt8);
		this.add(jlblCiudad, poslblCiudad);
		this.add(jtxt9, postxt9);
		this.add(jlblDireccion, poslblDireccion);
		this.add(jtxt10, postxt10);
		this.add(jlblCod_post, poslblCod_post);
		this.add(jtxt11, postxt11);
		
		jtxt11.addKeyListener(new KeyAdapter()// creamos KeyListener
		{
		   public void keyTyped(KeyEvent e)//creamos evento de KeyListener
		   {
		      char caracter = e.getKeyChar();

		      
		      if(((caracter < '0') ||
		         (caracter > '9')) &&
		         (caracter != KeyEvent.VK_BACK_SPACE)) //keylistener impide la introducción de las letras
		      {
		         e.consume();  
		      }
		   }
		});
		
		this.add(jlblTelefono, poslblTelefono);
		this.add(jtxt12, postxt12);
		this.add(jlblFoto, poslblFoto);
		this.add(jtxt13, postxt13);
		this.add(jlblNick, poslblNick);
		this.add(jpswContraseña, posjpswContraseña);
		
		jpswContraseña.addKeyListener(new KeyAdapter()// creamos KeyListener
		{
			public void keyTyped(KeyEvent e)//creamos evento de KeyListener
			{
				if (jpswContraseña.getText().length()==18)//limitamos el número de caracteres que se puede introducir a 18
					e.consume();
			}
		});
		
		this.add(jlblContrasenia, poslblContrasemia);
		this.add(jtxt15, postxt15);
		this.add(jlblPermisos, poslblPermisos);
		this.add(jtxt16, postxt16);
		this.add(jlblCod_partner, poslblCod_part);
		this.add(jcb1, poscb1);
		this.add(jlblRepresentante, poslblRepresentante);

		this.add(jbtnCrear, posBtnaceptar);
		this.add(jbtnSalir, posBtncancelar);
		this.add(jbtnExaminar, posBtnExaminar);
		
	}
}
