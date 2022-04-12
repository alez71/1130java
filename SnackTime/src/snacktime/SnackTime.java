package snacktime;

import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*; 


/**
 *
 * @author alexli
 */


/**
 * Snack Class
 * includes its name and price
 */
class Snack{
    String name;
    double price;
    
    public Snack(String namein){
        name = namein;
        price = genRandomPrice();
        printMessage();
    }
    
    private double genRandomPrice(){
        double price = Math.round(Math.random()*20);
        price = price * 0.5 + 5.0;
        return price;
    }
    
    public String getName(){
        //System.out.println(name);
        return name;
    }
    
    public double getPrice(){
        //System.out.println(price);
        return price;
    }
    
    private void printMessage(){
        System.out.println(name+" is sold at $"+price);
    }
}


public class SnackTime {
    private static int[] coinsInCents; 
    private static String[] snackNames;
    static final int NUMSNACK = 4;
    static Snack[] Snackcell = new Snack[NUMSNACK];
    
    /**
     * Main Panel that display the price of snacks and obtain the choices
     * 
     * @return Main Panel
     */
    static private JPanel getPricePanel(){
        JPanel base = new JPanel(new GridLayout(0, 1, 7, 7));
        JLabel mess;
        JLabel start = new JLabel("Buy Snack: Input your choice");
        JLabel end = new JLabel(String.format("%d. Cancel",NUMSNACK+1));
        base.add(start);
        for(int i=0;i<NUMSNACK;i++){
            mess = getPriceLabel(Snackcell[i], i);
            base.add(mess);
        }
        base.add(end);
        
        return base;
    }
    
    /**
     * Construct returned coin panel
     * 
     * @param taget choice of snack from user
     * @return leftovers panel
     */
    static private JPanel getLeftPanel(int taget){
        int[] left = getLeftOvers(taget);
        JPanel base = new JPanel(new GridLayout(0, 1, 7, 7));
        base.add(new JLabel(String.format("%.2f paid",Snackcell[taget-1].getPrice())));
        base.add(new JLabel("Coins returned:"));
        for(int i=0;i<5;i++){
            if(i!=4){
                if(left[i]!=0){
                    base.add(new JLabel(String.format("$%d x %d",coinsInCents[i]/100,left[i])));
                }
            }else {
                if(left[i]!=0){
                    base.add(new JLabel(String.format("50c x %d",left[i])));
                }
            }
        }
        return base;
    }
    
    private static JLabel getPriceLabel(Snack taget,int num){
        String mess = String.format("%d. [$%.2f]%s",num+1,taget.getPrice(),taget.getName());
        return new JLabel(mess);
    }
    
    public static String showSnackMenu(){
        return JOptionPane.showInputDialog(null,getPricePanel(),"<type [1-5] here >");
    }
    
    
    /**
     * Find the number and type of coins returned
     * 
     * @param choice choice of snacks from user
     * @return leftover coins array
     */
    private static int[] getLeftOvers(int choice){
        int[] left = new int[5];
        int price = 2000-(int)(Snackcell[choice-1].getPrice()*100);
        for(int i=0;i<5;i++){
            if(price != 0){
            left[i] = Math.floorDiv(price,coinsInCents[i]);
            price = price - left[i]*coinsInCents[i];
            }
        }
        //System.out.println(Arrays.toString(left));
        return left;
    }
    
    public static void main(String[] args) {
        coinsInCents = new int[]{1000, 500, 200, 100, 50}; 
        snackNames = new String[]{"KitKat", "Oreo", "Marshmallow", "Cupcake"}; 
        // TODO code application logic here
        for(int i=0; i<NUMSNACK;i++){
            Snackcell[i] = new Snack(snackNames[i]);
        }
        while(true){
            int snackMenuChoice = SnackTimeHelper.getChoiceFromSnackMenu();
            if (snackMenuChoice == -1 || snackMenuChoice == 5){
                System.out.println("User closed or cancelled dialog box");
                JOptionPane.showMessageDialog(null,"Hope to serve you again");
                break;
            }else if (snackMenuChoice > NUMSNACK+1 || snackMenuChoice < 1){
                System.out.println("No good input");
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }else{
                System.out.println("User picked "+ snackMenuChoice);
                int input = JOptionPane.showConfirmDialog(null,String.format("Insert $20 to buy %s ?",snackNames[snackMenuChoice-1]),"Comfirm",JOptionPane.YES_NO_OPTION);
                if(input == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, getLeftPanel(snackMenuChoice));
                }
            }
        }
    }
    
}
