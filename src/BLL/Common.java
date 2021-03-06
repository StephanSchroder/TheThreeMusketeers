/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import PL.LoginForm;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Nico, Pieter
 */
public class Common {

    //Checks the input of a string and returns a special code indicating the result of the method:
    //NOTE: Spaces are included under alphabetical characters (A-Z)
    //0 = input is empty
    //1 = input contains alphabetical characters (A-Z)
    //2 = input contains numerical characters (0-9)
    //3 = input contains special characters (!, @, #, $, %, ^, &, * etc.)
    //4 = input contains alphanumerical characters (A-Z and 0-9)
    //5 = input contains alphabetical and special characters (A-Z and !, @, # etc.)
    //6 = input contains numerical and special characters (0-9 and !, @, # etc.)
    //7 = input contains alphanumerical and special characters (A-Z and 0-9 and !, @, # etc.)
    //8 = input undetermined
    //9 = valid email address
    //10 = valid ID number 
    public static int checkInput(String input) {
        int resultCode = 8;
        boolean containsInput = false;
        boolean containsAlphabetical = false;
        boolean containsNumbers = false;
        boolean containsSpecialCharacters = false;

        //Scanning the input
        if (!(input.equals(""))) {
            containsInput = true;
            for (int i = 0; i < input.length(); i++) {
                if (!containsAlphabetical) {
                    if (((Character.toUpperCase(input.charAt(i)) == 'A') || (Character.toUpperCase(input.charAt(i)) == 'B') || (Character.toUpperCase(input.charAt(i)) == 'C') || (Character.toUpperCase(input.charAt(i)) == 'D') || (Character.toUpperCase(input.charAt(i)) == 'E') || (Character.toUpperCase(input.charAt(i)) == 'F') || (Character.toUpperCase(input.charAt(i)) == 'G') || (Character.toUpperCase(input.charAt(i)) == 'H') || (Character.toUpperCase(input.charAt(i)) == 'I') || (Character.toUpperCase(input.charAt(i)) == 'J') || (Character.toUpperCase(input.charAt(i)) == 'K') || (Character.toUpperCase(input.charAt(i)) == 'L') || (Character.toUpperCase(input.charAt(i)) == 'M') || (Character.toUpperCase(input.charAt(i)) == 'N') || (Character.toUpperCase(input.charAt(i)) == 'O') || (Character.toUpperCase(input.charAt(i)) == 'P') || (Character.toUpperCase(input.charAt(i)) == 'Q') || (Character.toUpperCase(input.charAt(i)) == 'R') || (Character.toUpperCase(input.charAt(i)) == 'S') || (Character.toUpperCase(input.charAt(i)) == 'T') || (Character.toUpperCase(input.charAt(i)) == 'U') || (Character.toUpperCase(input.charAt(i)) == 'V') || (Character.toUpperCase(input.charAt(i)) == 'W') || (Character.toUpperCase(input.charAt(i)) == 'X') || (Character.toUpperCase(input.charAt(i)) == 'Y') || (Character.toUpperCase(input.charAt(i)) == 'Z') || (Character.toUpperCase(input.charAt(i)) == ' '))) {
                        containsAlphabetical = true;
                    }
                }
                if (!containsNumbers) {
                    if (((input.charAt(i) == '0') || (input.charAt(i) == '1') || (input.charAt(i) == '2') || (input.charAt(i) == '3') || (input.charAt(i) == '4') || (input.charAt(i) == '5') || (input.charAt(i) == '6') || (input.charAt(i) == '7') || (input.charAt(i) == '8') || (input.charAt(i) == '9'))) {
                        containsNumbers = true;
                    }
                }
                if (!containsSpecialCharacters) {
                    if (((input.charAt(i) == '!') || (input.charAt(i) == '@') || (input.charAt(i) == '#') || (input.charAt(i) == '$') || (input.charAt(i) == '%') || (input.charAt(i) == '^') || (input.charAt(i) == '&') || (input.charAt(i) == '*') || (input.charAt(i) == '(') || (input.charAt(i) == ')') || (input.charAt(i) == '`') || (input.charAt(i) == '~') || (input.charAt(i) == '-') || (input.charAt(i) == '_') || (input.charAt(i) == '=') || (input.charAt(i) == '+') || (input.charAt(i) == '[') || (input.charAt(i) == '{') || (input.charAt(i) == ']') || (input.charAt(i) == '}') || (input.charAt(i) == ';') || (input.charAt(i) == ':') || (input.charAt(i) == '\'') || (input.charAt(i) == '"') || (input.charAt(i) == '\\') || (input.charAt(i) == '|') || (input.charAt(i) == ',') || (input.charAt(i) == '<') || (input.charAt(i) == '.') || (input.charAt(i) == '>') || (input.charAt(i) == '/') || (input.charAt(i) == '?'))) {
                        containsSpecialCharacters = true;
                    }
                }
            }
        } else {
            containsInput = false;
        }

        //Concluding the results
        if (containsInput) {
            if (containsAlphabetical) {
                if (containsNumbers) {
                    if (containsSpecialCharacters) {
                        resultCode = 7;
                    } else {
                        resultCode = 4;

                    }
                } else {
                    if (containsSpecialCharacters) {
                        resultCode = 5;
                    } else {
                        resultCode = 1;
                    }
                }
            } else {
                if (containsNumbers) {
                    if (containsSpecialCharacters) {
                        resultCode = 6;
                    } else {
                        resultCode = 2;
                    }
                } else {
                    if (containsSpecialCharacters) {
                        resultCode = 3;
                    } else {
                        resultCode = 8;
                    }
                }
            }
        } else {
            resultCode = 0;
        }

        //Email validation - https://emailregex.com/
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if (containsInput) {
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                resultCode = 9;
            }
        }

        //ID Validation
        if (containsInput && containsNumbers && !containsSpecialCharacters && !containsAlphabetical && input.length() == 13) {
            resultCode = 10;
        }

        return resultCode;
    }

    public static void logOff(javax.swing.JFrame j) {
        if (JOptionPane.showConfirmDialog(j, "Are you sure want to log off?", "Programing 321", JOptionPane.YES_NO_OPTION) == 0) {
            new LoginForm().setVisible(true);
            j.dispose();
        }
    }

    public static void focusGain(String str, JTextField tx) {
        if (tx.getText().trim().equals(str)) {
            tx.setText("");

        }
        tx.setForeground(Color.BLACK);
    }

    public static void focusLost(String str, JTextField tx) {
        if (tx.getText().trim().equals("")) {
            tx.setText(str);

        }
        tx.setForeground(Color.LIGHT_GRAY);
    }
    

    //<editor-fold defaultstate="collapsed" desc="Easter Egg">
    static AudioStream as = null;
    static InputStream is = null;

    public enum sound {
        PASS, EGG, TRANSITION
    };
    
    
    
    public static void playMusic(int counter) {
        String name = "";
        
        switch (counter) {
            case 0:
                name = "pass.wav";
                break;
            case 1:
                name = "Gandalf.wav";
                break;
            case 2:
                name = "russian.wav";
                break;
            default:
                name = "Gandalf.wav";
                break;
                
        }
        
        InputStream is = null;
        
        try {
            is = new FileInputStream(new File(name));
            as = new AudioStream(is);
            AudioPlayer.player.start(as);
            
            if (counter == 3) {
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EasterEggDaemon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EasterEggDaemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static List<EasterEggDaemon> threads = new ArrayList<>();
    
    public static void spam(int count) {
        for (int i = 0; i < count; i++) {
            EasterEggDaemon eg = new EasterEggDaemon();
            Thread t1 = new Thread(eg);
            threads.add(eg);
            t1.setDaemon(true);
            t1.start();
        }
    }
    
    public synchronized static void killSpam() {
        for (EasterEggDaemon t : threads) {
            t.dispose();
        }
        
        AudioPlayer.player.stop(as);
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Password Encryption">
    public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split("~");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
        
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        byte[] testHash = skf.generateSecret(spec).getEncoded();
        
        int diff = hash.length ^ testHash.length;
        
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        
        return diff == 0;
    }
    
    public static String encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 69;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();
        
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + "~" + toHex(salt) + "~" + toHex(hash);
        
    }
    
    private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
    
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Dynamic Font Setting">
    /*
    @params bit 0 for plain 1 for bold
    bit2 0 for menubar 1 for menu item
    */
    public static Font setFont(int bit, int bit2){
        
        return bit2==0 ? new Font("Century Gothic",bit,18) : bit2==1 ? new Font("Century Gothic",bit,12):new Font("Century Gothic",bit,36) ;
    }
//</editor-fold>
}
