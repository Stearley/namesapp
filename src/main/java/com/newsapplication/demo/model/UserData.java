package com.newsapplication.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserData implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    public UserData(){
        super();
    }
    public UserData(Long id, String first_name, String last_name) {
        super();
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

}