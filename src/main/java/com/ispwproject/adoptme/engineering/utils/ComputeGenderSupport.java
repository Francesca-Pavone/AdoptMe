package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.bean.PetBean;

public class ComputeGenderSupport {
    public static String computeGender(PetBean petBean) {
        String gender = (switch (petBean.getGender()) {
            case 1 -> "Female";
            default -> "Male";
        });
        return gender;
    }
}
