package net.bible.code.servicelayer.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.bible.code.data.jpa.TorahBook;
import net.bible.code.data.jpa.TorahVerse;
import net.bible.code.data.repository.TorahVerseRepository;
import net.bible.code.servicelayer.contants.StatusCode;
import net.bible.code.servicelayer.pojo.BibleVerse;
import net.bible.code.servicelayer.pojo.BibleVerseRequest;
import net.bible.code.servicelayer.pojo.Book;
import net.bible.code.servicelayer.pojo.MyResponse;
import net.bible.code.servicelayer.utils.Utils;

@Service
@Slf4j
public class VerseService {
	
	@Autowired
	private TorahVerseRepository verseRepository;

	public MyResponse <List<BibleVerse>> findBibleVerse(BibleVerseRequest request) {
		MyResponse<List<BibleVerse>> response = new MyResponse();
		try {
		response.setStatusCode(StatusCode.VALIDATION_ERROR);
		Set<ConstraintViolation<BibleVerseRequest>> errorsList = Utils.getValidator().validate(request);
        List<String> errorMessages2 = errorsList.stream().map(error -> error.getMessage()).collect(Collectors.toList());
        if (errorMessages2 != null && !errorMessages2.isEmpty()) {
            String errorMessage = errorMessages2.stream().collect(Collectors.joining(","));
            response.setErrorMessage(errorMessage);
            return response;
        }
        List<TorahVerse> verseList = request.getVerseY() != null ? 
        		verseRepository.findVerseBetween(Utils.toInteger(request.getBookId()), Utils.toInteger(request.getChapter()), 
        				Utils.toInteger(request.getVerseX()), Utils.toInteger(request.getVerseY())) :
        					verseRepository.findVerse(Utils.toInteger(request.getBookId()), Utils.toInteger(request.getChapter()),
        							Utils.toInteger(request.getVerseX()));
        
        		List<BibleVerse> scrollList2 = verseList.stream().map(p -> {
        			BibleVerse bv = toBibleVerse(p);
        					return bv;
        			    })
        			    .collect(Collectors.toList());
		response.setPayload(scrollList2);
    	response.setStatusCode(StatusCode.OK);
        response.setErrorMessage("ok");
    	} catch(Throwable t) {
			response.setStatusCode(StatusCode.DATABASE_EXCEPTION);
			response.setErrorMessage(t.getMessage());
			log.error("Error in findScroll", t);
		}
		return response;
	}
	
	private BibleVerse toBibleVerse(TorahVerse vers) {
		BibleVerse x = new BibleVerse();
		x.setEngVerse(vers.getEngVerse());
		x.setHChapter(vers.getHChapter());
		x.setHVerse(vers.getHVerse());
		x.setId(vers.getId());
		x.setNumChapter(vers.getNumChapter());
		x.setNumValue(vers.getNumValue());
		x.setNumVerse(vers.getNumVerse());
		x.setTorahBook(toBook(vers.getTorahBook()));
		x.setTranVerse(vers.getTranVerse());
		x.setVerse(vers.getVerse());
		return x;
	}
	
	private Book toBook(TorahBook book) {
		Book x = new Book();
		x.setBookName(book.getBookName());
		x.setEngBookName(book.getEngBookName());
		x.setId(book.getId());
		return x;
	}
	
}
