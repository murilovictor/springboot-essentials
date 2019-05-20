package br.com.springboot.essentials.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Murilo Victor on 20/05/2019
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student implements AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
}
