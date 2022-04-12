/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * CICI1130 Assignment 2 FutureValue
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
 * Date        : 10th Oct 2021
 */

package futurevalue;

import java.util.Scanner;
import java.util.Map;
/**
 *
 * @author LI tsz kin
 */
public class FutureValue {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner keyboard;
            
    // validate input, looks nice
    boolean check(int taget, double ans){
        //storing min and max of taget
        //taget 1,2,3 is principal, timespan and compunding period
        double range[][] = {{10000,109700},{1,10},{2,10}};
        if(taget <= 3){
            taget -= 1;
            if(ans >= range[taget][0] && ans <= range[taget][1]){
                return true;
            }else {
                return false;
            }
        //taget 4 is compunding months
        }else if(taget == 4){
            if(ans == 2 | ans == 3 | ans == 6){
                return true;
            }else {
                return false;
            }
        }
        return false;
    } 
    
    //ask user for input
    double ask(int taget){
        //cool thing found on google prob bad
        Map<Integer,String> key = Map.of(1,"Principal",2,"Annual Interest Rate",3,"Timespan",4,"Compunding Period");
        Map<Integer,String> range = Map.of(1,"[$10000.00 - %109700.00]",2,"[1.0% - 10.0%]",3,"2 - 10 years",4,"2, 3 or 6 months");
        
        double ans = 0;
        //keep ask question until check return true
        while(true){
            System.out.print("Input "+ key.get(taget)+ " " + range.get(taget) + ":");
            ans = keyboard.nextDouble();
            if(check(taget,ans)){
                break;
            }else {
                System.out.println("Invalid "+key.get(taget)+", please enter again.");
            }
        }
        return ans;
    }
    
    //func to calculate futurevalue
    void cal(double Oldmoney, double rate, int time, int period){
        double newmoney = Oldmoney;
        rate = rate/100;
        boolean onlyonetime = true;
        //I need to type another line because of grammer, thanks
        String nogd = "";
//        System.out.println(newmoney);
        int compfeq = 12/period;
//        System.out.println(Math.pow(1 + rate/compfeq , i*compfeq));
        //loop each year until limit
        for(int i = 1;i <= time; i++){
            newmoney = newmoney * Math.pow(1 + rate/compfeq , compfeq);
//            System.out.println(newmoney);
            if(i == 1){
                nogd = " year:";
            }else {
                nogd = " years:";
            }
            //print output
            if(i <= 2 | i == time){
                System.out.printf("Future Value after %d%s %.2f\n", i, nogd ,newmoney);
            }else if( i != time && onlyonetime){
                onlyonetime = false;
                System.out.println("...");
            }
        }
        //call func to cal time to double
        caltime(compfeq, rate, period);
    }
    
    //func to calculate double assest time
    void caltime(int compfeq, double rate, int period){
        double dtime = 0;
        //function given
        dtime = Math.log(2)/(compfeq*Math.log(1+rate/compfeq));
        int dyear = (int)Math.floor(dtime);
        //convert 10base month to 12base month
        double dmonth = (dtime%1)*12;
//        System.out.println(dmonth);
        //find the number of compounding period then mutiply period to get actual month
        dmonth = Math.ceil(dmonth/period)*period;
        int ddmonth = (int)dmonth;
        //carrybit
        if(ddmonth == 12){
            dyear++;
            ddmonth = 0;
        }
        //print output
        if(ddmonth == 0){
            System.out.println("Time to double assest: "+dyear+" years");
        }else{
            System.out.println("Time to double assest: "+dyear+" years "+ddmonth+" months");
        }
    }
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //prof said dont say static, so didnt, dafuq
        FutureValue test = new FutureValue();
        keyboard = new Scanner(System.in);
        
        double principal, rate;
        int time, period;
        
        // ask values
        principal = test.ask(1);
        rate = test.ask(2);
        time = (int)test.ask(3);
        period = (int)test.ask(4);
        
        test.cal(principal, rate, time, period);
        
//        System.out.println(principal);
//        System.out.println(rate);
//        System.out.println(time);
//        System.out.println(period);      
    }
    
}
