package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PnlAltasocio extends JPanel{


	JButton jbtnaceptar, jbtncancelar;
	JLabel jlblnombre, jlblmail;
	JTextField jtxtnombre, jtxtmail;
	GesIdioma rec = GesIdioma.obtener_instancia();
	
	public PnlAltasocio (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		this.setLayout(new GridBagLayout());
		
		System.out.println("idioma pnlsocio"+rec.eleidioma);
		jlblnombre = new JLabel(rec.idioma[rec.eleidioma][2]);
		GridBagConstraints poslblnombre = new GridBagConstraints();
		poslblnombre.gridx = 0; // El ·rea de texto empieza en la columna 2.
		poslblnombre.gridy = 1; // El ·rea de texto empieza en la fila 0
		poslblnombre.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		poslblnombre.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		poslblnombre.anchor = GridBagConstraints.WEST;
		
		
		jtxtnombre = new JTextField();
		GridBagConstraints postxtnombre = new GridBagConstraints();
		postxtnombre.gridx = 1; // El ·rea de texto empieza en la columna 2.
		postxtnombre.gridy = 1; // El ·rea de texto empieza en la fila 0
		postxtnombre.gridwidth = 3;  // El ·rea de texto ocupa 2 columnas. 
		postxtnombre.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		postxtnombre.fill = GridBagConstraints.HORIZONTAL;
		
		
		JLabel jlblsector = new JLabel(rec.idioma[rec.eleidioma][3]);
		GridBagConstraints poslblsector = new GridBagConstraints();
		poslblsector.gridx = 0; // El ·rea de texto empieza en la columna 2.
		poslblsector.gridy = 2; // El ·rea de texto empieza en la fila 0
		poslblsector.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		poslblsector.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		poslblsector.anchor = GridBagConstraints.WEST;		
		
		JTextField jtxtsector = new JTextField();
		GridBagConstraints postxtsector = new GridBagConstraints();
		postxtsector.gridx = 1; // El ·rea de texto empieza en la columna 2.
		postxtsector.gridy = 2; // El ·rea de texto empieza en la fila 0
		postxtsector.gridwidth = 8;  // El ·rea de texto ocupa 2 columnas. 
		postxtsector.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		postxtsector.fill = GridBagConstraints.HORIZONTAL;
		
		
		JLabel jlbldireccion = new JLabel(rec.idioma[rec.eleidioma][6]);
		GridBagConstraints poslbldireccion = new GridBagConstraints();
		poslbldireccion.gridx = 0; // El ·rea de texto empieza en la columna 2.
		poslbldireccion.gridy = 3; // El ·rea de texto empieza en la fila 0
		poslbldireccion.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		poslbldireccion.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		poslbldireccion.anchor = GridBagConstraints.WEST;
		
		
		JTextField jtxtdireccion = new JTextField();
		GridBagConstraints postxtdireccion = new GridBagConstraints();
		postxtdireccion.gridx = 1; // El ·rea de texto empieza en la columna 2.
		postxtdireccion.gridy = 3; // El ·rea de texto empieza en la fila 0
		postxtdireccion.gridwidth = 3;  // El ·rea de texto ocupa 2 columnas. 
		postxtdireccion.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		postxtdireccion.fill = GridBagConstraints.HORIZONTAL;
		
		
		JLabel jlblcodpostal = new JLabel(rec.idioma[rec.eleidioma][7]);
		GridBagConstraints poslblcodpostal = new GridBagConstraints();
		poslblcodpostal.gridx = 0; // El ·rea de texto empieza en la columna 2.
		poslblcodpostal.gridy = 4; // El ·rea de texto empieza en la fila 0
		poslblcodpostal.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		poslblcodpostal.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		poslblcodpostal.anchor = GridBagConstraints.WEST;
		
		
		JTextField jtxtcodpostal = new JTextField();
		GridBagConstraints postxtcodpostal = new GridBagConstraints();
		postxtcodpostal.gridx = 1; // El ·rea de texto empieza en la columna 2.
		postxtcodpostal.gridy = 4; // El ·rea de texto empieza en la fila 0
		postxtcodpostal.gridwidth = 3;  // El ·rea de texto ocupa 2 columnas. 
		postxtcodpostal.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		postxtcodpostal.fill = GridBagConstraints.HORIZONTAL;
		
		
		JLabel jlbltelf = new JLabel(rec.idioma[rec.eleidioma][4]);
		GridBagConstraints poslbltelf = new GridBagConstraints();
		poslbltelf.gridx = 0; // El ·rea de texto empieza en la columna 2.
		poslbltelf.gridy = 5; // El ·rea de texto empieza en la fila 0
		poslbltelf.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		poslbltelf.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		poslbltelf.anchor = GridBagConstraints.WEST;
		
		
		JTextField jtxttelf = new JTextField();
		GridBagConstraints postxttelf = new GridBagConstraints();
		postxttelf.gridx = 1; // El ·rea de texto empieza en la columna 2.
		postxttelf.gridy = 5; // El ·rea de texto empieza en la fila 0
		postxttelf.gridwidth = 3;  // El ·rea de texto ocupa 2 columnas. 
		postxttelf.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		postxttelf.fill = GridBagConstraints.HORIZONTAL;
		
		
		jlblmail = new JLabel(rec.idioma[rec.eleidioma][8]);
		GridBagConstraints poslblmail = new GridBagConstraints();
		poslblmail.gridx = 0; // El ·rea de texto empieza en la columna 2.
		poslblmail.gridy = 6; // El ·rea de texto empieza en la fila 0
		poslblmail.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		poslblmail.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		poslblmail.anchor = GridBagConstraints.WEST;
		
		
		jtxtmail = new JTextField();
		GridBagConstraints postxtmail = new GridBagConstraints();
		postxtmail.gridx = 1; // El ·rea de texto empieza en la columna 2.
		postxtmail.gridy = 6; // El ·rea de texto empieza en la fila 0
		postxtmail.gridwidth = 3;  // El ·rea de texto ocupa 2 columnas. 
		postxtmail.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		postxtmail.fill = GridBagConstraints.HORIZONTAL;
		
		
		jbtnaceptar = new JButton(rec.idioma[rec.eleidioma][0]);
		GridBagConstraints posbtnaceptar = new GridBagConstraints();
		posbtnaceptar.gridx = 0; // El ·rea de texto empieza en la columna 2.
		posbtnaceptar.gridy = 8; // El ·rea de texto empieza en la fila 0
		posbtnaceptar.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		posbtnaceptar.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		posbtnaceptar.anchor = GridBagConstraints.WEST;
		
		
		jbtncancelar = new JButton(rec.idioma[rec.eleidioma][1]);
		GridBagConstraints posbtncancelar = new GridBagConstraints();
		posbtncancelar.gridx = 1; // El ·rea de texto empieza en la columna 2.
		posbtncancelar.gridy = 8; // El ·rea de texto empieza en la fila 0
		posbtncancelar.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		posbtncancelar.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		//postxtnombre.weightx = 1.0;
		posbtncancelar.anchor = GridBagConstraints.WEST;
		
		
		
		
		
		
		
		
		
		
		this.add(jlblnombre,poslblnombre);
		this.add(jtxtnombre,postxtnombre);
		this.add(jlblsector, poslblsector);
		this.add(jtxtsector, postxtsector);
		this.add(jlbldireccion, poslbldireccion);
		this.add(jtxtdireccion, postxtdireccion);
		this.add(jlblcodpostal, poslblcodpostal);
		this.add(jtxtcodpostal, postxtcodpostal);
		this.add(jlbltelf, poslbltelf);
		this.add(jtxttelf, postxttelf);
		this.add(jlblmail,poslblmail);
		this.add(jtxtmail,postxtmail);
		this.add(jbtnaceptar,posbtnaceptar);
		this.add(jbtncancelar,posbtncancelar);
		
		this.setOpaque(true);
	}
}
