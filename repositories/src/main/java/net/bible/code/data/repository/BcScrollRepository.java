package net.bible.code.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bible.code.data.jpa.BcScroll;

@Repository
public interface BcScrollRepository extends CrudRepository<BcScroll, Long> {
	
	@Query("from BcScroll as a " +
	" join fetch a.torahBook as book "+
	" left join fetch a.bcScrollLookups as l "+
	" left join fetch l.torahBook as book2 "+
	" left join fetch a.bcScrollImages as img "+
	" left join fetch a.bcScrollVerses as v "+
	" order by a.id")
	public List<BcScroll> findAllScrolls();
	
	@Query("from BcScroll as a " +
			" join fetch a.torahBook as book "+
			" left join fetch a.bcScrollLookups as l "+
			" left join fetch a.bcScrollImages as img "+
			" left join fetch a.bcScrollVerses as v "+
			"where a.id = :id")
	public List<BcScroll> findScrollById(Integer id);
	
}
