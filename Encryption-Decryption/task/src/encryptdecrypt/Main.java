package encryptdecrypt;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
    
    private static String encrypt(String input, int key, String algorithm) {
        String result = "";
        if ("shift".equals(algorithm)) {
            result = shift_encrypt(input, key);
        } else {
            result = unicode_encrypt(input, key);
        }

        return result;
    }

    private static String shift_encrypt(String input, int key) {
        String result = "";
        //System.out.println(input);
        for (int i  = 0; i < input.length(); i++) {
            int newChar = (int) input.charAt(i);
            //System.out.println(input.charAt(i) + "pp" + (int) input.charAt(i) + "k " + key);
            char newChar2;
            if ((newChar >= (int) 'A' && newChar <= (int) 'Z')) {
                newChar += key;
                if (newChar > (int) 'Z') {
                    newChar -= 26;
                }
                newChar2 = (char) newChar;
                //System.out.println(newChar + "AZ" + (int) newChar2 +  ' ' + newChar2);
            } else if (newChar >= (int) 'a' && newChar <= (int) 'z') {
                newChar += key;
                if (newChar > (int) 'z') {
                    newChar -= 26;
                }
                newChar2 = (char) newChar;
                //System.out.println(newChar + "az" + (int) newChar2 +  ' ' + newChar2);
            } else {
                newChar2 = (char) newChar;
            }

            result += Character.toString(newChar2);
        }
        //System.out.println(result);
        return result;
    }

    private static String unicode_encrypt(String input, int key) {
        String result = "";

        for (int i  = 0; i < input.length(); i++) {
            int newChar = (int) input.charAt(i) + key;
            char newChar2 = (char) newChar;
            result += Character.toString(newChar2);
        }
        //System.out.println(result);
        return result;
    }
    
    private static String decrypt(String input, int key, String algorithm) {
        String result = "";

        if ("shift".equals(algorithm)) {
            result = shift_decrypt(input, key);
        } else {
            result = unicode_decrypt(input, key);
        }
        //System.out.println(result);
        return result;
    }

    private static String shift_decrypt(String input, int key) {
        String result = "";

        /*for (int i  = 0; i < input.length(); i++) {
            int newChar = (int) input.charAt(i) - key;
            char newChar2 = (char) newChar;
            result += Character.toString(newChar2);
        }*/

        for (int i  = 0; i < input.length(); i++) {
            int newChar = (int) input.charAt(i);
            //System.out.println(input.charAt(i) + "pp" + (int) input.charAt(i) + "k " + key);
            char newChar2;
            if ((newChar >= (int) 'A' && newChar <= (int) 'Z')) {
                newChar -= key;
                if (newChar < (int) 'A') {
                    newChar += 26;
                }
                newChar2 = (char) newChar;
                //System.out.println(newChar + "AZ" + (int) newChar2 +  ' ' + newChar2);
            } else if (newChar >= (int) 'a' && newChar <= (int) 'z') {
                newChar -= key;
                if (newChar < (int) 'a') {
                    newChar += 26;
                }
                newChar2 = (char) newChar;
                //System.out.println(newChar + "az" + (int) newChar2 +  ' ' + newChar2);
            } else {
                newChar2 = (char) newChar;
            }

            result += Character.toString(newChar2);
        }

        return result;
    }

    private static String unicode_decrypt(String input, int key) {
        String result = "";

        for (int i  = 0; i < input.length(); i++) {
            int newChar = (int) input.charAt(i) - key;
            char newChar2 = (char) newChar;
            result += Character.toString(newChar2);
        }
        return result;
    }
    
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        String input = scanner.nextLine();
        int key = scanner.nextInt();
        scanner.close();*/

        int  key = 0;
        String mode = "enc";
        String input =  "";
        String algorithm = "shift";

        String output = "";
        boolean inputFromFile = false;
        boolean outToFile = false;
        boolean isData = false;
        String inputFile = "";
        String outputFile = "";

        for (int i = 0; i < args.length; i++) {
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]); 
            }
            if ("-mode".equals(args[i])) {
                mode = args[i+1];
            }
            if ("-data".equals(args[i])) {
                input = args[i + 1];
                isData = true;
            }
            if ("-in".equals(args[i])) {
                inputFile = args[i + 1];
                inputFromFile = true;
            }
            if ("-out".equals(args[i])) {
                outputFile = args[i + 1];
                outToFile = true;
            }
            if("-alg".equals(args[i])) {
                algorithm = args[i + 1];
            }
        }

        if (inputFromFile && !isData) {
            File file = new File(inputFile);
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    input +=scanner.nextLine();
                }
            } catch (IOException e) {
                System.out.println("No file found: " + e.getMessage());
            }
        }

        /*mode = "dec";
        input = "Bjqhtrj yt mdujwxpnqq!";
        key = 5;*/
        if ("enc".equals(mode)) {
            output = encrypt(input, key, algorithm);
        } else if ("dec".equals(mode)) {
            output = decrypt(input, key, algorithm);
        } else {
            System.out.print("Wrong mode!");
        }

        if (outToFile) {
            File file = new File(outputFile);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(output);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        } else {
            System.out.println(output);
        }

    }
}
