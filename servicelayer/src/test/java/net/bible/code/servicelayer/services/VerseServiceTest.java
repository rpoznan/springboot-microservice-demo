package net.bible.code.servicelayer.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import net.bible.code.data.jpa.TorahBook;
import net.bible.code.data.jpa.TorahVerse;
import net.bible.code.data.repository.TorahVerseRepository;
import net.bible.code.servicelayer.contants.StatusCode;
import net.bible.code.servicelayer.pojo.BibleVerse;
import net.bible.code.servicelayer.pojo.BibleVerseRequest;
import net.bible.code.servicelayer.pojo.MyResponse;

public class VerseServiceTest {
	
	@InjectMocks
	private VerseService verseService;
	@Mock
    private TorahVerseRepository verseRepository;
	
	@Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testMin() {
		MyResponse <List<BibleVerse>> result = verseService.findBibleVerse(new BibleVerseRequest());
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	
	@Test
	public void testInvalid() {
		BibleVerseRequest req = new BibleVerseRequest();
		req.setBookId("abc");
		req.setChapter("abc");
		req.setVerseX("abc");
		req.setVerseY("abc");
		MyResponse <List<BibleVerse>> result = verseService.findBibleVerse(req);
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	
	private List<TorahVerse> createOne() {
		 List<TorahVerse> result = new  ArrayList<TorahVerse>();
		 TorahBook book = new TorahBook();
		 book.setId(1);
		 book.setBookName("testHebrew");
		 book.setEngBookName("testEnglish");
		 TorahVerse v = new TorahVerse();
		 v.setId(1);
		 v.setCreatedBy("x");
		 v.setCreatedDate(new java.util.Date());
		 v.setEngVerse("abceng");
		 v.setHChapter("a");
		 v.setHVerse("b");
		 v.setNumChapter(1);
		 v.setNumValue(1);
		 v.setNumVerse(1);
		 v.setTorahBook(book);
		 v.setTranVerse("abctrans");
		 v.setVerse("abchebrew");
		 result.add(v);
		return result;
	}
	
	private List<TorahVerse> createTwo() {
		 List<TorahVerse> result = new  ArrayList<TorahVerse>();
		 TorahBook book = new TorahBook();
		 book.setId(1);
		 book.setBookName("testHebrew");
		 book.setEngBookName("testEnglish");
		 TorahVerse v = new TorahVerse();
		 v.setId(1);
		 v.setCreatedBy("x");
		 v.setCreatedDate(new java.util.Date());
		 v.setEngVerse("abceng");
		 v.setHChapter("a");
		 v.setHVerse("b");
		 v.setNumChapter(1);
		 v.setNumValue(1);
		 v.setNumVerse(1);
		 v.setTorahBook(book);
		 v.setTranVerse("abctrans");
		 v.setVerse("abchebrew");
		 result.add(v);
		 v = new TorahVerse();
		 v.setId(2);
		 v.setCreatedBy("x");
		 v.setCreatedDate(new java.util.Date());
		 v.setEngVerse("2abceng");
		 v.setHChapter("2a");
		 v.setHVerse("2b");
		 v.setNumChapter(1);
		 v.setNumValue(1);
		 v.setNumVerse(2);
		 v.setTorahBook(book);
		 v.setTranVerse("2abctrans");
		 v.setVerse("2abchebrew");
		 result.add(v);
		return result;
	}
	
	@Test
	public void testSearchExact() {
		BibleVerseRequest req = new BibleVerseRequest();
		req.setBookId("1");
		req.setChapter("1");
		req.setVerseX("1");
		req.setVerseY(null);
		when(verseRepository.findVerse(any(Integer.class),any(Integer.class),any(Integer.class)))
        .thenReturn(createOne());
		MyResponse <List<BibleVerse>> result = verseService.findBibleVerse(req);
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.OK,result.getStatusCode());
		assertEquals(1,result.getPayload().size());
		for(BibleVerse v: result.getPayload()) {
			assertNotNull(v.getEngVerse());
			assertNotNull(v.getHChapter());
			assertNotNull(v.getHVerse());
			assertNotNull(v.getId());
			assertNotNull(v.getNumChapter());
			assertNotNull(v.getNumValue());
			assertNotNull(v.getNumVerse());
			assertNotNull(v.getTorahBook());
			assertNotNull(v.getTranVerse());
			assertNotNull(v.getVerse());
		}
	}
	
	@Test
	public void testSearchBetween() {
		BibleVerseRequest req = new BibleVerseRequest();
		req.setBookId("1");
		req.setChapter("1");
		req.setVerseX("1");
		req.setVerseY("1");
		when(verseRepository.findVerseBetween(any(Integer.class),any(Integer.class),any(Integer.class),any(Integer.class)))
        .thenReturn(createTwo());
		MyResponse <List<BibleVerse>> result = verseService.findBibleVerse(req);
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.OK,result.getStatusCode());
		assertEquals(2,result.getPayload().size());
		for(BibleVerse v: result.getPayload()) {
			assertNotNull(v.getEngVerse());
			assertNotNull(v.getHChapter());
			assertNotNull(v.getHVerse());
			assertNotNull(v.getId());
			assertNotNull(v.getNumChapter());
			assertNotNull(v.getNumValue());
			assertNotNull(v.getNumVerse());
			assertNotNull(v.getTorahBook());
			assertNotNull(v.getTranVerse());
			assertNotNull(v.getVerse());
		}
	}

}
