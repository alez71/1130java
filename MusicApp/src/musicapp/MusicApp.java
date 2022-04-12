/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapp;

import java.util.*;
import java.io.*;

/**
 *
 * @author alexli
 */
public class MusicApp {
    static Scanner songin;
    static Scanner keyboard = new Scanner(System.in);
    static PrintStream song;
    static String fname;
    static Map<Character,String> notes = Map.of('d',"Do",'r',"Re",'m',"Me",'f',"Fa",'s',"So",'l',"La",'t',"Ti",'o',"Off");
    static final boolean CHECKNOTES = true;
    static final String TIMES = "Time singature = ";
    
    private static boolean checkfiletype(String name){
        int dot = name.lastIndexOf(".");
        return dot == -1 || !(name.substring(dot+1).equals("txt"));
    }
    
    private static void printsong(String taget){
        song.println(taget);
        System.out.println(taget);
    }
    private static void printsong(String taget, int i){
        song.printf(taget);
        System.out.printf(taget);
    }
    
    private static int buildsong(){
        int timest;
        
        String time = songin.nextLine().strip();
//        System.out.println(time);

        String[] temp = time.split("\\s*=\\s");
//        System.out.println(Arrays.toString(temp));

        char[] line;
        
        /**
         * here separates the case of different time signature and same time signature
         */
        switch (temp[0]) {
            case "#simple song with assumed time signature" -> {
                timest = Integer.parseInt(temp[1]);
                printsong("#simple song with assumed time signature = "+timest);
                
            }
            case "#song with different time signatures" -> {
                time = songin.nextLine().strip();
                temp = time.split("\\*");
                timest = Integer.parseInt(temp[1]);
                
                printsong("#song with different time signatures");
                printsong(TIMES+timest);
            }
            default -> {
                return 1;
            }
        }
        
        /**
         * actually start to build the melody
         */
        while(songin.hasNextLine()){
            line = songin.nextLine().strip().toCharArray();
//            System.out.println(Arrays.toString(line));
            if(line[0] == '*'){
                timest = Character.getNumericValue(line[1]);
                printsong(TIMES+line[1]);
            }else{
                
                /**
                 * k counts the time passed
                 * i counts the index of the line being read
                 */
                int k=0;
                for(int i=0;i<line.length;i+=2){
//                    System.out.print("("+k+" "+timest+")");

                    /**  Whether the notes being valid is not mentioned in the assignment note
                     *   CHECKNOTES is a Boolean which is defaulted true
                     *   this will check if the notes in the file exists in the hash map
                     */
                    if(CHECKNOTES && notes.get(line[i])!=null){
                        printsong(" ",0);
                        printsong(notes.get(line[i]),0);
                        k++;
                        for(int j=1;j<Character.getNumericValue(line[i+1]);j++){
                            printsong("-",0);
                            k++;
                        }
                    }else{
                        return 2;
                    }
                    if(k%timest == 0){
                        printsong(" |",0);
                    }
                }
                printsong("\n",0);
            }
        }
        return 0;
    }
    
    private static int openfile(){
        int ttime = 0;
        while(ttime != 3){
            try {
               fname = keyboard.nextLine();
               songin = new Scanner(new File(fname));
               if(checkfiletype(fname)){
                    System.out.println("Something wrong!");
                    ttime++;
               }else{
                    System.out.println("Reading song file "+fname);
                    try {
                        song = new PrintStream("melody_"+fname);
//                        System.out.println("test2");
                        return 0;
                    } catch (Exception e) {
                        System.out.println("Cannot output melody file!");
                        keyboard.close();
                        song.close();
                        songin.close();
                        System.exit(-1);
                    }
               }
            } catch (Exception e) {
                System.out.println("Something wrong!");
                ttime++;
            }
        }
        keyboard.close();
        song.close();
        songin.close();            
        System.exit(1);
        return -1;
    }

    public static void main(String[] args) {
        openfile();
        int bug = buildsong();
        if(bug != 0){
            System.out.println("\nCannot output melody file! ");
            keyboard.close();
            song.close();
            songin.close();
            System.exit(1);
        }
    }
    
}
