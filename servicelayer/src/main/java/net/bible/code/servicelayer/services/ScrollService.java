package net.bible.code.servicelayer.services;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.bible.code.data.jpa.BcScroll;
import net.bible.code.data.jpa.BcScrollImage;
import net.bible.code.data.jpa.BcScrollLookup;
import net.bible.code.data.jpa.BcScrollVerse;
import net.bible.code.data.jpa.TorahBook;
import net.bible.code.data.repository.BcScrollImageRepository;
import net.bible.code.data.repository.BcScrollLookupRepository;
import net.bible.code.data.repository.BcScrollRepository;
import net.bible.code.data.repository.BcScrollVerseRepository;
import net.bible.code.servicelayer.contants.StatusCode;
import net.bible.code.servicelayer.pojo.Book;
import net.bible.code.servicelayer.pojo.MyResponse;
import net.bible.code.servicelayer.pojo.Scroll;
import net.bible.code.servicelayer.pojo.ScrollImage;
import net.bible.code.servicelayer.pojo.ScrollLookup;
import net.bible.code.servicelayer.utils.Utils;

@Service
@Slf4j
public class ScrollService {
	
	@Autowired
	private BcScrollRepository scrollRepository;
	@Autowired
	private BcScrollImageRepository scrollImageRepository;
	@Autowired
	private BcScrollLookupRepository scrollLookupRepository;
	@Autowired
	private BcScrollVerseRepository scrollVerseRepository;
	
	private Scroll convertBcScroll(BcScroll scroll) {
		Scroll x = new Scroll();
		x.setAge(scroll.getAge());
		x.setId(scroll.getId());
		x.setImageUrl(scroll.getImageUrl());
		x.setImageUrl2(scroll.getImageUrl2());
		x.setMaterial(scroll.getMaterial());
		x.setOrgin(scroll.getOrgin());
		List<ScrollImage> imageList = scroll.getBcScrollImages().stream().map(p -> {
			ScrollImage img = new ScrollImage();
			img.setId(p.getId());
			img.setImageUrl(p.getImageUrl());
			return img;
			    })
			    .collect(Collectors.toList());
		x.setImageUrls(imageList);
		List<ScrollLookup> lookupList = scroll.getBcScrollLookups().stream().map(p -> {
			ScrollLookup y = new ScrollLookup();
			y.setChapter(p.getChapter());
			y.setCrossRef(p.getCrossRef());
			y.setId(p.getId());
			y.setTorahBook(toBook(p.getTorahBook()));
			y.setVerseX(p.getVerseX());
			y.setVerseY(p.getVerseY());
			return y;
			    })
			    .collect(Collectors.toList());
		x.setScrollLookups(lookupList);
		x.setTitle(scroll.getTitle());
		x.setTorahBook(toBook(scroll.getTorahBook()));
		x.setVerseAddress(scroll.getVerseAddress());
		return x;
	}
	
	private Book toBook(TorahBook book) {
		Book x = new Book();
		x.setBookName(book.getBookName());
		x.setEngBookName(book.getEngBookName());
		x.setId(book.getId());
		return x;
	}
	
	@Transactional
	public MyResponse<List<Scroll>> findScroll(Integer scrollId) {
		MyResponse<List<Scroll>> response = new MyResponse(); 
    	try {
		List<BcScroll> scrollList = scrollRepository.findScrollById(scrollId);
		List<Scroll> scrollList2 = scrollList.stream().map(p -> {
			Scroll scroll =  convertBcScroll(p);
					return scroll;
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
	
	@Transactional
    public MyResponse<List<Scroll>> findAllScroll() {
    	MyResponse<List<Scroll>> response = new MyResponse(); 
    	try {
    	List<BcScroll> scrollList = scrollRepository.findAllScrolls();
    	List<Scroll> scrollList2 = scrollList.stream().map(p -> {
			Scroll scroll =  convertBcScroll(p);
					return scroll;
			    })
			    .collect(Collectors.toList());
    	response.setPayload(scrollList2);
    	response.setStatusCode(StatusCode.OK);
        response.setErrorMessage("ok");
    	} catch(Throwable t) {
			response.setStatusCode(StatusCode.DATABASE_EXCEPTION);
			response.setErrorMessage(t.getMessage());
			log.error("Error in findAllScroll", t);
		}
		return response;
	}
	
	@Transactional
	public MyResponse<String> saveScroll(Scroll scroll, String username) {
		MyResponse<String> response = validateScroll(scroll);
		if(response.getStatusCode() != StatusCode.OK) {
			return response;
		}
		try {
		
        Date now = new Date();
        BcScroll scrollX = new BcScroll();
        if(scroll.getId() == null) {
        	scrollX.setCreatedBy(username);
        	scrollX.setCreatedDate(now);
        } else {
            scrollX.setModifiedBy(username);
            scrollX.setModifiedDate(now);
        }
        scrollX.setId(scroll.getId());
        scrollX.setImageUrl(scroll.getImageUrl());
        scrollX.setImageUrl2(scroll.getImageUrl2());
        scrollX.setMaterial(scroll.getMaterial());
        scrollX.setOrgin(scroll.getOrgin());
        scrollX.setTitle(scroll.getTitle());
        TorahBook book = new TorahBook();
        book.setId(scroll.getTorahBook().getId());
        scrollX.setTorahBook(book);
        scrollX.setVerseAddress(scroll.getVerseAddress());
        scrollRepository.save(scrollX);
        scrollVerseRepository.deleteUsingSingleQuery(scrollX.getId());
        scroll.getScrollLookups().stream().forEach(sl -> {
        	BcScrollLookup bsl = new BcScrollLookup();
        	bsl.setBcScroll(scrollX);
        	bsl.setChapter(sl.getChapter()); 
        	if(sl.getId() != null) {
        	bsl.setCreatedBy(username);
        	bsl.setCreatedDate(now);
        	} else {
        	bsl.setModifiedBy(username);
        	bsl.setModifiedDate(now);
        	}
        	bsl.setCrossRef(sl.getCrossRef());
        	bsl.setId(sl.getId());
        	TorahBook book2 = new TorahBook();
            book2.setId(sl.getTorahBook().getId());
        	bsl.setTorahBook(book2);
        	bsl.setVerseX(sl.getVerseX());
        	bsl.setVerseY(sl.getVerseY());
        	scrollLookupRepository.save(bsl);
        	for(int i=sl.getVerseX(); i <= sl.getVerseY(); i++) {
        		BcScrollVerse verse = new BcScrollVerse();
        		verse.setChapter(sl.getChapter());
        		verse.setCreatedBy(username);
        		verse.setCreatedDate(now);
        		verse.setTorahBook(book2);
        		verse.setVerse(i);
        	    scrollVerseRepository.save(verse);
        	}
        });
        scroll.getImageUrls().stream().forEach(img -> {
        	BcScrollImage simg = new BcScrollImage();
        	simg.setBcScroll(scrollX);
        	if(img.getId() == null) {
        	simg.setCreatedBy(username);
        	simg.setCreatedDate(now);
        	} else {
        	simg.setModifiedBy(username);
        	simg.setModifiedDate(now);
        	}
        	simg.setId(img.getId());
        	simg.setImageUrl(img.getImageUrl());
        	scrollImageRepository.save(simg);
        });
        response.setStatusCode(StatusCode.OK);
        response.setErrorMessage("ok");
        response.setPayload(scrollX.getId()+"");
		} catch(Throwable t) {
			response.setStatusCode(StatusCode.DATABASE_EXCEPTION);
			response.setErrorMessage(t.getMessage());
			log.error("Error in saveScroll", t);
		}
		return response;
	}
 
	private MyResponse<String> validateScroll(Scroll scroll) {
		MyResponse<String> response = new MyResponse();
		response.setStatusCode(StatusCode.VALIDATION_ERROR);
		Set<ConstraintViolation<Scroll>> errorsList = Utils.getValidator().validate(scroll);
        List<String> errorMessages2 = errorsList.stream().map(error -> error.getMessage()).collect(Collectors.toList());
        if (errorMessages2 != null && !errorMessages2.isEmpty()) {
            String errorMessage = errorMessages2.stream().collect(Collectors.joining(","));
            response.setErrorMessage(errorMessage);
            return response;
        }
        if(scroll.getTorahBook().getId() < 1 || scroll.getTorahBook().getId() > 66) {
        	response.setErrorMessage("invalid book id");
            return response;
        }
        if(scroll.getScrollLookups().size() == 0) {
        	response.setErrorMessage("scroll lookups required");
            return response;
        }
        if(scroll.getImageUrls().size() == 0) {
        	response.setErrorMessage("images are required");
            return response;
        }
        response.setStatusCode(StatusCode.OK);
        response.setErrorMessage("ok");
        return response;
	}

}
