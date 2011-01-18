package pkGesproject;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class JImageContainer extends JPanel //or JPanel, or whatever
{
  private Image myImage;
  
  public void SetImage(String imagen){
	  this.myImage = new ImageIcon(imagen).getImage();
  }

  public void paint(Graphics g)
  {
    //Paint the container.  Optional if you don't have any borders or
    //anything specific to the container.  This should be avoided if
    //possible because it will draw each component contained by the
    //class, and we take care of that later in this function.
    if(myImage == null)
	{
		//myImage = new ImageIcon(CRM.pathImg+"negro.jpg").getImage();
	}
    super.paint(g);
	
    //Clear the space to be filled by the image.
    g.clearRect(getInsets().left, getInsets().top,getWidth(), getHeight());
    if(myImage.getHeight(null) < getHeight() || myImage.getWidth(null) < getWidth())
    {
      //This tiles the image if it's smaller than the area
      int x = getInsets().left;
      int y = getInsets().top;
      for(y = getInsets().top; y < getHeight();y+= myImage.getHeight(null))
      {
        for(x = getInsets().left; x < getWidth();x+= myImage.getWidth(null))
        {
          g.drawImage(myImage, x, y, this);
        }
      }
    }
    else
    {
      //This scales the image to the size of the container
      g.drawImage(myImage, getInsets().left, getInsets().top, getWidth(), getHeight(), this);
    }
    //Then paint each contained item in order
    Component myComponents[] = getComponents();
    int i;
    for(i=0; i< myComponents.length; i++)
    {
      myComponents[i].paint(g);
    }
  }
	
	public void setImage(Image myImage)
	{
		this.myImage = myImage; 
	}

	public Image getImage()
	{
		return (this.myImage); 
	}
}