//package com.kenschool.Services;
//
//import com.kenschool.Constants.KenSchoolConstants;
//import com.kenschool.Model_POJOs.POJOContact;
//import com.kenschool.Repository.ContactRepository;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.annotation.ApplicationScope;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.annotation.SessionScope;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//@Slf4j
////Since i given the sessionScope the counter will be incremented ie same instance throughout the session
////@SessionScope
////We are using the @ApplicationScope so that the instance will be same in the entire application no matter we use different browser the instance will be sane
//@ApplicationScope
////@RequestScope
//@Service(value = "ServicesContact")
//public class ContactServices {
//@Autowired
//private ContactRepository contactRepository;
//private int counter=0;
//    public boolean SaveMessageContacts(POJOContact pojoContact){ // The return statement can be void also
////       boolean isSaved;
////        log.info("Inside the service Layer");
////        log.info( pojoContact.toString());
////
////        if(null != pojoContact.getName()){
////            return true;
////        }
////      else {
////          return false;
////        }
//        boolean isSaved = false;
//        pojoContact.setStatus(KenSchoolConstants.OPEN);
//        pojoContact.setCreatedBy(KenSchoolConstants.ANONYMOUS);
//        pojoContact.setCreatedAt(LocalDateTime.now());
//        int result = contactRepository.SaveContactMsg(pojoContact);
//        if(result>0) {
//            isSaved = true;
//        }
//        return isSaved;
//    }
//  public ContactServices(){
//        System.out.println("The ContactService Bean is Initialized");
//    }
//
//    public List<POJOContact> findOpenMessages() {
//        List<POJOContact> contactmsgs=contactRepository.findMsgsWithStatus(KenSchoolConstants.OPEN);
//        return contactmsgs;
//    }
//
//    public boolean UpdateMessageStatus(int contactId, String name) {
//        boolean isUpdated=true;
//        int result=contactRepository.setstatusUpdate(contactId,name,KenSchoolConstants.CLOSE);
//        if(result>0) {
//            return isUpdated;
//        }else{
//            return false;}
//    }
//}
