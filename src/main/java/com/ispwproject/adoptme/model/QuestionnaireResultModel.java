package com.ispwproject.adoptme.model;

import java.util.List;

public interface QuestionnaireResultModel {
    enum Ages {
        PUPPY(0, 1),
        YOUNG(1, 3),
        ADULT(3, 10),
        SENIOR(10, 30);

        private final int i;
        private final int j;

        Ages(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public List<Ages> returnAges();

}
