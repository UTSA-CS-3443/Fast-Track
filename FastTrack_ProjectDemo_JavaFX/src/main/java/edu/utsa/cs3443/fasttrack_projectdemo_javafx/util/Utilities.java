package edu.utsa.cs3443.fasttrack_projectdemo_javafx.util;

public class Utilities {

    public static String encrypt(String str){
        return encrypt(str, 2);
    }

    public static String encrypt(String str, int encryptionKey){
        String encrypted = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = (int)c;
            index += encryptionKey;
            encrypted += (char)index;
        }
        return encrypted;
    }

    public static String decrypt(String str){
        return decrypt(str, 2);
    }

    public static String decrypt(String str, int decryptionKey){
        String decrypted = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = (int)c;
            index -= decryptionKey;
            decrypted += (char)index;
        }
        return decrypted;
    }
}
