package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.bean.PetBean;

public class ComputeGenderSupport {
    private ComputeGenderSupport() {
        //ignore
    }
    public static String computeGender(PetBean petBean) {
        return switch (petBean.getPetBeanGender()) {
            case 1 -> "Female";
            default -> "Male";
        };
    }
}
