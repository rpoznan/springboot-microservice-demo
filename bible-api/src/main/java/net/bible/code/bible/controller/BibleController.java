package net.bible.code.bible.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bible.code.servicelayer.pojo.BibleVerse;
import net.bible.code.servicelayer.pojo.BibleVerseRequest;
import net.bible.code.servicelayer.pojo.MyResponse;
import net.bible.code.servicelayer.services.VerseService;

@RestController
public class BibleController {
	
	@Autowired
	private VerseService verseService;

    @PostMapping(value = "/api/verselookup")
    public ResponseEntity<?> getConfiguration(@RequestBody BibleVerseRequest request) {
    	MyResponse <List<BibleVerse>> result = verseService.findBibleVerse(request);
    	return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }

}
