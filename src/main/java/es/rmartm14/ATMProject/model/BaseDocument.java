package es.rmartm14.ATMProject.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public class BaseDocument {

    @Id
    private String id;

    @CreatedDate
    private Instant creationDate;

    @LastModifiedDate
    private Instant modificationDate;
}
