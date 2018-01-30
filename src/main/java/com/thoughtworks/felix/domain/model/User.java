package com.thoughtworks.felix.domain.model;

import com.thoughtworks.felix.domain.repo.UserRepository;
import com.thoughtworks.felix.util.InRange;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @InRange
    private Integer age;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    private UserRepository userRepository;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private User user = new User();

        public Builder withId(Long id) {
            user.setId(id);
            return this;
        }

        public Builder withName(String name) {
            user.setName(name);
            return this;
        }

        public Builder withAge(Integer age) {
            user.setAge(age);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
