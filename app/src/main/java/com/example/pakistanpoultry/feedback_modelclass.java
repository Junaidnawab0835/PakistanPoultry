package com.example.pakistanpoultry;

public class feedback_modelclass {
    private String name;
    private String email;

    public feedback_modelclass(String name, String email, String phone, String question) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.question = question;
    }
    public feedback_modelclass()
    {
        
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private String phone;
    private String question;
}
