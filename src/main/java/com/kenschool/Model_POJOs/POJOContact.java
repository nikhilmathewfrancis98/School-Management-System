//package com.kenschool.Model_POJOs;
//
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//import javax.validation.constraints.*;
//
///*
//@Data annotation is provided by Lombok library which generates getter, setter,
//equals(), hashCode(), toString() methods & Constructor at compile time.
//This makes our code short and clean.
//* */
//// This is a POJO class for the form values so be careful with the the variable name give the same name
//// that we given to the form input tag name
//@Data
//@Component
//public class POJOContact extends BaseEntity {
//    /*
//   * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
//     @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
//     @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
//   * */
//    private int contactId;
//
//
//    @NotBlank(message="Name must not be blank")
//    @Size(min=3, message="Name must be at least 3 characters long")
//    private String name;
//
//    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must contain 10 digits ")
//    @NotBlank(message="Mobile number must not be blank")
//    private String mobileNum;
//
//    @NotBlank(message="Email must not be blank")
//    @Email(message = "Enter a valid email address")
//    private  String email;
//
//    @NotBlank(message="Subject must not be blank")
//    @Size(min=5, message="Subject must be at least 5 characters long")
//    private String subject;
//
//    @NotBlank(message="Message must not be blank")
//    @Size(min=10, message="Message must be at least 10 characters long")
//    private  String message;
//    private String status;
//}
