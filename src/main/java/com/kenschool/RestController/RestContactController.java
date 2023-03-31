package com.kenschool.RestController;

import com.kenschool.Constants.KenSchoolConstants;
import com.kenschool.ModelPojosRESTCall.ResponseEntityRest;
import com.kenschool.Model_POJOs.NewPOJOContactEntity;
import com.kenschool.Repository.NewContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController // If we are using the @RestController we don't need to use the @ResponseBody for each Method
//@Controller // For this annot we need the @ResponseBody
@RequestMapping(path = "/api/contact",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
// We added the produces attr inorder to produce 2 types of Responses XML and JSON as well That is this class can produce
// These kind of data as the response. So add a dependency too im POM.xml
@CrossOrigin(origins = "*")
public class RestContactController {
    @Autowired(required = false)
    NewContactRepository newContactRepository;

    @GetMapping(path = "/getMessages")
//    @ResponseBody //Commented since we are using the @RestController
    public List<NewPOJOContactEntity> getMessagesWithOpen(@RequestParam("status") String Status){
        return newContactRepository.findByStatus(Status);
    }

    @GetMapping(path = "/getAllMessagesWithOpenStat")
//    @ResponseBody // The use of this annot is to convert the Object into the Json Object so the the client app which can be of
                    // python, React, android can accept the data.
    public List<NewPOJOContactEntity> getAllOpenMessages(@RequestBody NewPOJOContactEntity contact){
//        if(null!=contact&&contact.getStatus()!=null)
//          return newContactRepository.findByStatus(contact.getStatus());
//        else
//          return List.of();
        return (null!=contact&&contact.getStatus()!=null)? newContactRepository.findByStatus(contact.getStatus()): List.of();
    }

//    This is the save message done by me
//    @PostMapping("/saveMessage")
//    public ResponseEntity<String> SaveMessages(@RequestHeader String invocationFrom,
//                                              @Valid @RequestBody NewPOJOContactEntity contactEntity){
////        log.info("The Header invocation form is \n"+invocationForm);
//        log.info(String.format("The Header invocation form is = %s ",invocationFrom)); // This invocationFrom attr is added by us
////invocationFrom.forEach(x->{
////System.out.println(x);});
////        in the header part of the postman request and rather than using the @RequestHeader and @RequestBody we can use the
////        @RequestEntity and can extract the body and header
//        newContactRepository.save(contactEntity);
////        Response response=new Response();
////        response.setStatus(200);
////        response.setS
//        return  ResponseEntity.status(HttpStatus.CREATED).
//                header("isMessageSaved", String.valueOf(true)).
//                body("The message saved successfully");
//    }

//    This is the save message that is slightly updated and advanced
    @PostMapping("/saveMessage")
    public ResponseEntity<ResponseEntityRest> SaveMessages(@RequestHeader String invocationFrom,
                                                           @Valid @RequestBody NewPOJOContactEntity contactEntity){

        log.info(String.format("The Header invocation form is = %s ",invocationFrom)); // This invocationFrom attr is added by us
//        in the header part of the postman request and rather than using the @RequestHeader and @RequestBody we can use the
//        @RequestEntity and can extract the body and header
        newContactRepository.save(contactEntity);
//        here we created a new POJO class ResponseEntityRest and added 2 fields and that is sending to the Response
        ResponseEntityRest response=new ResponseEntityRest();
        response.setStatusCode("200");
        response.setStatusMessage("The message is saved successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).
                header("isMessageSaved", String.valueOf(true)). // this header can be seen in the response part
                body(response);
    }

//    Implementing the delete Operation to delete a particular message
    @DeleteMapping(path = "/deleteMsg")
    public ResponseEntity<ResponseEntityRest> DeleteAMessage(RequestEntity<NewPOJOContactEntity> contactEntity){
        HttpHeaders headers=contactEntity.getHeaders();
        headers.forEach((key,value)->{
        log.info(String.format("Header '%s' = %s",key,value.stream().collect(Collectors.joining("|")))); // Or String.join("|", value)) can be used
        });

        NewPOJOContactEntity Contact_body=contactEntity.getBody();
        newContactRepository.deleteById(Contact_body.getContactId());

        ResponseEntityRest response=new ResponseEntityRest();
        response.setStatusMessage("The message deleted successfully");
        response.setStatusCode("200");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    Implementing the Update Operation for the REST services

    @PatchMapping(path = "/updateOpenMsgs") // We use the @PatchMapping to update the database values
    public ResponseEntity<ResponseEntityRest> UpdateOpenMessages(@RequestBody NewPOJOContactEntity contacts){
        ResponseEntityRest response=new ResponseEntityRest();
        Optional<NewPOJOContactEntity> contact=newContactRepository.findById(contacts.getContactId());
//        log.info(contact.get().getStatus()+" "+contact.get().getContactId());
        if (contact.isPresent()){
            contact.get().setStatus(KenSchoolConstants.CLOSE);
            newContactRepository.save(contact.get());
        }else{
            response.setStatusCode("400");
            response.setStatusMessage("Invalid ContactId received");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setStatusCode("200");
        response.setStatusMessage("Status Updated Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
 }
