package net.bible.code.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bible.code.data.jpa.JpsTanakh;

@Repository
public interface JpsTanakhRepository extends CrudRepository<JpsTanakh, Long> {

}
