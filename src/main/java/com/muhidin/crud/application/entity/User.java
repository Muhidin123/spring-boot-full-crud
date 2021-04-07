package com.muhidin.crud.application.entity;

//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
//import java.util.Date;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
//@Table(name = "users")
public class User {

    @Column(updatable = false, nullable = false)
    private @Id @GeneratedValue Long id;

    private String name;
    private String email;

    public User() {}

    User(String name, String email) {

        this.name = name;
        this.email = email;
    }

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt =  new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
                && Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + '}';
    }



}