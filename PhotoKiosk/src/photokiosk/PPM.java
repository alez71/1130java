package photokiosk;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Portable Pixel Map (RGB Color image file in ASCII text) Java application
 * employs 2D array and file I/O
 *
 * @author Michael FUNG, YPChui
 *
 * I declare that the assignment here submitted is original except for source
 * material explicitly acknowledged, and that the same or closely related
 * material has not been previously submitted for another course. I also
 * acknowledge that I am aware of University policy and regulations on honesty
 * in academic work, and of the disciplinary guidelines and procedures
 * applicable to breaches of such policy and regulations, as contained in the
 * website.
 *
 * University Guideline on Academic Honesty:
 *   http://www.cuhk.edu.hk/policy/academichonesty/
 *
 * Student Name : xxx [fill in yourself]
 * Student ID   : xxx [fill in yourself]
 * Class/Section: xxx [fill in yourself]
 * Date         : xxx [fill in yourself]
 */
public class PPM {
  // instance fields
  private String imageName;
  private int width, height;
  private int maxValue;
  private Color[][] image;
  private Color[][] image_old;
  // Default constructor for creating an blank PPM image of 2 x 3
  // provided for reference, NEED NOT be modified
  public PPM() {
    imageName = "Simple";
    width = 2;
    height = 3;
    maxValue = 255;
    image = new Color[height][width];
    image[0][0] = new Color(0, 128, 255);

    int r = image[0][0].getRed();
    int g = image[0][0].getGreen();
    int b = image[0][0].getBlue();

    image[0][1] = new Color(r, g + 127, b - 128);
    image[1][0] = new Color(128, g, 128);
    image[1][1] = new Color(255, 0, 255);
    image[2][0] = new Color(255, 255, 255);
    image[2][1] = new Color(0, 0, 0);
  }

  // Constructor for creating an "orange" PPM image of width x height
  // All pixels shall carry Color(255, 165, 0) in RGB
  public PPM(String name, int w, int h) {
    // fill in code here  
    imageName = name;
    width = w;
    height = h;
    for(int i=0;i<height;i++){
        for(int j=0;j<width;j++){
            image[i][j] = new Color(255,165,0);
        }
    }
    
    
  }

  // Constructor for reading a PPM image file
  public PPM(String filename) {
    this.imageName = filename;
    read(filename);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Color[][] getImage() {
    return image;
  }

  // Show image on screen
  // given and NEED NOT be modified
  public void showImage() {
    if (getHeight() <= 0 || getWidth() <= 0 || image == null) {
      JOptionPane.showConfirmDialog(null, "Width x Height = " + getWidth() + "x" + getHeight(), imageName + " corrupted!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
      return;
    }

    BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        img.setRGB(col, row, image[row][col].getRGB());
      }
    }

    JOptionPane.showConfirmDialog(null, "Width x Height = " + getWidth() + "x" + getHeight(), imageName, JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img));
  }

  /* student's work here to define extra methods to handle color space conversion, 
     grayscale and saturate */
 
  
  public PPM grayscale() {
 
      
      
  }
  
  public PPM saturate() {
 
      
      
  }

  public boolean read(String filename) {
    try {
      File f = new File(filename);
      Scanner reader = new Scanner(f);
      String header = reader.nextLine();
      if (header == null || header.length() < 2 || header.charAt(0) != 'P' || header.charAt(1) != '3') {
        throw new Exception("Wrong PPM header!");
      }
      
      do { // skip lines (if any) start with '#'
        header = reader.nextLine();
      } while (header.charAt(0) == '#');

      Scanner readStr = new Scanner(header);  // get w, h from last line scanned instead of file
      width = readStr.nextInt();
      height = readStr.nextInt();
//      width = reader.nextInt();
//      height = reader.nextInt();
      maxValue = reader.nextInt();  // get the rest from file      

      System.out.println("Reading PPM image of size " + width + "x" + height);
      // Write your code here
        int[] rgb = {0,0,0};
        for(int h=0;h<height;h++){
            for(int w=0;w<width;w++){
                for(int i=0;i<3;i++){
                    rgb[i] = reader.nextInt();
                    //The following check is added because I don't know if all the test files contain valid data
                    if(rgb[i]>maxValue){
                        throw new Exception("RGB value exceeded maximum valued allowed");
                    }
                }
                image_old[h][w] = image[h][w] = new Color(rgb[0],rgb[1],rgb[2]);
            }
        }
      
      
      
      
      
    } catch (Exception e) {
      System.err.println(e);
      image = null;
      width = -1;
      height = -1;
      return false;
    }
    return true;
  }

  public void write(String filename) {
    PrintStream ps;
    try {
      ps = new PrintStream(filename);
      // fill in code here      
      ps.println("P3");
      ps.print(width+"\n"+height+"\n");
      for(int i=0;i<height;i++){
          for(int j=0;j<width;){
              ps.print(image[i][j].getRed()+" ");
              ps.print(image[i][++j].getGreen()+" ");
              ps.print(image[i][++j].getBlue()+" ");
          }
          ps.printf("\n");
      }
      
    } catch (Exception e) {
      System.err.println(e);
    }
  }

}