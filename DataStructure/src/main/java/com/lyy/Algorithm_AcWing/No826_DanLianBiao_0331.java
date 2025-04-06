package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No826_DanLianBiao_0331 {

    static class Node{
        int data;
        int times;
        Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());


        Node header =  null;
        Node r = null;
        int i = 1;
        int o = 0;
        try {
            for (;o < m; o++) {
                String[] no = br.readLine().split(" ");
                if(no[0].equals("H")){
                    int data = Integer.parseInt(no[1]);
                    Node newheader = new Node();
    //                if (header == null){
    //                    header = newheader;
    //                    continue;
    //                }
                    newheader.next = header;
                    newheader.data = data;
                    newheader.times = i++;
                    header = newheader;

    //                System.out.println("header.data" + header.data);
                } else if (no[0].equals("I")) {
                    int times = Integer.parseInt(no[1]);
                    int data = Integer.parseInt(no[2]);
                    Node n = header;
                    while (n.times != times){
                        n = n.next;
                    }
                    Node nextNode = n.next;
                    Node newNode = new Node();
                    newNode.next = n.next;
                    n.next = newNode;
                    newNode.data = data;
    //                newNode.next = nextNode;
                    newNode.times = i++;

                } else if (no[0].equals("D")) {
                    int k = Integer.parseInt(no[1]);
                    if(k == 0){
                        header = header.next;
                        continue;
                    }
                    Node n = header;
                    while (n.times != k){
                        n = n.next;
                    }
                    n.next = n.next.next;
                }
            }

            Node n = header;

            while (n != null){
                System.out.print(n.data + " ");
                n = n.next;
            }
        } catch (Exception e) {
            System.out.println("异常步骤:" + o);
            throw new RuntimeException(e);
        }

    }

}
