package com.kenschool.Model_POJOs;

import com.kenschool.ValidationAnnotation.FiledValueMatch;
import com.kenschool.ValidationAnnotation.PasswordValidator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Slf4j
@Entity
@Table(name = "person")
// Since we are using the fieldValueMatch here (spell is not correct) we don't need to specify it in the field
// Remoce this and add it on top of fields if needed  and add attributes like field field match message
@FiledValueMatch.List({
        @FiledValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FiledValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
public class PersonPojo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int personId;


    @NotNull(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must contain 10 digits ")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address")
    @Transient
    private String confirmEmail;

    @PasswordValidator
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @NotBlank(message = "Password must not be blank")
    private String pwd;

    //    @PasswordValidator
    @NotNull
    @NotBlank(message = "Confirm password must not be blank")
    @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
    @Transient // Since we don't need this confirmPwd to get stored into the db we use this annot
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = AddressPoJO.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private AddressPoJO address;

    //    Here the fetch type is lazy coz we can give Eager also but it has nothing to do, lazy here  is when ever load person we can either load class or not
//    We are not giving the cascade here coz we don't want to store the class details w.r.t person, else we are storing the class first then saving the person details
//
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private ClassPojo kenClass;
}
