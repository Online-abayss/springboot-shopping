package com.example.springboot_shopping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})//1
@MappedSuperclass//2
@Getter @Setter
public abstract class BaseTimeEntity {

    @CreatedDate//3
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate //4
    private LocalDateTime updateTime;

}
