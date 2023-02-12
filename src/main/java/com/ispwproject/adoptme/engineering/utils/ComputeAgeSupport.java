package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.bean.PetBean;

import java.time.Year;

public class ComputeAgeSupport {
    private ComputeAgeSupport() {
        //ignore
    }
    public static String computeAge(PetBean petBean) {
        int yearDiff = Year.now().getValue() - petBean.getPetBeanBirthYear();
        if (yearDiff <= 1)
            return "Puppy";
        else if (yearDiff <= 3)
            return "Young";
        else if (yearDiff <= 10)
            return "Adult";
        else
            return "Senior";
    }
}
