package pkGesproject;

import java.awt.*;

import javax.swing.*;

public class PnlBienvenida extends JPanel{
	JLabel jlblbienvenida, titulo;
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	public PnlBienvenida(){
		
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0xED,0xED,0xED,0xED));
		jlblbienvenida = new JLabel(rec.idioma[rec.eleidioma][6]);
		GridBagConstraints poslblbienvenida = new GridBagConstraints();
		titulo = new JLabel("Titulo");
		
		Font auxFont = titulo.getFont();
		jlblbienvenida.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),50));
		
		this.add(jlblbienvenida);
		
		this.setOpaque(true);
	}
}
