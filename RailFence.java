public class RailFence {
    public static void main(String[] args) {
        String pre_encryption=encrypt("arpitmathur",5);
        String encrypted="";
        for(int i=0;i<pre_encryption.length();i++){
            if(pre_encryption.charAt(i)==' '){
                continue;
            }
            else {
                encrypted += pre_encryption.charAt(i) + "";
            }
        }
        System.out.println("Encrypted Message: "+encrypted);
        System.out.println("Decrypted Message: "+decrypt(encrypt("arpitmathur",5),5));

    }

    public static String encrypt(String plaintext, int key){
        char[][] matrix=getMatrix(plaintext,key);
        String encrypted="";
        for(char[] row:matrix){
            for(char c:row){
                encrypted+=c+"";
            }
        }
        return encrypted;
    }


    public static char[][] getMatrix(String plaintext,int key){
        int cols=plaintext.length();
        int rows=key;

        char[][] matrix=new char[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j]=' ';
            }
        }

        int index=0;
        int pointer=0;
        for(int i=0;i<cols;i++){
            if(pointer==0){
                matrix[index][i]=plaintext.charAt(i);
                index++;
                if(index==key){
                    pointer=1;
                    index--;
                }
            }
            else {
                matrix[--index][i]=plaintext.charAt(i);
                if(index==0){
                    pointer=0;
                    index++;
                }
            }
        }

        return matrix;
    }



    public static String decrypt(String encrypted,int key) {
        int cols = 0;
        for (int i = 0; i < encrypted.length(); i++) {
            if (encrypted.charAt(i) != ' ') {
                cols++;
            }
        }
        char[][] matrix = new char[key][cols];
        int idx = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = encrypted.charAt(idx);
                idx++;
            }
        }
        String decrypted = getDecrypted(key, cols, matrix);
        return decrypted;
    }

    private static String getDecrypted(int key, int cols, char[][] matrix) {
        String decrypted = "";
        int index = 0;
        int pointer = 0;
        for (int i = 0; i < cols; i++) {
            if (pointer == 0) {
                decrypted += matrix[index][i];
                index++;
                if (index == key) {
                    pointer = 1;
                    index--;
                }
            } else {
                decrypted += matrix[--index][i];
                if (index == 0) {
                    pointer = 0;
                    index++;
                }
            }
        }
        return decrypted;
    }
}
