package pkGesproject;

public class DlgModal {
	private int anchojtoolbar;
	
	public DlgModal(){
		if (RsGesproject.SistemaOp.equals("Mac OS X")){ //En MacOs no tenemos en cuenta el tamaño de la barra de titulo
			this.setAnchojtoolbar(-1);
		}
		
	}
	
	
	
	
	

	public void setAnchojtoolbar(int anchojtoolbar) {
		this.anchojtoolbar = anchojtoolbar;
	}

	public int getAnchojtoolbar() {
		return anchojtoolbar;
	}

}
