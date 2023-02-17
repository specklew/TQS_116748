package com.example.project;

import java.util.LinkedList;

public class TqsStack<T> {

    private final LinkedList<T> collection = new LinkedList<>();

    public T pop(){
        return collection.pop();
    }
    public int size(){
        return collection.size();
    }
    public T peek(){
        return collection.getFirst();
    }
    public void push(T element){
        collection.push(element);
    }
    public boolean isEmpty(){
        return collection.isEmpty();
    }
}
