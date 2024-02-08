package com.userservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "users_table")
@Table(name = "your_table_name", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
//@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))

public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID_USER")
    Long idUser;

    @Column(name="FIRST_NAME")
    String firstName;

    @Column(name="LAST_NAME")
    String lastName;

    @Column(name="USERNAME")
    String username;

    @Column(name="PASSWORD")
    String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BIRTH")
    Date birth;

    @Column(name="STATUS")
    String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;

    public UserEntity(Long idUser, String firstName, String lastName, String username, String password, Date birth, String status, UserType userType) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.status = status;
        this.userType = userType;
    }

    public UserEntity() {
    }















    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserEntity userEntity;

        private Builder() {
            userEntity = new UserEntity();
        }

        public Builder firstName(String firstName) {
            userEntity.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            userEntity.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            userEntity.username = username;
            return this;
        }

        public Builder password(String password) {
            userEntity.password = password;
            return this;
        }

        public Builder birth(Date birth) {
            userEntity.birth = birth;
            return this;
        }

        public Builder status(String status) {
            userEntity.status = status;
            return this;
        }

        public Builder userType(UserType userType) {
            userEntity.userType = userType;
            return this;
        }

        public UserEntity build() {
            return userEntity;
        }
    }


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth=" + birth +
                ", status='" + status + '\'' +
                ", userType=" + userType +
                '}';
    }

    public enum UserType {
        DRIVER,
        USER,
        MODERATOR
    }

}
