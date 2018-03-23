package com.thoughtworks.felix.domain.user;

import com.thoughtworks.felix.interfaces.validation.Age;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Age
    private Integer age;

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
