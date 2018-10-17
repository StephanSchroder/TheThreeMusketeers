/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author Nico
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
        public static int CheckInput(String input)
        {
            int resultCode = 8;
            boolean containsInput = false;
            boolean containsAlphabetical = false;
            boolean containsNumbers = false;
            boolean containsSpecialCharacters = false;
            //Scanning the input
            if (!(input.equals("")))
            {
                containsInput = true;
                for (int i = 0; i < input.length(); i++)
                {
                    if (!containsAlphabetical)
                    {
                        if (((Character.toUpperCase(input.charAt(i)) =='A') || (Character.toUpperCase(input.charAt(i)) =='B') || (Character.toUpperCase(input.charAt(i)) =='C') || (Character.toUpperCase(input.charAt(i)) =='D') || (Character.toUpperCase(input.charAt(i)) =='E') || (Character.toUpperCase(input.charAt(i)) =='F') || (Character.toUpperCase(input.charAt(i)) =='G') || (Character.toUpperCase(input.charAt(i)) =='H') || (Character.toUpperCase(input.charAt(i)) =='I') || (Character.toUpperCase(input.charAt(i)) =='J') || (Character.toUpperCase(input.charAt(i)) =='K') || (Character.toUpperCase(input.charAt(i)) =='L') || (Character.toUpperCase(input.charAt(i)) =='M') || (Character.toUpperCase(input.charAt(i)) =='N') || (Character.toUpperCase(input.charAt(i)) =='O') || (Character.toUpperCase(input.charAt(i)) =='P') || (Character.toUpperCase(input.charAt(i)) =='Q') || (Character.toUpperCase(input.charAt(i)) =='R') || (Character.toUpperCase(input.charAt(i)) =='S') || (Character.toUpperCase(input.charAt(i)) =='T') || (Character.toUpperCase(input.charAt(i)) =='U') || (Character.toUpperCase(input.charAt(i)) =='V') || (Character.toUpperCase(input.charAt(i)) =='W') || (Character.toUpperCase(input.charAt(i)) =='X') || (Character.toUpperCase(input.charAt(i)) =='Y') || (Character.toUpperCase(input.charAt(i)) =='Z') || (Character.toUpperCase(input.charAt(i)) ==' ')))
                        {
                            containsAlphabetical = true;
                        }
                    }
                    if (!containsNumbers)
                    {
                        if (((input.charAt(i) == '0') || (input.charAt(i) == '1') || (input.charAt(i) == '2') || (input.charAt(i) == '3') || (input.charAt(i) == '4') || (input.charAt(i) == '5') || (input.charAt(i) == '6') || (input.charAt(i) == '7') || (input.charAt(i) == '8') || (input.charAt(i) == '9')))
                        {
                            containsNumbers = true;
                        }
                    }
                    if (!containsSpecialCharacters)
                    {
                        if (((input.charAt(i) == '!') || (input.charAt(i) == '@') || (input.charAt(i) == '#') || (input.charAt(i) == '$') || (input.charAt(i) == '%') || (input.charAt(i) == '^') || (input.charAt(i) == '&') || (input.charAt(i) == '*') || (input.charAt(i) == '(') || (input.charAt(i) == ')') || (input.charAt(i) == '`') || (input.charAt(i) == '~') || (input.charAt(i) == '-') || (input.charAt(i) == '_') || (input.charAt(i) == '=') || (input.charAt(i) == '+') || (input.charAt(i) == '[') || (input.charAt(i) == '{') || (input.charAt(i) == ']') || (input.charAt(i) == '}') || (input.charAt(i) == ';') || (input.charAt(i) == ':') || (input.charAt(i) == '\'') || (input.charAt(i) == '"') || (input.charAt(i) == '\\') || (input.charAt(i) == '|') || (input.charAt(i) == ',') || (input.charAt(i) == '<') || (input.charAt(i) == '.') || (input.charAt(i) == '>') || (input.charAt(i) == '/') || (input.charAt(i) == '?')))
                        {
                            containsNumbers = true;
                        }
                    }
                }
            }
            else
            {
                containsInput = false;
            }

            //Concluding the results
            if (containsInput)
            {
                if (containsAlphabetical)
                {
                    if (containsNumbers)
                    {
                        if (containsSpecialCharacters)
                        {
                            resultCode = 7;
                        }
                        else
                        {
                            resultCode = 4;
                        }
                    }
                    else
                    {
                        if (containsSpecialCharacters)
                        {
                            resultCode = 5;
                        }
                        else
                        {
                            resultCode = 1;
                        }
                    }
                }
                else
                {
                    if (containsNumbers)
                    {
                        if (containsSpecialCharacters)
                        {
                            resultCode = 6;
                        }
                        else
                        {
                            resultCode = 2;
                        }
                    }
                    else
                    {
                        if (containsSpecialCharacters)
                        {
                            resultCode = 3;
                        }
                        else
                        {
                            resultCode = 8;
                        }
                    }
                }
            }
            else
            {
                resultCode = 0;
            }

            return resultCode;
        }
        
  
}