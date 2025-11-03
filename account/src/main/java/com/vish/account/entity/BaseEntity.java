package com.vish.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass //This is going to tell jpa that it is going to act as superclass for my entities where I'm going to extend this baseEntity class.
@Getter @Setter  //when we mention these annotation behind the scene lambok library is going to generate
@ToString // /Getter & setter method and toString method
@EntityListeners(AuditingEntityListener.class)//This class is present inside spring data jpa framework
public class BaseEntity {
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreatedBy
    private  String createdBy;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    @LastModifiedBy
    private  String updatedBy;

    /*
      @Column(updatable=false) -->whenever a record is updated inside the database table , we don't want this
       column to be considered by spring data jpa to populate the value and to update . It means this field is
       not updated wherever we're trying to update our record.

       @Column(insertable=false)  --> it is telling to not populate or update these column whenever it is trying
        to insert a very new record inside the database
     */
  /*
  With help of Spring data jpa we can update meta data column automatically which means we can give responsibility
  to spring data jpa because anyway the framework is going to take care of creating all the insert script,update script,
  select and delete script
  Spring Data JPA also support auditing features
   */
}
