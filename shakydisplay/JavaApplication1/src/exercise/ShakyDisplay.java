/**
 * CICI1130 Assignment 1 Shaky Display
 * Aim: Get acquainted with the JDK + NetBeans programming environment
 *      Learn the structure and format of a Java program by example
 * 
 * I declare that the assignment here submitted is original
 * except for the source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and 
 * regulations on honesty in academic work, and of the disciplinary 
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 * 
 * University Guideline on Academic Honesty:
 *   http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *   https://www.erg.cuhk.edu.hk/erg/Academichonesty
 * 
 * Student Name: Li Tsz Kin
 * Student ID  : 1155158177 
 * Date        : 22/9/2021
 */

package exercise;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class ShakyDisplay extends JFrame implements ActionListener{
    // instance fields
    protected int width, height;
    protected JButton buttons[][];
    
    //default constructor
    public ShakyDisplay()
    {
        width = 20;
        height = 10;
        initDisplay();
    }
    
    //constructor with given width and height of the ShakdyDisplay object
    public ShakyDisplay(int w, int h)
    {
        width = w;
        height = h;
        initDisplay();
    }
    
    //intialize the ShakyDisplay window
    public final void initDisplay(){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException|
                 IllegalAccessException|
                 InstantiationException|
                 UnsupportedLookAndFeelException exceptionObject){
        }
        
        setTitle("Java Shaky Display");
        setLayout(new GridLayout(height, width));
        buttons = new JButton[height][width];
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
            {
                buttons[row][col] = new JButton(row + ", " +col);
                buttons[row][col].setMargin(new Insets(1, 1, 1, 1));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
                if (row == height - 1)
                    buttons[row][col].setForeground(Color.RED);
            }
        setSize(width * 45, height * 45);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //change button text color on user clicks
    @Override
    public void actionPerformed(ActionEvent eventObject)
    {
        JButton target = (JButton) (eventObject.getSource());
        if (target.getForeground() == Color.GREEN)
            target.setForeground(Color.BLUE);
        else if (target.getForeground() == Color.BLUE)
        {
            target.setForeground(null);
            shake();
        }
        else
            target.setForeground(Color.GREEN);
    }
    
    //slow down this process by sleeping this thread
    private void delay(long sleepInMs){
        try{
            TimeUnit.MILLISECONDS.sleep(sleepInMs);
        } catch (InterruptedException exceptionObject){
            Thread.currentThread().interrupt();
        }
    }
    
    //shake the ShakyDisplay
    private void shake()
    {
        Point windowLocation = getLocation();
        
        double round = 5, max_radius = 10, step = 100;
        
        double limit = 2 * Math.PI * round;
        double angle_increment = limit/step;
        double radius_increment = max_radius/step;
        
        for(double angle = 0, radius = 0;
            angle < limit;
            angle += angle_increment, radius += radius_increment)
        {
            setLocation((int)(Math.cos(angle) * radius) + windowLocation.x,
                        (int)(Math.sin(angle) * radius) + windowLocation.y);
            this.delay(6);
        }
        this.setLocation(windowLocation);
    }
    
    /// *** TO DO: Students should customize this method ***
    /// - to show the last FIVE digits of your SID in YELLOW in BIG PIXELS
    /// - AND to show your SURNAME char-by-char as button text on the bottom
    public void showmyinfo()
    {
        // example digit: 7 in YELLOW in BIG PIXELS
        
        for(int i=1; i<5;i++){
            //5
            buttons[1][i].setBackground(Color.YELLOW);
            buttons[4][i].setBackground(Color.YELLOW);
            buttons[7][i].setBackground(Color.YELLOW);
            buttons[i][1].setBackground(Color.YELLOW);
            buttons[3+i][4].setBackground(Color.YELLOW);
            //8
            buttons[i][6].setBackground(Color.YELLOW);
            buttons[i][9].setBackground(Color.YELLOW);
            buttons[3+i][6].setBackground(Color.YELLOW);
            buttons[3+i][9].setBackground(Color.YELLOW);
            buttons[1][5+i].setBackground(Color.YELLOW);
            buttons[4][5+i].setBackground(Color.YELLOW);
            buttons[7][5+i].setBackground(Color.YELLOW);
            //fat 1
            buttons[i][12].setBackground(Color.YELLOW);
            buttons[i][13].setBackground(Color.YELLOW);
            buttons[3+i][12].setBackground(Color.YELLOW);
            buttons[3+i][13].setBackground(Color.YELLOW);
            //7
            buttons[1][15+i].setBackground(Color.YELLOW);
            buttons[i][19].setBackground(Color.YELLOW);
            buttons[3+i][19].setBackground(Color.YELLOW);
            //sec 7
            buttons[1][20+i].setBackground(Color.YELLOW);
            buttons[i][24].setBackground(Color.YELLOW);
            buttons[3+i][24].setBackground(Color.YELLOW);
        }
        
        // example name : N A M E
        int c = 0;
        buttons[height-1][c++].setText("L");
        buttons[height-1][c++].setText("I");
    }
    /**
     * main() method, starting point of the Java application
     * @param args are command line arguments in a String array
     */
    public static void main(String[] args) {
        ShakyDisplay dpy;
        // may change this lline to create a ShakyDisplay of different size
        dpy = new ShakyDisplay(30, 10);
        dpy.showmyinfo();
    }
}