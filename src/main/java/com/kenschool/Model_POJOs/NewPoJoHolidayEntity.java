package com.kenschool.Model_POJOs;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@Table(name = "holidays")
public class NewPoJoHolidayEntity extends BaseEntity {
    @Id
    @Column(name = "holiday")
    private String day;
    private String reason;
    //    public Type getType() {
//        return type;
//    }
    @Enumerated(EnumType.STRING)
    private Type type;
//    private  String type;

    public enum Type {
        FEDERAL, FESTIVAL
    }
}
