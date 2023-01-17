package com.ispwproject.adoptme.model;

import java.util.List;

public interface QuestionnaireResultModel {
    enum Ages {
        Puppy(0, 1),
        Young(1, 3),
        Adult(3, 10),
        Senior(10, 30);

        private final int i;
        private final int j;

        Ages(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public List<Ages> returnAges();

}
