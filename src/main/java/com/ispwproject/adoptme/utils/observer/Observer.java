package com.ispwproject.adoptme.utils.observer;

import java.io.IOException;

public interface Observer {
    void update(Object object) throws IOException;
}
