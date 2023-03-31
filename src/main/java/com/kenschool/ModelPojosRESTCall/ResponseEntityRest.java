package com.kenschool.ModelPojosRESTCall;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEntityRest {
    private String StatusCode;
    private String StatusMessage;
}
