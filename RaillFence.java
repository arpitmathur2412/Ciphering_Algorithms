public class RaillFence {
    public static void main(String[] args) {
        System.out.println("Encrypted text:"+encrypt("arpitmathur",2));
        System.out.println("Decrypted text:"+decrypt("aptahrrimtu",2));
    }

    public static String encrypt(String plaintext, int key){
        int cols=(plaintext.length()/key)+1;
        char[][] matrix=new char[key][cols];
        int index=0;
        for(int i=0;i<cols;i++){
            for(int j=0;j<key;j++){
                if(index==plaintext.length() && i==cols-1){
                    matrix[j][i]=' ';
                }
                else {
                    matrix[j][i] = plaintext.charAt(index);
                    index++;
                }
            }
        }

        String encrypted="";
        for(int i=0;i<key;i++){
            for(int j=0;j<cols;j++){
                encrypted+=matrix[i][j]+"";
            }
        }
        return encrypted;
    }

    public static String decrypt(String encrypted,int key){
        int cols=(encrypted.length()/key)+1;
        char[][] matrix=new char[key][cols];

        int index=0;
        for(int i=0;i<key;i++){
            for(int j=0;j<cols;j++){
                if(index==encrypted.length()){
                    matrix[i][j]=' ';
                }
                else {
                    matrix[i][j] = encrypted.charAt(index);
                    index++;
                }
            }
        }

        String decrypted="";

        for(int i=0;i<cols;i++){
            for(int j=0;j<key;j++){
                decrypted+=matrix[j][i]+"";
            }
        }
        return decrypted;
    }
}
