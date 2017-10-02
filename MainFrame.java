
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pack;


import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import com.googlecode.javacv.cpp.opencv_highgui;
//import com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ritu
 */
public class MainFrame extends JFrame implements ActionListener{
    
    static JLabel l1,l2,ll1,ll2,ll3,ll4,l3;
    Button b1,b2,b3,b4,b5,b7;
    TextField t1,t2,tt1,tt2,tt3,tt4;
    JMenuItem m1,m2,m3,m4,m5,m6,m7,m8,m9;
    JMenu men; 
    JMenuBar mb;
    JPanel p;
    CanvasFrame canvas;
    RescaleOp rescale;
     JFileChooser jf=new JFileChooser();
     static BufferedImage image;
     static int height,width;
     int c1,w,c2,h,c3,c4;
     int drag_status=0;
     
    public MainFrame()
    {
        
        

setLayout(new BorderLayout());
setContentPane(new JLabel(new ImageIcon("C:\\pics\\fbg.jpg")));
setLayout(new FlowLayout());

b1=new Button("Upload image");
        b2=new Button("web cam image");
        b4=new Button("capture image");
        b7=new Button("SAVE");
        l1=new JLabel("Image Uploading");
        
    l2=new JLabel();
    l3=new JLabel();
   
    mb=new JMenuBar();
    
    ll1=new JLabel("x-axis");
    ll2=new JLabel("y-axis");
    ll3=new JLabel("height");
    ll4=new JLabel("width");
    tt1=new TextField();
    tt2=new TextField();
    tt3=new TextField();
    tt4=new TextField();
    
b1.setBounds(180, 45, 110, 40);
add(b1);
Font myFont = new Font("Sans Serif", Font.BOLD, 14);
b1.setFont(myFont);
b1.setForeground(Color.blue);
b1.setBackground(Color.WHITE);
//b1.setContentAreaFilled(false);
//b1.setOpaque(true);
b2.setBounds(560, 45, 115, 40);
add(b2);
b2.setFont(myFont);
b2.setForeground(Color.blue);
b2.setBackground(Color.WHITE);

b4.setBounds(960,45,105,40);
add(b4);
b4.setFont(myFont);
b4.setForeground(Color.blue);
b4.setBackground(Color.WHITE);

Font myFont2 = new Font("Sans Serif", Font.BOLD, 18);
b7.setBounds(1200,620,90,40);
add(b7);
b7.setFont(myFont2);
b7.setForeground(Color.BLUE);
b7.setBackground(Color.WHITE);

l2.setBounds(300,110 ,800, 600);
add(l2);

l3.setBounds(0,0 ,50, 30);

ll1.setBounds(50,100 ,40, 40);
add(ll1);
ll2.setBounds(50,130 ,50, 50);
add(ll2);
ll3.setBounds(50,160 ,50, 50);
add(ll3);
ll4.setBounds(50,190 ,50, 50);
add(ll4);
tt1.setBounds(100,110,35,20);
add(tt1);
tt2.setBounds(100,140,35,20);
add(tt2);
tt3.setBounds(100,170,35,20);
add(tt3);
tt4.setBounds(100,200,35,20);
add(tt4);
ll1.setVisible(false);
ll2.setVisible(false);
ll3.setVisible(false);
ll4.setVisible(false);
tt1.setVisible(false);
tt2.setVisible(false);
tt3.setVisible(false);
tt4.setVisible(false);
 mb.setBounds(0,0, 1500, 20);
//add(mb,BorderLayout.NORTH);
add(mb);
 
men=new JMenu("edit");

mb.add(men);

m1=new JMenuItem("crop");

m3=new JMenuItem("sharpen");
m4=new JMenuItem("grayscale");
m5=new JMenuItem("brightness");
m6=new JMenuItem("contrast");
m7=new JMenuItem("black and white");
m8=new JMenuItem("rotate");
m9=new JMenuItem("text-on-img");
//men.setBounds(0, 0, 400, 20);

men.add(m1);

men.add(m3);
men.add(m4);

men.add(m5);
men.add(m6);
men.add(m7);
men.add(m8);
men.add(m9);

b1.addActionListener(this);
b2.addActionListener(this);
//b3.addActionListener(this);
b4.addActionListener(this);
m4.addActionListener(this);
b7.addActionListener(this);
m3.addActionListener(this);
m5.addActionListener(this);
m6.addActionListener(this);
m1.addActionListener(this);
m8.addActionListener(this);
m9.addActionListener(this);
m1.addActionListener(this);
m7.addActionListener(this);

setExtendedState(MAXIMIZED_BOTH);
setLayout(null);
setVisible(true);
    }

         
     public int colorToRGB(int alpha, int red, int green, int blue) {
            int newPixel = 0;
            newPixel += alpha;
            newPixel = newPixel << 8;
            newPixel += red; newPixel = newPixel << 8;
            newPixel += green; newPixel = newPixel << 8;
            newPixel += blue;

            return newPixel;
        }




    public BufferedImage buffer()
    {
        try {
            image= ImageIO.read(jf.getSelectedFile());
            /*int factor=1;
                  if(image.getWidth()>=2000)
                      factor=5;
                  else if(image.getWidth()>=1500)
                      factor=4;
                  else if(image.getWidth()>=1000)
                      factor=3;
                  else 
                      factor=1;
                  
                  Image img=image.getScaledInstance(image.getWidth()/factor,image.getHeight()/factor, Image.SCALE_SMOOTH);
                  ImageIcon ic=new ImageIcon(img);
                  l2.setSize(image.getWidth()/factor, image.getHeight()/factor);
                  l2.setIcon(ic);
                  image=(BufferedImage)l2.getIcon();
                  
File  f1=new File("capture.jpg");
ImageIO.write(image,"jpg",f1);*/
                  
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return image;
    }
    int factor=1;
    public void setImage(BufferedImage img)
    {
         if(img.getHeight()>=3000)
             factor=6;
                 else if(img.getWidth()>=2000||img.getHeight()>=2000)
                      factor=5;
                  else if(img.getWidth()>=1500||img.getHeight()>=2000)
                      factor=4;
                  else if(img.getWidth()>=1000||img.getHeight()>=2000)
                      factor=3;
                  else 
                      factor=1;
                  
                  Image img2=img.getScaledInstance(img.getWidth()/factor,img.getHeight()/factor, Image.SCALE_SMOOTH);
                  ImageIcon ic=new ImageIcon(img2);
                  l2.setBounds(310,110,img.getWidth()/factor, img.getHeight()/factor);
                  l2.setIcon(ic);
                  
                  File obj=new File("capture.jpg");
                  
             try {
                 ImageIO.write(img, "jpg", obj);
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
   

    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==b1)
        {
           
            if(jf.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
            {
              try{
                 image=buffer();
              
                 setImage(image);
              } catch(Exception exc)
              {
                  
              }
            }
        }    
         
         if(e.getSource()==m3)
         {
             File file = new File("capture.jpg");
             try {
                 ImageIO.write(image, "jpg", file);
                 
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
          //   String i = JOptionPane.showInputDialog(this, "Please enter sharpen value between 9 to 12");
           //  float [] sharpen = {-1,-1,-1,-1,Float.parseFloat(i),-1,-1,-1,-1};
                float [] sharpen = {  0.0f, -1.0f, 0.0f,
    -1.0f, 4.5f, -1.0f,
     0.0f, -1.0f, 0.0f};
             BufferedImageOp bop =new ConvolveOp(new Kernel(3,3,sharpen), ConvolveOp.EDGE_NO_OP, null);
             image=bop.filter(image, null);
             setImage(image);
         }
         
         if(e.getSource()==m8)
         { 
        setImage(image);
             int width2 = image.getWidth();
	int height2 = image.getHeight();
        //l2.setSize(width2, height2);
	BufferedImage returnImage = new BufferedImage( height2, width2 , image.getType()  );

	for( int x = 0; x < width2; x++ ) {
		for( int y = 0; y < height2; y++ ) {
                    int z=height2-y-1;
			returnImage.setRGB(z, x, image.getRGB( x, y  )  );
		}
		}
        image=returnImage;
         setImage(image);
        File f2=new File("capture.jpg");
             try {
                 ImageIO.write(image, "jpg", f2);
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
       }   
         if(e.getSource()==m7)
         {
             BufferedImage binarized = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
             int red;
             int newPixel;
             int threshold =130;
             for(int i=0; i<image.getWidth(); i++)
             {
                 for(int j=0; j<image.getHeight(); j++)
                 {
                     
                     // Get pixels
                     red = new Color(image.getRGB(i, j)).getRed();
                     
                     int alpha = new Color(image.getRGB(i, j)).getAlpha();
                     
                     if(red > threshold)
                     {
                         newPixel = 0;
                     }
                     else
                     {
                         newPixel = 255;
                     }
                     newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
                     binarized.setRGB(i, j, newPixel);
                     
                 }
             }
             setImage(binarized);   
         }
         
         if(e.getSource()==m9)
         {
               
         File f3=new File("capture.jpg");
             try {
                 ImageIO.write(image,"jpg",f3);
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
             Graphics g = image.createGraphics();
                g.setFont(g.getFont().deriveFont(30f));
                g.drawString("Hello World!", 100, 100);
                g.dispose();
                Image img4=image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                ImageIcon ic3=new ImageIcon(img4);
                l2.setIcon(ic3);
          File f4=new File("capture.jpg");
             try {
                 ImageIO.write(image,"jpg",f4);
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }
if(e.getSource()==m1)
         {
            CropImage cp = new CropImage(factor);
            this.dispose();
             System.out.println(factor);
            cp.setVisible(true);
         
         }    
         if(e.getSource()==b2)
         {
          Thread th=new Thread()
          {
               
              public void run()
         {
             canvas = new CanvasFrame("web cam");
             canvas.setExtendedState(Frame.MAXIMIZED_BOTH);
             opencv_highgui.CvCapture capture= opencv_highgui.cvCreateCameraCapture(0);
             opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT,800 );
             opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 800);
             opencv_core.IplImage iplimage = opencv_highgui.cvQueryFrame(capture);
             
             while(canvas.isVisible()  && (iplimage=opencv_highgui.cvQueryFrame(capture))!=null)
             {
                 canvas.showImage(iplimage);
                 //canvas.setExtendedState(Frame.MAXIMIZED_BOTH);
             }
         
         }
          };
            th.start();    
         }
         if(e.getSource()==b4)
         {
             OpenCVFrameGrabber grabber= new OpenCVFrameGrabber(0);
             try
             {
                 grabber.start();
                 opencv_core.IplImage image1 = grabber.grab();
                 if(image1!=null)
                 {
                    opencv_highgui.cvSaveImage("capture.jpg", image1);
                    canvas.dispose();
                    image = image1.getBufferedImage();
                    Image img2 = (Image)image;
                 setImage(image);
                
                 }
                 
             }catch(Exception excp)
             {
                 
             }
         }
         
         if(e.getSource()==m4)
         {
             
                   try         
           {
               File file = new File("capture.jpg");
             try {
                 ImageIO.write(image, "jpg", file);
                 
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
               height=image.getHeight();
               width=image.getWidth();
               for(int i=0;i<height;i++)
               {
                   for(int j=0;j<width;j++)
                   {
                       Color color=new Color(image.getRGB(j, i));
                       int red=(int)(color.getRed()*0.299);
                       int green=(int)(color.getGreen()*0.255);
                       int blue=(int)(color.getBlue()*0.144);
                       Color col=new Color(red+blue+green,red+blue+green,red+blue+green);
                       image.setRGB(j, i, col.getRGB());
                   }
               }
                 setImage(image);
              
           }catch(Exception excpt)
           {
               JOptionPane.showMessageDialog(this, excpt.getMessage());
           }
             
         }
         
         if(e.getSource()==m5)
         {
         //   b6.setVisible(true);
         
         
           //String str=tt1.getText();
             String str = JOptionPane.showInputDialog(this, "Please enter an integral value from 0-150");
             int off=Integer.parseInt(str);
             File file = new File("capture.jpg");
             try {
                 ImageIO.write(image, "jpg", file);
                 
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
             rescale=new RescaleOp(1.0f, off, null);
             
             image=rescale.filter(image,image);//dest null or not?
             setImage(image);
            
         }
         if(e.getSource()==b7)
                 {
                     try{
                         
             if(l2.getIcon()==null)
             {
                 JOptionPane.showInputDialog("Sorry");
             }
             else
             {
                 JFileChooser chooser = new JFileChooser("Save Image ");
                 JFileChooser savechooser=new JFileChooser("save image");
                  //savechooser.setMultiSelectionEnabled(true);
                 savechooser.setFileFilter(new FileNameExtensionFilter("JPEG File","jpg"));
                 savechooser.setFileFilter(new FileNameExtensionFilter("PNG File", "png"));
                 savechooser.setFileFilter(new FileNameExtensionFilter("GIF File","gif"));
                 if(savechooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)
                 {
                     
                     String name=savechooser.getSelectedFile().getAbsolutePath();
                     String name1=savechooser.getFileFilter().getDescription();
                     ImageIcon icon2=(ImageIcon)l2.getIcon();
                     Image img = icon2.getImage();
                     if (name1.equals("JPEG File")){
                         String ext = ".jpg";
                         name = name + ext;
                         System.out.println(name);
                     }
                     else if(name1.equals("PNG File")){
                         String ext = ".png";
                         name = name + ext;
                         System.out.println(name);
                     }
                     else if(name1.equals("GIF File")){
                         String ext = ".gif";
                         name = name + ext;
                         System.out.println(name);
                     }
                     else if(name1.equals("All Files")){
                         
                         System.out.println(name);
                     }
                     else{
                         JOptionPane.showMessageDialog(this, "Error in saving file", "ERROR", JOptionPane.ERROR_MESSAGE);
                     }
                     try {
                         File file=new File(name);
                         BufferedImage bf=ImageIO.read(new File("capture.jpg"));
                         if (name1.equals("JPEG File")){
                            Graphics g = bf.getGraphics();
                            g.drawImage(img, 0, 0, 0, 0, chooser);
                            g.dispose();
                         
                             ImageIO.write(bf,"jpeg", file);
                             
                         }
                         if (name1.equals("PNG File")){
                            Graphics g = bf.getGraphics();
                         g.drawImage(img, 0, 0, 0, 0, chooser);
                         g.dispose();
                        
                             ImageIO.write(bf,"png", file);
                             
                         }
                         if(name1.equals("GIF File"))
                         {
                             Graphics g = bf.getGraphics();
                         g.drawImage(img, 0, 0, 0, 0, chooser);
                         g.dispose();
                        
                             ImageIO.write(bf, "gif", file);
                         }
                         
                     }catch (IOException ex) {
                         System.out.print(ex.getMessage());
                     }
                 }
             }
 }catch(Exception ee){}
                     
                 }
                 if(e.getSource()==m6)
                 {
                   
                     
                     String str2=JOptionPane.showInputDialog(this,"enter value between 0-2");
                     float scale=Float.parseFloat(str2);
                     File file = new File("capture.jpg");
             try {
                 ImageIO.write(image, "jpg", file);
                 
             } catch (IOException ex) {
                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
             }
                     rescale=new RescaleOp(scale, 1.0f, null);
                     image=rescale.filter(image, image);
                     
                    setImage(image);
                     
                 }

             }
    public void acceptImage(BufferedImage imgg)
    {
        
      //  Image img = imgg.getScaledInstance(imgg.getWidth()/5, imgg.getHeight()/5, Image.SCALE_SMOOTH);
        image=imgg;
        setImage(imgg);
        
    }
    public static void main(String []ar)
    {
        
         MainFrame mainFrame = new MainFrame();
             
       }
}