package com.kenschool.Services;


import com.kenschool.Constants.KenSchoolConstants;
import com.kenschool.Model_POJOs.NewPOJOContactEntity;
import com.kenschool.Repository.NewContactRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Data
@Slf4j
@ApplicationScope
@Service(value = "ServicesContact")
public class NewContactServices {
    @Autowired
    private NewContactRepository contactRepository;
    private int counter = 0;

    public NewContactServices() {
        System.out.println("The ContactService Bean is Initialized");
    }

    public boolean SaveMessageContacts(NewPOJOContactEntity pojoContact) { // The return statement can be void also
//       boolean isSaved;
//        log.info("Inside the service Layer");
//        log.info( pojoContact.toString());
//
//        if(null != pojoContact.getName()){
//            return true;
//        }
//      else {
//          return false;
//        }
        boolean isSaved = false;
        pojoContact.setStatus(KenSchoolConstants.OPEN);
//        pojoContact.setCreatedBy(KenSchoolConstants.ANONYMOUS); //  Commented because we are using the Auditing this will be automatically updated by the Spring Date JPA
//        pojoContact.setCreatedAt(LocalDateTime.now());
        NewPOJOContactEntity pojoContactResult = contactRepository.save(pojoContact);
        if (null != pojoContactResult && pojoContactResult.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<NewPOJOContactEntity> findOpenMessages() {
        List<NewPOJOContactEntity> contactmsgs = contactRepository.findByStatus(KenSchoolConstants.OPEN);
        return contactmsgs;
    }

    // For using the UpdateMessageStatus we are updating the row with new value
    // now get the current values of that particular row and add new value and add it to the database
    public boolean UpdateMessageStatus(int contactId) {

        Optional<NewPOJOContactEntity> resultset = contactRepository.findById(contactId);
        resultset.ifPresent(result -> {
                    result.setStatus(KenSchoolConstants.CLOSE);
//        result.setUpdatedBy(name); //  Commented because we are using the Auditing this will be automatically updated by the Spring Date JPA
//        result.setUpdatedAt(LocalDateTime.now());
                }
        );
        boolean isUpdated = true;
        NewPOJOContactEntity UpdatedContactEntity = contactRepository.save(resultset.get());
        if (null != UpdatedContactEntity && UpdatedContactEntity.getContactId() > 0) {
            return isUpdated;
        } else {
            return false;
        }
    }
}
