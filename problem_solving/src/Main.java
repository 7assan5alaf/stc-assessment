import java.util.Scanner;
import java.util.Stack;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
         String s=sc.next();
        System.out.println(reverseWord(s));

    }
    public static String reverseWord(String s){
        String ans="";
        StringBuilder st=new StringBuilder();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('){
                i++;
                for (;i<s.length();i++){

                    if (s.charAt(i)==')'){
                        break;
                    }
                    st.append(s.charAt(i));
                }

                ans+='('+st.reverse().toString()+')';
                st=new StringBuilder();
            }
            else ans+=s.charAt(i);
        }
        return ans;
    }

}
