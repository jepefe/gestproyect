package pkGesproject;

import java.awt.*;

import javax.swing.*;

public class PnlBienvenida extends JPanel{
	JLabel jlblbienvenida, titulo;
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma nidioma = new GesIdioma();
	PnlBienvenida(){
		
		this.setLayout(new GridBagLayout());
		
		
		jlblbienvenida = new JLabel(nidioma.idioma[recursos.eleidioma][5]);
		GridBagConstraints poslblbienvenida = new GridBagConstraints();
		titulo = new JLabel("Titulo");
		
		Font auxFont = titulo.getFont();
		jlblbienvenida.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),50));
		
		this.add(jlblbienvenida);
		
		this.setOpaque(true);
	}
}
