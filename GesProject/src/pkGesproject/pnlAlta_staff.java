package pkGesproject;

import java.io.File;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class pnlAlta_staff extends JScrollPane {
	GesIdioma rec = GesIdioma.obtener_instancia();
	JButton jbtnCrear = new JButton("Crear");
	JButton jbtnSalir = new JButton("Salir");
	JButton jbtnExaminar = new JButton("Examinar");
	
	JTextField jtxt1 = new JTextField(20);
	JTextField jtxt2 = new JTextField(20);
	JTextField jtxt3 = new JTextField(20);
	JTextField jtxt4 = new JTextField(20);
	JTextField jtxt5 = new JTextField(20);
	JComboBox jcmbPais = new JComboBox();
	JTextField jtxt7 = new JTextField(20);
	JTextField jtxt8 = new JTextField(20);
	JTextField jtxt9 = new JTextField(20);
	JTextField jtxt10 = new JTextField(20);
	JTextField jtxt11 = new JTextField(20);
	JTextField jtxt12 = new JTextField(20);
	JTextField jtxt13 = new JTextField(20);
	JTextField jtxt14 = new JTextField(20);
	JTextField jtxt15 = new JTextField(20);
	JTextField jtxt16 = new JTextField(20);
	JCheckBox jcb1 = new JCheckBox();
	JLabel jlblempty = new JLabel("    "); 
	JLabel jlblempty2 = new JLabel("    ");
	JLabel jlblIDstaff = new JLabel("ID STAFF:");
	JLabel jlblDNI = new JLabel("DNI:");
	JLabel jlblNombre = new JLabel(rec.idioma[rec.eleidioma][2]);
	JLabel jlblCategoria = new JLabel("Categoria:");
	JLabel jlblFec_nac = new JLabel("Fecha de nacimiento: ");
	JLabel jlblPais = new JLabel("Pa�s:");
	JLabel jlblRegion = new JLabel("Regi�n:");
	JLabel jlblCiudad = new JLabel("Ciudad:");
	JLabel jlblDireccion = new JLabel("Direcci�n:");
	JLabel jlblCod_post = new JLabel("C�digo postal: ");
	JLabel jlblTelefono = new JLabel("Telefono:");
	JLabel jlblFoto = new JLabel("Foto:");
	JLabel jlblNick = new JLabel("Nick:");
	JLabel jlblContrasenia = new JLabel("Contrase�a:");
	JLabel jlblPermisos = new JLabel("Permisos: ");
	JLabel jlblCod_partner = new JLabel("Codigo partner:");
	JLabel jlblRepresentante = new JLabel("Representante:");
	JPanel jp = new JPanel();
	public pnlAlta_staff() {

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblstaff = new GridBagConstraints();
		poslblstaff.gridx = 1;
		poslblstaff.gridy = 0;
		poslblstaff.gridwidth = 3;
		poslblstaff.gridheight = 1;

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblempty = new GridBagConstraints();
		poslblempty.gridx = 1;
		poslblempty.gridy = 1;
		poslblempty.gridwidth = 1;
		poslblempty.gridheight = 1;

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblidstaff = new GridBagConstraints();
		poslblidstaff.gridx = 0;
		poslblidstaff.gridy = 2;
		poslblidstaff.gridwidth = 2;
		poslblidstaff.gridheight = 1;
		poslblidstaff.weighty = 1.0;
		poslblidstaff.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt1 = new GridBagConstraints();
		postxt1.gridx = 2;
		postxt1.gridy = 2;
		postxt1.gridwidth = 2;
		postxt1.gridheight = 1;

		GridBagConstraints poslbldni = new GridBagConstraints();
		poslbldni.gridx = 0;
		poslbldni.gridy = 3;
		poslbldni.gridwidth = 2;
		poslbldni.gridheight = 1;
		poslblidstaff.weighty = 0.0;
		poslbldni.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt2 = new GridBagConstraints();
		postxt2.gridx = 2;
		postxt2.gridy = 3;
		postxt2.gridwidth = 2;
		postxt2.gridheight = 1;

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblnombre = new GridBagConstraints();
		poslblnombre.gridx = 0;
		poslblnombre.gridy = 4;
		poslblnombre.gridwidth = 2;
		poslblnombre.gridheight = 1;
		poslblnombre.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt3 = new GridBagConstraints();
		postxt3.gridx = 2;
		postxt3.gridy = 4;
		postxt3.gridwidth = 2;
		postxt3.gridheight = 1;

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblCategoria = new GridBagConstraints();
		poslblCategoria.gridx = 0;
		poslblCategoria.gridy = 5;
		poslblCategoria.gridwidth = 2;
		poslblCategoria.gridheight = 1;
		poslblCategoria.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt4 = new GridBagConstraints();
		postxt4.gridx = 2;
		postxt4.gridy = 5;
		postxt4.gridwidth = 2;
		postxt4.gridheight = 1;

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblFec_nac = new GridBagConstraints();
		poslblFec_nac.gridx = 0;
		poslblFec_nac.gridy = 6;
		poslblFec_nac.gridwidth = 2;
		poslblFec_nac.gridheight = 1;
		poslblFec_nac.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt5 = new GridBagConstraints();
		postxt5.gridx = 2;
		postxt5.gridy = 6;
		postxt5.gridwidth = 2;
		postxt5.gridheight = 1;

		jp.setLayout(new GridBagLayout());
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
		

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
		GridBagConstraints poslblContrasemia = new GridBagConstraints();
		poslblContrasemia.gridx = 0;
		poslblContrasemia.gridy = 15;
		poslblContrasemia.gridwidth = 2;
		poslblContrasemia.gridheight = 1;
		poslblContrasemia.anchor = GridBagConstraints.WEST;

		GridBagConstraints postxt14 = new GridBagConstraints();
		postxt14.gridx = 2;
		postxt14.gridy = 15;
		postxt14.gridwidth = 2;
		postxt14.gridheight = 1;

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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

		jp.setLayout(new GridBagLayout());
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
		
		
		jp.add(jlblIDstaff, poslblstaff);
		jp.add(jlblempty, poslblempty);
		jp.add(jlblempty2, poslblempty2);
		jp.add(jlblIDstaff, poslblidstaff);
		poslblidstaff.weighty = 0.0;
		jp.add(jtxt1, postxt1);
		jtxt1.setEditable(false); //impide la modificaci�n del campo ID STAFF
		jp.add(jlblDNI, poslbldni);
		jp.add(jtxt2, postxt2);
		jp.add(jlblNombre, poslblnombre);
		jp.add(jtxt3, postxt3);
		jp.add(jlblCategoria, poslblCategoria);
		jp.add(jtxt4, postxt4);
		jp.add(jlblFec_nac, poslblFec_nac);
		jp.add(jtxt5, postxt5);
		jp.add(jcmbPais,posjcmbPais);
		jcmbPais.setPreferredSize(new Dimension(233,30));
		jcmbPais.addItem(rec.idioma[rec.eleidioma][17]);
		jcmbPais.addItem("Inglaterra");
		jcmbPais.addItem("Alemania");
		jcmbPais.addItem("Francia");
		jcmbPais.addItem("Italia");
		jp.add(jlblPais, poslblPais);
		jp.add(jtxt7, postxt7);
		jp.add(jlblRegion, poslblRegion);
		jp.add(jtxt8, postxt8);
		jp.add(jlblCiudad, poslblCiudad);
		jp.add(jtxt9, postxt9);
		jp.add(jlblDireccion, poslblDireccion);
		jp.add(jtxt10, postxt10);
		
		jtxt10.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      if(((caracter < '0') ||
		         (caracter > '9')) &&
		         (caracter != KeyEvent.VK_BACK_SPACE))
		      {
		         e.consume();  
		      }
		   }
		});
		
		jp.add(jlblCod_post, poslblCod_post);
		jp.add(jtxt11, postxt11);
		
		jtxt11.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();
		      
		      if(((caracter < '0') ||
		         (caracter > '9')) &&
		         (caracter != KeyEvent.VK_BACK_SPACE))
		      {
		         e.consume();  
		      }
		   }
		});
		
		jp.add(jlblTelefono, poslblTelefono);
		jp.add(jtxt12, postxt12);
		jp.add(jlblFoto, poslblFoto);
		jp.add(jtxt13, postxt13);
		jp.add(jlblNick, poslblNick);
		jp.add(jtxt14, postxt14);
		jp.add(jlblContrasenia, poslblContrasemia);
		jp.add(jtxt15, postxt15);
		jp.add(jlblPermisos, poslblPermisos);
		jp.add(jtxt16, postxt16);
		jp.add(jlblCod_partner, poslblCod_part);
		jp.add(jcb1, poscb1);
		jp.add(jlblRepresentante, poslblRepresentante);

		jp.add(jbtnCrear, posBtnaceptar);
		jp.add(jbtnSalir, posBtncancelar);
		jp.add(jbtnExaminar, posBtnExaminar);
		
		jbtnExaminar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser filechooser = new JFileChooser();
				int returnVal = filechooser.showSaveDialog(null);
			
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = filechooser.getSelectedFile();
				jtxt12.setText(file.getPath());
			    } 
					
			}
			
		});
		this.setViewportView(jp);
	}
}