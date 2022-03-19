package com.mikaros.aesencryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


// JAVA 11 removed the java EE modules
// import javax.xml.bind.DatatypeConverter;

//Install Jakarta.xml.bin to use  DatatypeConverter

import jakarta.xml.bind.*;

/**
 *
 * @author MikoG
 */
public class AESEncryption {

    /**
     * 1- Generaremos un texto plano para encriptarlo 2- Optendremos un a clave
     * secreta (Impresa en Hexadecimal)
     */
    public static void main(String[] args) {
        String text = "Hello World";
        //generaremos una llave secreta, que resibiremos de una funcion 
        SecretKey secKey = getSecretEncryptionKey();
        byte[] cipherText = encryptText(text, secKey);
        String decryptedText = decryptText(cipherText, secKey);

        // Imprimimos el texto orginal, la llave AES, el texto encriptado y su version sin encriptar
        System.out.println("Original Text: " + text);
        System.out.println("AES key (HEX Form): " + bytesToHex(secKey.getEncoded()));
        System.out.println("Encrypted Text (HEX Form): " + bytesToHex(cipherText));
        System.out.println("Desrypted Text: " + decryptedText);

    }

    /**
     * Optenemos la llave de encriptacion AES, en este programa se guarda de
     * forma segura
     *
     * @return
     * @throws Exception
     */
    public static SecretKey getSecretEncryptionKey() throws Exception {
        // crearemos un generador con el algoritmo AES
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        // establecemos el largo de la clave, en este caso 128 caracteres
        generator.init(256);
        //generaremos la clave
        SecretKey secKey = generator.generateKey();

        //retornamos la clave generada       
        return secKey;
    }

    /**
     * 1- encriptamos el texto con nuestra llave secreta
     *
     * @param Text
     * @param secKey
     * @return
     * @throws Exception
     */
    
    public static byte[] encryptText(String Text, SecretKey secKey) throws Exception {
        //creamos un instancia
        Cipher aesCipher = Cipher.getInstance("AES");
        // Inicializamos pasando la encriptacion y la llave generada de 128
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(Text.getBytes());
        //retornamos 
        return byteCipherText;
    }

    /**
     * 1- Desencriptamos el texto con la llave secreta
     *
     * @param byteCipherText
     * @param secKey
     * @return
     * @throws Exception
     */
    public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES
        Cipher aesCipher = Cipher.getInstance("AES");
        //desencriptamosos con nuestra lleave
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] byteText = aesCipher.doFinal(byteCipherText);
        //retornamos
        return new String(byteText);
    }

    private static String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

}
