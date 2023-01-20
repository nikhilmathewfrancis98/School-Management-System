package com.kenschool.Model_POJOs;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "classes")
public class ClassPojo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int classId;

    @NotBlank(message = "Class name cannot be blank")
    @Size(min = 3, message = "Name must be atleast 3 character long")
    private String className;
    //    since the person and class is a bidirectional relation we do @onetomany here
//    Here the fetch type lazy coz we don't need to load the entire person in the class when we load the class details
//    The cascade type is persist coz we don't want to delete the peson when we are deleting the class
//    Also I feel when ever we save the class details we save the person details and that class id should be added in the person pojo classid field (FK)
//    Finally given the target entity in here coz the parent is the ClassPojo
    @OneToMany(mappedBy = "kenClass", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = PersonPojo.class)
    private Set<PersonPojo> persons;
}
