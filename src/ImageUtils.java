// ImageUtils.java
// Andrew Davison, August 2012, ad@fivedots.coe.psu.ac.th

/* A collection of useful image manipulation methods.
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageUtils
{
  private final static String IMAGES_DIR = "\\out\\production\\yaddayaddayadda\\images\\";


  private ImageUtils()
  {}


  public static BufferedImage loadImage(String imFnm)
  // load the image stored in imFnm from the IMAGES_DIR directory
  {
    System.out.println(System.getProperty("user.dir"));
    String fnm = System.getProperty("user.dir")+IMAGES_DIR + imFnm;
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(fnm));
      // System.out.println("Loaded " + fnm);
    }
    catch (IOException e) 
    {  System.out.println("Unable to load " + fnm);  }
    return image;
  }  // end of loadImage()



  public static BufferedImage padBorders(BufferedImage im)
  /* Pad the borders of an image with transparent pixels, so that
     it becomes diagonal*diagonal large. This means that the image 
     can always be rotated around its center without losing any
     visible pixels because of cropping.
  */
  {
    int w = im.getWidth();
    int h = im.getHeight();
    int diagonal = (int)Math.round(Math.sqrt(w*w + h*h)) + 2;
             /* the "+2" is a bit of extra space to compensate for the
                integer rounding */

    // larger image with transparent background
    BufferedImage result = new BufferedImage(diagonal, diagonal, 
                                                   BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = result.createGraphics();

    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                         RenderingHints.VALUE_INTERPOLATION_BICUBIC);   // best quality
    
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                         RenderingHints.VALUE_RENDER_QUALITY);
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    
    // center original image inside larger one
    int x = diagonal/2 - w/2;
    int y = diagonal/2 - h/2;
    g2d.drawImage(im, x, y, w, h, null);
    g2d.dispose();

    return result;
  }  // end of padBorders()



  public static BufferedImage scale(BufferedImage im, double scale)
  // return a scaled version of the image
  {
    if(im == null)
      return null;

    if (scale == 1)  // no change
      return im;

    int newWidth = (int) Math.round(im.getWidth()*scale);
    int newHeight = (int) Math.round(im.getHeight()*scale);

    BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = result.createGraphics();

    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                         RenderingHints.VALUE_INTERPOLATION_BICUBIC);   // best quality
    
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                         RenderingHints.VALUE_RENDER_QUALITY);
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    
    g2d.drawImage(im, 0, 0, newWidth, newHeight, null);
    g2d.dispose();

    return result;
  }  // end of scale()

  

  public static BufferedImage rotate(BufferedImage im, double angle)
  /* returns a rotated version of the image; angle is in radians.
     The rotation is around the center of the image. */
  {
    if(im == null)
      return null;

    int degAngle = (int)Math.round(Math.toDegrees(angle));
    if (degAngle == 0)    // no change
      return im;    

    int w = im.getWidth();
    int h = im.getHeight();
    // create a BufferedImage on which to draw rotated image
    BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    
    // rotate image about center
    AffineTransform at = new AffineTransform();
    at.rotate(angle, w/2.0, h/2.0);

    Graphics2D g2d = (Graphics2D) result.getGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                         RenderingHints.VALUE_INTERPOLATION_BICUBIC);   // best quality
    
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                         RenderingHints.VALUE_RENDER_QUALITY);
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);

    // draw translated, rotated image on the result
    g2d.drawImage(im, at, null);
    g2d.dispose();

    return result;
  }  // end of rotate()



  public static boolean onVisiblePixel(BufferedImage im, int x, int y)
  /* is (x,y) a *visible* point inside the image? Since an image can have
     a large transparent border, the alpha value of the (x,y) pixel must
     be checked.
  */
  {
    if ((x < 0) || (y < 0) || (x >= im.getWidth()) || (y >= im.getHeight()))
      return false;     // since (x,y) not in range

    if (!im.getColorModel().hasAlpha())    // no alpha pixels
      return true;

    int alpha = (im.getRGB(x,y) >> 24) & 0xFF;
    return (alpha != 0);    // 0 == completely transparent
  }  // end of onVisiblePixel();



}  // end of ImageUtils class