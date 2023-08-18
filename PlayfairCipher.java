import java.util.*;
public class PlayfairCipher {
    public static void main(String[] args) {
//        List<String> list=split("plaintext");
//        System.out.println(list);
//        char[][] matrix=createMatrix("water");
//        for(char[] row:matrix){
//            for(char ch:row){
//                System.out.print(ch+"");
//            }
//            System.out.println();
//        }
        String encrypted=encrypt("plaintext","water");
        System.out.println("Encrypted Message:"+encrypted);
        String decrypted=decrypt("water","qkcopwtydt");
        System.out.println("Decrypted Message:"+decrypted);
    }

    public static List<Integer> getLocation(char ch,char[][] matrix){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(matrix[i][j]==ch){
                    list.add(i);
                    list.add(j);
                    return list;
                }
            }
        }
        return list;
    }

    public static String decrypt(String key, String encrypted){
        String decrypted="";
        char[][] matrix=createMatrix(key);
        List<String> list=split(encrypted);

        for(String str:list){
            char ch1=str.charAt(0);
            char ch2=str.charAt(1);
            List<Integer> l1=getLocation(ch1,matrix);
            List<Integer> l2=getLocation(ch2,matrix);
            int r1=l1.get(0);
            int c1=l1.get(1);
            int r2=l2.get(0);
            int c2=l2.get(1);

            if(r1==r2){
                c1=c1-1;
                c2=c2-1;
                if(c1<0){
                    c1=5+c1;
                }
                if(c2<0){
                    c2=5+c2;
                }
                String left=matrix[r1][c1]+"";
                String right=matrix[r2][c2]+"";
                decrypted+=left+right;
            }
            else if(c1==c2){
                r1=r1-1;
                r2=r2-1;
                if(r1<0){
                    r1=5+r1;
                }
                if(r2<0){
                    r2=5+r2;
                }
                String left=matrix[r1][c1]+"";
                String right=matrix[r2][c2]+"";
                decrypted+=left+right;
            }
            else{
                String left=matrix[r1][c2]+"";
                String right=matrix[r2][c1]+"";
                decrypted+=left+right;
            }
        }
        return decrypted;
    }
    public static String encrypt(String plaintext,String key){
        String encrypted="";
        List<String> list=split(plaintext);
        char[][] matrix=createMatrix(key);
        for(String str:list){
            char ch1=str.charAt(0);
            char ch2=str.charAt(1);
            List<Integer> l1=getLocation(ch1,matrix);
            List<Integer> l2=getLocation(ch2,matrix);
            int r1=l1.get(0);
            int c1=l1.get(1);
            int r2=l2.get(0);
            int c2=l2.get(1);

            if(r1==r2){
                String left=matrix[r1][(c1+1)%5]+"";
                String right=matrix[r2][(c2+1)%5]+"";
                encrypted+=left+right;
            }
            else if(c1==c2){
                String left=matrix[(r1+1)%5][c1]+"";
                String right=matrix[(r2+1)%5][c2]+"";
                encrypted+=left+right;
            }
            else{
                String left=matrix[r1][c2]+"";
                String right=matrix[r2][c1]+"";
                encrypted+=left+right;
            }
        }
        return encrypted;
    }

    public static char[][] createMatrix(String key){
        char[][] matrix=new char[5][5];
        int length=key.length();
        int c=0;
        int row=0;
        int col=0;
            outerloop:
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(c==length){
                        row=i;
                        col=j;
                        break outerloop;
                    }
                    else{
                    matrix[i][j]=key.charAt(c);
                    row=i;
                    col=j;
                    c++;
                    }
                }
            }

        int counter=1;
        for(int i=row;i<5;i++){
            for(int j=col;j<5;j++){
                char ch='a';
                while(contains(ch,key) || ch=='j'){
                    ch= (char) ('a'+counter);
                    counter++;
                }
                matrix[i][j]=ch;
            }
        }
        return matrix;
    }


    public static boolean contains(char a, String key){
        for(int i=0;i<key.length();i++){
            if(a==key.charAt(i)){
                return true;
            }
        }
        return false;
    }
    public static List<String> split(String plaintext){
        List<String> list=new ArrayList<>();
        int length=plaintext.length();
        String element="";
        int counter=0;
        int i=0;
        while(i!=length){
            element="";
            if(i==length-1){
                element+=plaintext.substring(i,i+1)+(char)('x'+counter);
                list.add(element);
                i++;
            }

            else if(plaintext.charAt(i)==plaintext.charAt(i+1)){
                element+= plaintext.substring(i,i+1)+(char)('x'+counter);
                counter++;
                list.add(element);
                i++;
            }
            else{
                if(i+1==length){
                    element+=plaintext.substring(i,i+1)+(char)('x'+counter);
                    counter++;
                    list.add(element);
                    i++;
                }
                else {
                    element+= plaintext.substring(i,i+2);
                    list.add(element);
                    i += 2;
                }
                }
            }
        return list;
    }

}
