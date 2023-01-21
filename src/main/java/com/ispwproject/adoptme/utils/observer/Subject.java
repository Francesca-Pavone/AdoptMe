package com.ispwproject.adoptme.utils.observer;

import java.util.List;

//definisco la classe astratta anche se tutti i metodi che fornisce sono concreti per impedire l'istanziazione di essa
public abstract class Subject {
    private List<Observer> observerList;

    public void register(Observer observer) {
        observerList.add(observer);
    }

    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObservers(Object object) {
        for (Observer observer : observerList) {
            observer.update(object);
        }
    }
}
