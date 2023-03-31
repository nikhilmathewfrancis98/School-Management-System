package com.kenschool.Model_POJOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Component
@Table(name = "contact_msg") // This is not required if the class name and the table name is same
// Code for adding the Named and NamedNative queries
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
        @NamedQuery(name = "NewPOJOContactEntity.findOpenMsgs",
                query = "SELECT c FROM NewPOJOContactEntity c WHERE c.status = :status"),
        @NamedQuery(name = "NewPOJOContactEntity.updateMsgStatus",
                query = "UPDATE NewPOJOContactEntity c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "NewPOJOContactEntity.findOpenMsgsNative",
                query = "SELECT * FROM contact_msg  WHERE status = :status"
                ,resultClass = NewPOJOContactEntity.class),
        @NamedNativeQuery(name = "NewPOJOContactEntity.findOpenMsgsNative.count",
                query = "select count(*) as cnt from contact_msg  where status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        /*Spring Data JPA doesn’t support dynamic sorting for native queries.
        Doing that would require Spring Data to analyze the provided statement and generate
        the ORDER BY clause in the database-specific dialect. This would be a very complex operation
        and is currently not supported by Spring Data JPA.*/
        @NamedNativeQuery(name = "NewPOJOContactEntity.updateMsgStatusNative",
                query = "UPDATE contact_msg  SET status = ?1 WHERE contact_id = ?2")
})
public class NewPOJOContactEntity extends BaseEntity {
    // Note : here we are extending the BaseEntity so it also has some fields that we use so that class also need to be considered so add a annotation there

    @Id // Specifies the field is primary in nature
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO) //or GenerationType.SEQUENCE can be given
    @GenericGenerator(name = "native", strategy = "native") // to state that autoGenerate in native method
    @Column(name = "contact_id")
    private int contactId; // Here we don't need @column coz spring will remove the _ and the case so it
    // Look exactly like contactid will be == to contactId spring data jpa does this

    // All the below fields are exactly same as the db entities so no need of @Column
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @JsonProperty(value = "Person_name") // This annot helps to change the name of the field when sending the Json Response
    private String name;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must contain 10 digits ")
    @NotBlank(message = "Mobile number must not be blank")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;
    private String status;
}
