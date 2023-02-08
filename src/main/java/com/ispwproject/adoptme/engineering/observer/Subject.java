package com.ispwproject.adoptme.engineering.observer;

import java.util.List;
import java.util.Vector;

/** definisco la classe astratta anche se tutti i metodi che
 *  fornisce sono concreti per impedire l'istanziazione di essa
 */
public abstract class Subject {
    private final List<Observer> observersList;
    protected Subject() {
        this((Observer) null);
    }

    protected Subject(Observer observer){
        this(new Vector<>());
        if(observer!= null){
            this.register(observer);
        }
    }

    protected Subject(List<Observer> observersList) {
        this.observersList = observersList;
    }

    public void register(Observer observer) {
        observersList.add(observer);
    }

    public void unregister(Observer observer) {
        observersList.remove(observer);
    }

    public void notifyObservers(Object object){
        for (Observer observer : observersList) {
            observer.update(object);
        }
    }
    public void notifyObservers(Object object1, Object object2){
        for (Observer observer : observersList) {
            observer.update2(object1, object2);
        }
    }

}
