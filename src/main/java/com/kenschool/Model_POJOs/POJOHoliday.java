//package com.kenschool.Model_POJOs;
////Since we where using the final for the fields we need Constructor to initialize the fields
//// No setters can be given to initialize the fields even if we comment the constructor
//// If we are using lombok the constructor is initialized automatically
//
//import lombok.*;
//
//import java.sql.ResultSet;
//
//
//// @Data can be used instead of this which is a whole package of these lombok annotations
////@NoArgsConstructor Cannot use this since we have final fields  and cannot annotate with @Component
////@AllArgsConstructor Since we don't need this 3 annot any more because we are using the repository
////@Getter
////@Setter
////@ToString
//@Data
//public class POJOHoliday extends BaseEntity{
//
//    public void setType(String type) {
//        this.type=Type.valueOf(type.toUpperCase());
//    }
//
//    public enum Type{
//        FEDERAL,FESTIVAL
//    }
//    private  String day;
//    private  String reason;
////    private  String type;
//
////    public Type getType() {
////        return type;
////    }
//
//    private Type type;
//
//}
