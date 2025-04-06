package com.lyy.Algorithm_AcWing;

import com.lyy.LinkedList.ListNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No829_MoNiQueue {

    static class Queue{

        int data;
        Queue next;

        public Queue() {

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());


        Queue front = null;
        Queue rear = null;
        int num = 0;
        int o = 0;
        for (;o < m; o++) {
            String[] no = br.readLine().split(" ");
            if(no[0].equals("push")){
                int data = Integer.parseInt(no[1]);
                Queue newNode = new Queue();
                newNode.data = data;
                if(front == null){
                    front = newNode;
                    rear = newNode;
                    num++;
                }else {
                    rear.next = newNode;
                    rear = newNode;
                }
            } else if (no[0].equals("pop")) {
                if(front != null){
                    front = front.next;
                }
            } else if (no[0].equals("empty")) {
                if(front == null) System.out.println("Yes");
                else System.out.println("No");
            } else if (no[0].equals("query")) {
                System.out.println(front.data);

            }
        }

    }
}
