package net.bible.code.servicelayer.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.bible.code.data.jpa.BcScroll;
import net.bible.code.data.jpa.BcScrollImage;
import net.bible.code.data.jpa.BcScrollLookup;
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

public class ScrollServiceTest {
	
	@InjectMocks
	private ScrollService scrollService;
	@Mock
	private BcScrollRepository scrollRepository;
	@Mock
	private BcScrollLookupRepository scrollLookupRepository;
	@Mock
	private BcScrollVerseRepository scrollVerseRepository;
	@Mock
	private BcScrollImageRepository scrollImageRepository;
	
	@Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testMin() {
		MyResponse<String> result = scrollService.saveScroll(new Scroll(), "test");
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	@Test
	public void testMin2() {
		Scroll x = new Scroll();
		x.setAge("50");
		x.setId(1);
		x.setImageUrl("/test/p14.jpg");
		x.setImageUrl2("/test/p15.jpg");
		x.setImageUrls(Collections.emptyList());
		x.setMaterial("deer skin");
		x.setOrgin("yemen");
		x.setScrollLookups(Collections.emptyList());
		x.setTitle("test parsha");
		Book book = new Book();
		book.setId(null);
		x.setTorahBook(book);
		x.setVerseAddress("Test 1:1");
		MyResponse<String> result = scrollService.saveScroll(x, "test");
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	@Test
	public void testMin3() {
		Scroll x = new Scroll();
		x.setAge("50");
		x.setId(1);
		x.setImageUrl("/test/p14.jpg");
		x.setImageUrl2("/test/p15.jpg");
		x.setImageUrls(Collections.emptyList());
		x.setMaterial("deer skin");
		x.setOrgin("yemen");
		x.setScrollLookups(new ArrayList<ScrollLookup>());
		x.getScrollLookups().add(new ScrollLookup());
		x.setTitle("test parsha");
		Book book = new Book();
		book.setId(1);
		x.setTorahBook(book);
		x.setVerseAddress("Test 1:1");
		MyResponse<String> result = scrollService.saveScroll(x, "test");
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	
	@Test
	public void testMin4() {
		Scroll x = new Scroll();
		x.setAge("50");
		x.setId(1);
		x.setImageUrl("/test/p14.jpg");
		x.setImageUrl2("/test/p15.jpg");
		x.setImageUrls(Collections.emptyList());
		x.setMaterial("deer skin");
		x.setOrgin("yemen");
		Book book = new Book();
		book.setId(1);
		x.setScrollLookups(new ArrayList<ScrollLookup>());
		ScrollLookup y = new ScrollLookup();
		y.setChapter(1);
		y.setCrossRef(null);
		y.setTorahBook(book);
		y.setVerseX(1);
		y.setVerseY(1);
		x.getScrollLookups().add(y);
		x.setTitle("test parsha");
		x.setTorahBook(book);
		x.setVerseAddress("Test 1:1");
		MyResponse<String> result = scrollService.saveScroll(x, "test");
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	
	@Test
	public void testMin5() {
		Scroll x = new Scroll();
		x.setAge("50");
		x.setId(1);
		x.setImageUrl("/test/p14.jpg");
		x.setImageUrl2("/test/p15.jpg");
		x.setImageUrls(new ArrayList<ScrollImage>());
		x.getImageUrls().add(new ScrollImage());
		x.setMaterial("deer skin");
		x.setOrgin("yemen");
		Book book = new Book();
		book.setId(1);
		x.setScrollLookups(new ArrayList<ScrollLookup>());
		ScrollLookup y = new ScrollLookup();
		y.setChapter(1);
		y.setCrossRef(null);
		y.setTorahBook(book);
		y.setVerseX(1);
		y.setVerseY(1);
		x.getScrollLookups().add(y);
		x.setTitle("test parsha");
		x.setTorahBook(book);
		x.setVerseAddress("Test 1:1");
		MyResponse<String> result = scrollService.saveScroll(x, "test");
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.VALIDATION_ERROR,result.getStatusCode());
	}
	
	@Test
	public void testSave() {
		Scroll x = new Scroll();
		x.setAge("50");
		x.setId(1);
		x.setImageUrl("/test/p14.jpg");
		x.setImageUrl2("/test/p15.jpg");
		x.setImageUrls(new ArrayList<ScrollImage>());
		ScrollImage a = new ScrollImage();
		a.setImageUrl("http://www/test/tst.jpg");
		x.getImageUrls().add(a);
		x.setMaterial("deer skin");
		x.setOrgin("yemen");
		Book book = new Book();
		book.setId(1);
		x.setScrollLookups(new ArrayList<ScrollLookup>());
		ScrollLookup y = new ScrollLookup();
		y.setChapter(1);
		y.setCrossRef(null);
		y.setTorahBook(book);
		y.setVerseX(1);
		y.setVerseY(30);
		x.getScrollLookups().add(y);
		x.setTitle("test parsha");
		x.setTorahBook(book);
		x.setVerseAddress("Test 1:1");
		MyResponse<String> result = scrollService.saveScroll(x, "test");
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.OK,result.getStatusCode());
	}
	
	private List<BcScroll> createOne() {
		 List<BcScroll> scrollList = new ArrayList<BcScroll>();
		 BcScroll sc = new BcScroll();
		 sc.setId(1);
		 sc.setImageUrl("/img/pc/pfg56.jpg");
		 sc.setImageUrl2("/img/pc/achj78.jpg");
		 sc.setMaterial("parchment");
		 sc.setModifiedBy("test");
		 sc.setModifiedDate(new java.util.Date());
		 sc.setCreatedBy("test");
		 sc.setCreatedDate(new java.util.Date());
		 sc.setOrgin("israel");
		 sc.setTitle("test scroll");
		 sc.setAge("200");
		 TorahBook book = new TorahBook();
		 book.setId(1);
		 book.setBookName("Vikrah");
		 book.setEngBookName("Leviticus");
		 sc.setTorahBook(book);
		 sc.setVerseAddress("Lev 4:10-9:20");
		 sc.setBcScrollImages(new HashSet<BcScrollImage>());
		 sc.setBcScrollLookups(new HashSet<BcScrollLookup>());
		 BcScrollImage img = new BcScrollImage();
		 img.setId(1);
		 img.setImageUrl("/gvh/yyu/ytr678.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 img = new BcScrollImage();
		 img.setId(2);
		 img.setImageUrl("/gvh/yyu/ytr679.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 img = new BcScrollImage();
		 img.setId(3);
		 img.setImageUrl("/gvh/yyu/ytr680.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 BcScrollLookup lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(4);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(1);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(30);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(5);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(2);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(22);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(6);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(3);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(46);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(9);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef("Y");
		 lk.setId(4);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 book = new TorahBook();
		 book.setId(53);
		 book.setBookName("Yehudi");
		 book.setEngBookName("Hebrewss");
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(20);
		 sc.getBcScrollLookups().add(lk);
		 scrollList.add(sc);
		return scrollList;
	}
	
	private List<BcScroll> createTwo() {
		 List<BcScroll> scrollList = new ArrayList<BcScroll>();
		 BcScroll sc = new BcScroll();
		 sc.setId(1);
		 sc.setImageUrl("/img/pc/pfg56.jpg");
		 sc.setImageUrl2("/img/pc/achj78.jpg");
		 sc.setMaterial("parchment");
		 sc.setModifiedBy("test");
		 sc.setModifiedDate(new java.util.Date());
		 sc.setCreatedBy("test");
		 sc.setCreatedDate(new java.util.Date());
		 sc.setOrgin("israel");
		 sc.setTitle("test scroll");
		 sc.setAge("200");
		 TorahBook book = new TorahBook();
		 book.setId(1);
		 book.setBookName("Vikrah");
		 book.setEngBookName("Leviticus");
		 sc.setTorahBook(book);
		 sc.setVerseAddress("Lev 4:10-9:20");
		 sc.setBcScrollImages(new HashSet<BcScrollImage>());
		 sc.setBcScrollLookups(new HashSet<BcScrollLookup>());
		 BcScrollImage img = new BcScrollImage();
		 img.setId(1);
		 img.setImageUrl("/gvh/yyu/ytr678.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 img = new BcScrollImage();
		 img.setId(2);
		 img.setImageUrl("/gvh/yyu/ytr679.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 img = new BcScrollImage();
		 img.setId(3);
		 img.setImageUrl("/gvh/yyu/ytr680.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 BcScrollLookup lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(4);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(1);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(30);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(5);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(2);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(22);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(6);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(3);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(46);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(9);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef("Y");
		 lk.setId(4);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 book = new TorahBook();
		 book.setId(53);
		 book.setBookName("Yehudi");
		 book.setEngBookName("Hebrewss");
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(20);
		 sc.getBcScrollLookups().add(lk);
		 scrollList.add(sc);
		 sc = new BcScroll();
		 sc.setId(2);
		 sc.setImageUrl("/img/pc/adf78.jpg");
		 sc.setImageUrl2("/img/pc/ghu890.jpg");
		 sc.setMaterial("deer skin");
		 sc.setModifiedBy("test");
		 sc.setModifiedDate(new java.util.Date());
		 sc.setCreatedBy("test");
		 sc.setCreatedDate(new java.util.Date());
		 sc.setOrgin("yemen");
		 sc.setTitle("test scroll2");
		 sc.setAge("200");
		 book = new TorahBook();
		 book.setId(1);
		 book.setBookName("Shemot");
		 book.setEngBookName("Exodus");
		 sc.setTorahBook(book);
		 sc.setVerseAddress("Ex 17:34-20:20");
		 sc.setBcScrollImages(new HashSet<BcScrollImage>());
		 sc.setBcScrollLookups(new HashSet<BcScrollLookup>());
		 img = new BcScrollImage();
		 img.setId(5);
		 img.setImageUrl("/gvh/yyu/ytr700.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 img = new BcScrollImage();
		 img.setId(6);
		 img.setImageUrl("/gvh/yyu/ytr701.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 img = new BcScrollImage();
		 img.setId(7);
		 img.setImageUrl("/gvh/yyu/ytr702.jpg");
		 img.setModifiedBy("test");
		 img.setModifiedDate(new java.util.Date());
		 img.setCreatedBy("test");
		 img.setCreatedDate(new java.util.Date());
		 sc.getBcScrollImages().add(img);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(17);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(5);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(30);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(18);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(6);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(22);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(19);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef(null);
		 lk.setId(7);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 lk.setTorahBook(book);
		 lk.setVerseX(1);
		 lk.setVerseY(46);
		 sc.getBcScrollLookups().add(lk);
		 lk = new BcScrollLookup();
		 lk.setBcScroll(sc);
		 lk.setChapter(11);
		 lk.setCreatedBy("test");
		 lk.setCreatedDate(new java.util.Date());
		 lk.setCrossRef("Y");
		 lk.setId(4);
		 lk.setModifiedBy("test");
		 lk.setModifiedDate(new java.util.Date());
		 book = new TorahBook();
		 book.setId(53);
		 book.setBookName("Yehudi");
		 book.setEngBookName("Hebrewss");
		 lk.setTorahBook(book);
		 lk.setVerseX(14);
		 lk.setVerseY(18);
		 sc.getBcScrollLookups().add(lk);
		 scrollList.add(sc);
		return scrollList;
	}
	
	@Test
	public void testFindScroll() {
		when(scrollRepository.findScrollById(any(Integer.class)))
        .thenReturn(createOne());
		MyResponse<List<Scroll>> result = scrollService.findScroll(2);
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.OK,result.getStatusCode());
		assertEquals(1,result.getPayload().size());
		for(Scroll sc: result.getPayload()) {
			assertNotNull(sc.getAge());
			assertNotNull(sc.getId());
			assertNotNull(sc.getImageUrl());
			assertNotNull(sc.getImageUrl2());
			assertNotNull(sc.getMaterial());
			assertNotNull(sc.getOrgin());
			assertNotNull(sc.getTitle());
			assertNotNull(sc.getTorahBook());
			assertNotNull(sc.getTorahBook().getId());
			assertNotNull(sc.getTorahBook().getBookName());
			assertNotNull(sc.getTorahBook().getEngBookName());
			assertNotNull(sc.getVerseAddress());
			assertNotNull(sc.getImageUrls());
			assertNotNull(sc.getScrollLookups());
			for(ScrollImage img : sc.getImageUrls()) {
				assertNotNull(img.getId());
				assertNotNull(img.getImageUrl());
			}
			for(ScrollLookup lk : sc.getScrollLookups()) {
				assertNotNull(lk.getChapter());
				assertNotNull(lk.getId());
				assertNotNull(lk.getTorahBook());
				assertNotNull(lk.getTorahBook().getBookName());
				assertNotNull(lk.getTorahBook().getEngBookName());
				assertNotNull(lk.getVerseX());
			}
		}
	}
	
	@Test
	public void testFindAllScroll() {
		when(scrollRepository.findAllScrolls())
        .thenReturn(createTwo());
		MyResponse<List<Scroll>> result = scrollService.findAllScroll();
		System.out.println(result.getErrorMessage());
		assertEquals(StatusCode.OK,result.getStatusCode());
		assertEquals(2,result.getPayload().size());
		for(Scroll sc: result.getPayload()) {
			assertNotNull(sc.getAge());
			assertNotNull(sc.getId());
			assertNotNull(sc.getImageUrl());
			assertNotNull(sc.getImageUrl2());
			assertNotNull(sc.getMaterial());
			assertNotNull(sc.getOrgin());
			assertNotNull(sc.getTitle());
			assertNotNull(sc.getTorahBook());
			assertNotNull(sc.getTorahBook().getId());
			assertNotNull(sc.getTorahBook().getBookName());
			assertNotNull(sc.getTorahBook().getEngBookName());
			assertNotNull(sc.getVerseAddress());
			assertNotNull(sc.getImageUrls());
			assertNotNull(sc.getScrollLookups());
			for(ScrollImage img : sc.getImageUrls()) {
				assertNotNull(img.getId());
				assertNotNull(img.getImageUrl());
			}
			for(ScrollLookup lk : sc.getScrollLookups()) {
				assertNotNull(lk.getChapter());
				assertNotNull(lk.getId());
				assertNotNull(lk.getTorahBook());
				assertNotNull(lk.getTorahBook().getBookName());
				assertNotNull(lk.getTorahBook().getEngBookName());
				assertNotNull(lk.getVerseX());
			}
		}
	}

}
