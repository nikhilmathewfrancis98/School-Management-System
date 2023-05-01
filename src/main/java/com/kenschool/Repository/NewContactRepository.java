package com.kenschool.Repository;

import com.kenschool.Model_POJOs.NewPOJOContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@RepositoryRestResource(path = "courses")
public interface NewContactRepository extends JpaRepository<NewPOJOContactEntity, Integer> {
    List<NewPOJOContactEntity> findByStatus(String open);
//    @Query(value = "Select c from NewPOJOContactEntity c where c.status=?1") // Here we can use the =:status where this status is the argument
   @Query(value = "SELECT * FROM contact_msg  where status = ?1",nativeQuery = true) // the native query is exactly like the sql query and this got nothing to do with jpa
    Page<NewPOJOContactEntity> findStatus(String status,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update NewPOJOContactEntity c set c.status=:status where c.contactId=:id ")// JPQL query corresponding for native query do like  @Query("update contact_msg  set status=:status where contact_id=?2 ")
    int updateStatus(@Param("status") String status,int id);

//    For updating the code with the @NamedQuery and @NamedNativeQuery code starts from here

    Page<NewPOJOContactEntity> findOpenMsgs(String status,Pageable pageable); // @NamedQuery related code in Repo class

    @Transactional
    @Modifying
    int updateMsgStatus(@Param("status") String status,int id); // @NamedQuery related code in Repo class

//    Methods for @NamedNativeQuery

    @Query(nativeQuery = true)
    Page<NewPOJOContactEntity> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);

}
