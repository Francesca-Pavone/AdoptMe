package com.ispwproject.adoptme.engineering.observer;

public interface Observer {
    void update(Object object) throws Exception;
    void update2(Object object1, Object object2) throws Exception;
}
