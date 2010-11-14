package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class DlgSiNo extends Thread  {
	JPanel dlg = new JPanel();
	JLabel msg = new JLabel();
	JButton si = new JButton("Si");
	JButton no = new JButton("No");
	ImageIcon ic = RsGesproject.Obtener_Instancia().icono[1];
	JLabel icl = new JLabel(ic);
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	FrmPrincipal frmppal = recursos.getRfrmppal();
	GridBagConstraints cons = new GridBagConstraints();
	public DlgSiNo(String msg){
		dlg.setBackground(new Color(0xED,0xED,0xED,240));
		dlg.setVisible(false);
		dlg.setOpaque(true);
		this.msg.setText(msg); 
		dlg.setLayout(new GridBagLayout());
		
		dlg.setSize(new Dimension(380, 180));
		
		
		
		
	}
	
		
	public void setConst(){
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth=1;
		cons.gridheight=1;
		dlg.add(icl, cons);
		
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth=3;
		cons.gridheight=1;
		cons.weighty = 1.0;
		dlg.add(this.msg, cons);
		
		cons.weighty = 0.0;
		cons.gridx = 2;
		cons.gridy = 1;
		cons.gridwidth=1;
		cons.gridheight=1;
		dlg.add(si, cons);
		
		cons.gridx = 3;
		cons.gridy = 1;
		cons.gridwidth=1;
		cons.gridheight=1;
		dlg.add(no, cons);
	}
	public void run() {
		int x=dlg.getWidth(),y=dlg.getHeight(),i=0,j=0;
		
		JPanel glass = (JPanel) frmppal.getGlassPane();
		JPanel jpcontenedor = new JPanel();
		glass.setLayout(null);
		jpcontenedor.setLocation(((frmppal.getSize().width/2)-(x/2)),frmppal.jtlbFrmppal.getSize().height+23);
		dlg.setSize(0,0);
		//jpcontenedor.setSize(0,0);
		jpcontenedor.setBorder(new DropShadowBorder(UIManager.getColor("Control"), 1, 5, .5f, 12, false, true, true, true));
		jpcontenedor.setLayout(new BorderLayout());
		jpcontenedor.add(dlg,BorderLayout.CENTER);
		glass.add(jpcontenedor);
		glass.setVisible(true);
		jpcontenedor.setVisible(true);
		dlg.setVisible(true);
		System.out.println(Integer.toString(x) + "," + Integer.toString(y));
		
		//this.setConst();
		for (i=0;i<=y;i++){
	    	dlg.setSize(x, i);	
	    	jpcontenedor.setSize(x+12,i+7);
	    	glass.repaint();
	    	dlg.repaint();
	    	jpcontenedor.repaint();
	    	
	    	for (j=0;j<dlg.getComponentCount();j++){
	    		dlg.getComponent(j).repaint();
	    		this.setConst();
	    	}
	    	try {
	    		Thread.sleep(1);
	    		} catch (InterruptedException ex) {
	    		// aqu� tratamos la excepci�n como queramos, haciendo nada, sacando por pantalla el error, ...
	    		}
	   if (i==y){
		   dlg.setPreferredSize(new Dimension(x,y));
		   jpcontenedor.setPreferredSize(new Dimension(x+12,y+9));
		   this.setConst();
		   dlg.repaint();
			System.out.println(Integer.toString(x) + "," + Integer.toString(y));

		   
	   }
	}
		
	}
		
	
}
	


