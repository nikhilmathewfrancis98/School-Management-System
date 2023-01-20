package com.kenschool.Model_POJOs;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "address")
@Data
public class AddressPoJO extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int addressId;

    @Lob
    @Column(name = "profile_pic", columnDefinition = "BLOB", nullable = true)
    private byte[] profilePic;

    @NotBlank(message = "Address1 field must not be blank")
    @Size(min = 5, message = "Address1 must be at least 5 characters long")
    private String address1;


    private String address2;


    @NotBlank(message = "City must not be blank")
    @Size(min = 4, message = "city must be at least 4 characters long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 4, message = "State must be at least 4 characters long")
    private String State;

    @NotBlank(message = "Zip_code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip Code must be 5 digits")
    private String Zip_code;


}
