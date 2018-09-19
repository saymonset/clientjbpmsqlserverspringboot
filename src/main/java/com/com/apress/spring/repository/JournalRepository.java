package com.com.apress.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.apress.spring.domain.Journal;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by simon on 19/09/18.
 */
/*@Repository
@Transactional*/
public interface JournalRepository extends JpaRepository<Journal, Long> {
}
