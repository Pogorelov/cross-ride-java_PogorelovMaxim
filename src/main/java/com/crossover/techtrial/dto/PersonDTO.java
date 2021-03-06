package com.crossover.techtrial.dto;

/**
 * Created by pogorelov on 12/9/18.
 */
public class PersonDTO {

    private Long id;
    private String name;
    private String email;
    private String registrationNumber;

    public PersonDTO() {}

    public PersonDTO(Long id, String name, String email, String regNum) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registrationNumber = regNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}