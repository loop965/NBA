package com.yf.producer.collection;

/**
 * @author: yf
 * @date: 2020/03/26  13:18
 * @desc:
 */
public class MyLinkedList<E> {

    private Node<E> first;

    private Node<E> last;

    public MyLinkedList(){

    }


    public void add(E e){
        linkLast(e);
    }

    public void removeLast() throws Exception {
        if (last == null){
            throw new Exception("no such element");
        }
        Node<E> l = last;
        Node<E> prev = l.prev;
        prev.next = null;
        last = prev;
    }


    private void linkLast(E e){
        Node<E> l = last;
        Node<E> newNode = new Node<>(l,e,null);
        last = newNode;
        if (l == null){
            first = newNode;
        }else {
            l.next = newNode;
        }

    }



    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }


}
