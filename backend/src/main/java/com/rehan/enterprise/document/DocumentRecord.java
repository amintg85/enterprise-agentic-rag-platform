package com.rehan.enterprise.document;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="documents")
public class DocumentRecord {
 @Id private UUID id=UUID.randomUUID();
 private String filename;
 private String contentType;
 private long sizeBytes;
 private String status;
 private Instant createdAt=Instant.now();
 protected DocumentRecord(){}
 public DocumentRecord(String f,String c,long s,String status){filename=f;contentType=c;sizeBytes=s;this.status=status;}
 public UUID getId(){return id;} public String getFilename(){return filename;}
 public String getContentType(){return contentType;} public long getSizeBytes(){return sizeBytes;}
 public String getStatus(){return status;} public Instant getCreatedAt(){return createdAt;}
}
