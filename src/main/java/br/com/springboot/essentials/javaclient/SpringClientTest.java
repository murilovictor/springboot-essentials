package br.com.springboot.essentials.javaclient;

import br.com.springboot.essentials.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Murilo Victor on 21/06/2019
 */
public class SpringClientTest {
    private static final String ROOT_URI = "http://localhost:8080/v1/protected/students";
    private static final String USER_NAME = "MuriloUser";
    private static final String PASSWORD = "murilo";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(ROOT_URI)
                .basicAuthentication(USER_NAME,PASSWORD)
                .build();

        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        ResponseEntity<Student> studentResponseEntity = restTemplate.getForEntity("/{id}", Student.class, 1);

        System.out.println(student);
        System.out.println(studentResponseEntity);

        Student[] students = restTemplate.getForObject("/", Student[].class);
        System.out.println(Arrays.toString(students));


        ResponseEntity<List<Student>> responseEntity = restTemplate.exchange("/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                });

        System.out.println(responseEntity.getBody());

    }
}
