import java.util.*;

public class Transposition_Ciphers {

    public static void main(String[] args) {
        System.out.println("Encrypted Text:"+encrypt(create_matrix("xiglrspskc","4312"),"4312"));
//        String decrypted=decrypt(reverse_matrix("ttnaaptmtsuoaodwcoixknlypetz","4312567"),"4312567",5,7);
//        System.out.println("Decrypted text:"+decrypted);
    }
    public static char[][] create_matrix(String plaintext, String key){
        int col_length=key.length();
        int row_length=0;
        if(plaintext.length()%col_length==0){
            row_length= (plaintext.length()/col_length)+1;
        }
        else {
            row_length = (plaintext.length() / col_length) + 2;
        }
        char[][] matrix=new char[row_length][col_length];
        for(int i=0;i<col_length;i++){
            matrix[0][i]=key.charAt(i);
        }
        int index=0;
        int extra=0;

        for(int i=1;i<row_length;i++){
            for(int j=0;j<col_length;j++){
                if(index==plaintext.length()){
                    matrix[i][j]=(char)('x'+extra);
                    extra++;
                }
                else {
                    matrix[i][j] = plaintext.charAt(index);
                    index++;
                }
            }
        }
        return matrix;
    }
    public static String encrypt(char[][] matrix,String key) {
        int num=1;
        char[] index_row=matrix[0];
        String encrypted="";
        while(num!=key.length()+1) {
            int index=0;
            for (int i = 1; i < index_row.length; i++) {
                if(index_row[i]-'0'==num){
                    index=i;
                }
            }
            for(int i=1;i<matrix.length;i++){
                encrypted+=matrix[i][index];
            }
            num++;
        }
        return encrypted;
    }

    public static char[][] reverse_matrix(String encrypted,String key){
        int row_length=(encrypted.length()/key.length()) +1;
        int col_length=key.length();
        char[][] matrix=new char[row_length][col_length];
        char index='1';
        for(int i=0;i<col_length;i++){
            matrix[0][i]=index ;
            index++;
        }
        int length=0;
        for(int i=0;i<col_length;i++){
            for(int j=1;j<row_length;j++){
                matrix[j][i]=encrypted.charAt(length);
                length++;
            }
        }
        return matrix;
    }

    public static String decrypt(char[][] reverse_matrix,String key,int row_length,int col_length){
        String decrypted="";
        for(int i=1;i<row_length;i++){
            for(int j=0;j<key.length();j++){
                for(int k=1;k<=col_length;k++){
                    if(k==key.charAt(j)-'0'){
                        if(reverse_matrix[i][k-1]>='x'){
                            continue;
                        }
                        decrypted+=reverse_matrix[i][k-1];
                    }
                }
            }
        }
        return decrypted;
    }


}
