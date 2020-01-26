package net.bible.code.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bible.code.data.jpa.BcScrollVerse;

@Repository
public interface BcScrollVerseRepository extends CrudRepository<BcScrollVerse, Long> {

	@Query("delete from BcScrollVerse as a where a.bcScroll.id = :scrollId")
    void deleteUsingSingleQuery(Integer scrollId);
}
