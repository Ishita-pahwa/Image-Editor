/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pack;

/**
 *
 * @author Dell User
 */

import static Pack.MainFrame.l2;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CropImage extends JFrame implements MouseListener, MouseMotionListener
{
	int drag_status=0,c1,c2,c3,c4;
        static File obj=new File("capture.jpg");
        int factor=1;
    public CropImage(int f)  {
        factor=f;
        start(obj);
                
    }
public static void main(String args[]) throws IOException
{
    //File obj=new File("capture.jpg");
	//new CropImage().start(obj);
}
public void start(File f)
{
    try{
    BufferedImage img = ImageIO.read(f);
      Image img2=img.getScaledInstance(img.getWidth()/factor,img.getHeight()/factor, Image.SCALE_SMOOTH);
                  //ImageIcon ic=new ImageIcon(img2);
        //setLayout(null);
       JLabel l2 = new JLabel(new ImageIcon(img2));
           //l2.setBounds(0,0,img.getWidth()/factor, img.getHeight()/factor);
                  
    	add(l2);
        
	setSize(500, 500);
	setVisible(true);
	addMouseListener(this);
	addMouseMotionListener( this ); 
	//setDefaultCloseOperation(EXIT_ON_CLOSE);
    }catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
}
public void draggedScreen()throws Exception
{
	int w = c1 - c3;
	int h = c2 - c4;
	w = (w * -1)-7;
	h = (h * -1)-7;
	Robot robot = new Robot();
	BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2,w,h));
	File save_path=new File("capture.jpg");
	ImageIO.write(img, "JPG", save_path);
	System.out.println("Cropped image saved successfully.");
        this.dispose();
        this.setVisible(false);
      //  this.
        MainFrame mf = new MainFrame();
        mf.acceptImage(img);
        mf.setVisible(true);
        
        
        
}
@Override
public void mouseClicked(MouseEvent arg0) {		
}

@Override
public void mouseEntered(MouseEvent arg0) {		
}

@Override
public void mouseExited(MouseEvent arg0) {		
}

@Override
public void mousePressed(MouseEvent arg0) {
	repaint();
	c1=arg0.getX();
	c2=arg0.getY();
}

@Override
public void mouseReleased(MouseEvent arg0) {
	repaint();
	if(drag_status==1)
	{
	c3=arg0.getX();
	c4=arg0.getY();
	try
	{
	draggedScreen();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}

@Override
public void mouseDragged(MouseEvent arg0) {
	repaint();
	drag_status=1;
	c3=arg0.getX();
	c4=arg0.getY();
}

@Override
public void mouseMoved(MouseEvent arg0) {
	
}

public void paint(Graphics g)
{
	super.paint(g);
	int w = c1 - c3;
	int h = c2 - c4;
	w = w * -1;
	h = h * -1;
	if(w<0 && h<0)
		w = w * -1;
    g.drawRect(c1, c2, w, h);	
}
}

