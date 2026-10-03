package com.rehan.enterprise.document;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface DocumentRepository extends JpaRepository<DocumentRecord,UUID>{}
