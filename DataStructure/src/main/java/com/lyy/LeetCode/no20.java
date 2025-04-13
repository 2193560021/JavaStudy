package com.lyy.LeetCode;

public class no20 {
    public static void main(String[] args) {
        String s = "{()}";
        s = " " + s + " ";
        char[] a = s.toCharArray();
        int n = 1;
        int q = 0;
        int flag = 1;
        char[] tmp = new char[10010];
        if(a.length == 2){
            System.out.println(false);
            return;
        }
        for(int i = 1; i < a.length; i++){
            if(a[i] == '(' || a[i] == '[' || a[i] == '{'){
                tmp[n] = a[i];
                n++;
            }
            if(a[i] == ')'){
                if(tmp[n - 1] != '('){
                    System.out.println(false);
                    return;
                }
                n--;
            }
            if(a[i] == ']'){
                if(tmp[n - 1] != '['){
                    System.out.println(false);
                    return;
                }
                n--;
            }
            if(a[i] == '}'){
                if(tmp[n - 1] != '{') {
                    System.out.println(false);
                    return;
                }
                n--;
            }
        }

        if(n > 1) {
            System.out.println(false);
            return;
        }

        {
            System.out.println(true);
            return;
        }
    }
}
