public class HillCipher {

    public static void main(String[] args) {
        System.out.println("Encrypted text: "+encrypt("EXAM","HILL"));
        int[][] matrix=getKeyMatrix("HILL");
        for(int[] row:matrix){
            for(int val:row){
                System.out.println(val);
            }
        }
//        int[][] key_inverse={{25,22},{1,23}};
//        System.out.println("Decrypted text: "+decrypt(key_inverse,"ELSC"));
    }

    public static int[][] getKeyMatrix(String plaintext){
        int cols=plaintext.length()/2;
        int[][] P=new int[2][cols];
        int length=0;
        while(length!=plaintext.length()){
        for(int i=0;i<2;i++){
           for(int j=0;j<plaintext.length()/2;j++){
            P[i][j]=(int)plaintext.charAt(length) -65;
            length++;
                }
            }
        }
        return P;
    }

    public static int[][] getplainTextMatrix(String key){
        int[][] K=new int[2][key.length()/2];
        int length=0;
        for(int i=0;i<key.length()/2;i++){
            for(int j=0;j<2;j++){
                K[j][i]=(int)key.charAt(length) -65;
                length++;
            }
        }
        return K;
    }
    public static int[][] mat_mul(int[][] matrix1,int r1,int c1,int[][] matrix2,int r2,int c2){
        int[][] ans=new int[r1][c2];
        for(int i=0;i<r1;i++){
            for(int j=0;j<c2;j++){
                ans[i][j]=0;
                for(int k=0;k<c1;k++){
                    ans[i][j]+=matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return ans;
    }
    public static String encrypt(String plaintext,String key){
        int length=plaintext.length();
        int[][] P=getplainTextMatrix(plaintext);
        int[][] K=getKeyMatrix(key);
        int[][] Encrypted=mat_mul(K,2,key.length()/2,P,2,plaintext.length()/2);
//        return Encrypted;
        String encrypted="";
        for(int i=0;i<plaintext.length()/2;i++){
            for(int j=0;j<2;j++){
                encrypted+=(char)(Encrypted[j][i]%26+65);
            }
        }
        return encrypted;
    }

    public static String decrypt(int[][] K_inverse, String encrypted){
        int[][] E=getplainTextMatrix(encrypted);
        int[][] multiplied_matrix=mat_mul(K_inverse,2,2,E,2,2);
        String decrypted="";
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                multiplied_matrix[j][i]=multiplied_matrix[j][i]%26;
                decrypted+=(char)('a'+multiplied_matrix[j][i])+"";
            }
        }
        return decrypted.toUpperCase();
    }

}
