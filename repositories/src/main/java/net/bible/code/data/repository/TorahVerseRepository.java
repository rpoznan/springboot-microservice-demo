package net.bible.code.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bible.code.data.jpa.TorahVerse;

@Repository
public interface TorahVerseRepository extends CrudRepository<TorahVerse, Long> {

	
	@Query("from TorahVerse as a " +
			" join fetch a.torahBook as book "+
			"where book.id = :bookId and a.numChapter = :chapter and a.numVerse = :verse")
	public List<TorahVerse> findVerse(Integer bookId, Integer chapter, Integer verse);
	
	@Query("from TorahVerse as a " +
			" join fetch a.torahBook as book "+
			"where book.id = :bookId and a.numChapter = :chapter and a.numVerse between :versex and :versey")
	public List<TorahVerse> findVerseBetween(Integer bookId, Integer chapter, Integer versex, Integer versey);

	
	
}
