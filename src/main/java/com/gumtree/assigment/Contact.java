package com.gumtree.assigment;

import com.gumtree.assigment.enums.Gender;

import java.util.Date;

public class Contact {

    private String name;
    private Gender gender;
    private Date birthDate;

    public Contact(String name, Gender gender, Date birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
