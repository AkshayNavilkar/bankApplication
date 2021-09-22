package com.bank.app.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "f_name")
    private String f_name;

    @Column(name = "m_name")
    private String m_name;

    @Column(name = "l_name")
    private String l_name;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_pan")
    private String user_pan;

    @Column(name = "user_uid")
    private Long user_uid;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Column(name = "user_email")
    private String user_email;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pan() {
        return user_pan;
    }

    public void setUser_pan(String user_pan) {
        this.user_pan = user_pan;
    }

    public Long getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(Long user_uid) {
        this.user_uid = user_uid;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String f_name, String l_name) {
        this.user_name = f_name+l_name;
    }
}
