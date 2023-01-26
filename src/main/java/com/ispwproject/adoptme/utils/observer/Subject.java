package com.ispwproject.adoptme.utils.observer;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

//definisco la classe astratta anche se tutti i metodi che fornisce sono concreti per impedire l'istanziazione di essa
public abstract class Subject {
    private List<Observer> observerList;
    protected Subject() {
        this((Observer) null);
    }

    protected Subject(Observer observer){
        this(new Vector<>());
        if(observer!= null){
            this.register(observer);
        }
    }

    protected Subject(List<Observer> observerList) {
        this.observerList = observerList;
    }

    public void register(Observer observer) {
        observerList.add(observer);
    }

    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObservers(Object object)  {
        for (Observer observer : observerList) {
            observer.update(object);
        }
    }
    public void notifyObservers(Object object1, Object object2)  {
        for (Observer observer : observerList) {
            observer.update2(object1, object2);
        }
    }

}
