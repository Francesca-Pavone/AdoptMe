package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.bean.PetBean;

import java.time.Year;

public class ComputeAgeSupport {
    public static String computeAge(PetBean petBean) {
        String age;
        int yearDiff = Year.now().getValue() - petBean.getYearOfBirth();
        if (yearDiff <= 1)
            age = "Puppy";
        else if (yearDiff <= 3)
            age = "Young";
        else if (yearDiff <= 10)
            age = "Adult";
        else
            age = "Senior";
        return age;
    }
}
