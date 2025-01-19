package com.lyy.LinkedList;

public class LinkedList01 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        ListNode head = n1;

        ListNode n8 = new ListNode(8);
        n8.next = n4;
        n3.next = n8;

        while (head != null){
            System.out.println("head = " + head.val);
            head = head.next;
        }

    }
}
