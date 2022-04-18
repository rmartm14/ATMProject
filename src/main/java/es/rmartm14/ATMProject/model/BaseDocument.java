package es.rmartm14.ATMProject.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

/**
 * Base document class to save on mongo
 *
 * @author Rafael Martinez
 */
public class BaseDocument implements Serializable {

    @Id
    private String id;

    @CreatedDate
    private Instant creationDate;

    @LastModifiedDate
    private Instant modificationDate;
}
