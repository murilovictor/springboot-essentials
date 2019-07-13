package br.com.springboot.essentials.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Murilo Victor on 20/05/2019
 */
@Entity
@Getter
@Setter
@ToString
public class Student implements AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    private int age;

    public Student(@NotEmpty String name, @NotEmpty @Email String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }
}
