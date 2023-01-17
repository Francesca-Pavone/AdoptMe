package com.ispwproject.adoptme.utils.adapter;

import com.ispwproject.adoptme.model.QuestionnaireResultModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireAdapter implements QuestionnaireResultModel {
    private boolean puppy;
    private boolean young;
    private boolean adult;
    private boolean senior;

    public QuestionnaireAdapter(boolean puppy, boolean young, boolean adult, boolean senior) {
        this.puppy = puppy;
        this.young = young;
        this.adult = adult;
        this.senior = senior;
    }

    @Override
    public List<Ages> returnAges() {
        List<Ages> agesList = new ArrayList<>();
        if(this.puppy)
            agesList.add(Ages.Puppy);
        if(this.young)
            agesList.add(Ages.Young);
        if (this.adult)
            agesList.add(Ages.Adult);
        if (this.senior)
            agesList.add(Ages.Senior);
        return agesList;
    }

    public int cbewi() {
        return 1;
    }
}
