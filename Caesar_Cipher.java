import java.util.ArrayList;
import java.util.List;

class Caeser_Cipher {
    public static void main(String[] args) {
        System.out.println("Encrypted text: "+encrypt("hi how are you",2));
//        System.out.println(encrypt("cipher",2));
        System.out.println(decrypt(encrypt("cipher",2),2));
        System.out.println(cryptanalysis("cipher"));
    }
    public static String encrypt(String input, int key){
        String encrypted="";

        for(int i=0;i<input.length();i++){
            char ch=input.charAt(i);
            if(ch==' '){
                String updated =" ";
                encrypted = encrypted + updated;
                continue;
            }
            else {
                char check = (char) (ch + (key % 26));
                if ((int) check > 122) {
                    check = (char) ((char) check - 26);
                }
                String updated = check + "";

                encrypted = encrypted + updated;
            }
        }
        return encrypted;
    }

    public static String decrypt(String input,int key){
        String decrypted="";
        for(int i=0;i<input.length();i++){
            char ch=input.charAt(i);
            char check=(char)(ch-(key%26));
            if((int)check<97){
                check= (char) (check+26);
            }
            String updated=check+"";

            decrypted+=updated;
        }
        return decrypted;
    }

    public static List<String> cryptanalysis(String input){
        List<String> decryptedlist=new ArrayList<>();
        for(int key=1;key<26;key++){
            String decrypted= decrypt(input,key);
            decryptedlist.add(decrypted);
        }
        return decryptedlist;
    }
}
