package net.bible.code.bible.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.bible.code.servicelayer.contants.StatusCode;
import net.bible.code.servicelayer.pojo.BibleVerse;
import net.bible.code.servicelayer.pojo.BibleVerseRequest;
import net.bible.code.servicelayer.pojo.Book;
import net.bible.code.servicelayer.pojo.MyResponse;
import net.bible.code.servicelayer.services.VerseService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BibleControllerTest {
	
    ObjectMapper objectMapper = new ObjectMapper();
	
    private MockMvc mockMvc;

    @InjectMocks
	private BibleController bibleController;
    @Mock
	private VerseService verseService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bibleController).build();
    }
    
    public MyResponse <List<BibleVerse>> createData() {
    	MyResponse <List<BibleVerse>> response = new MyResponse();
    	List<BibleVerse> verseList = new ArrayList<BibleVerse>();
    	Book book = new Book();
    	book.setBookName("test");
    	book.setId(1);
    	book.setEngBookName("test2");
    	BibleVerse verse = new BibleVerse();
    	verse.setEngVerse("test a b c ");
    	verse.setHChapter("AA");
    	verse.setHVerse("BB");
    	verse.setId(1);
    	verse.setNumChapter(1);
    	verse.setNumValue(200);
    	verse.setNumVerse(2);
    	verse.setTorahBook(book);
    	verse.setTranVerse("ghy trei");
    	verse.setVerse("alpeh bet");
    	verseList.add(verse);
    	response.setPayload(verseList);
    	response.setStatusCode(StatusCode.OK);
    	return response;
    }
    
    public MyResponse <List<BibleVerse>> validateError() {
    	MyResponse <List<BibleVerse>> response = new MyResponse();
    	response.setErrorMessage("missing fields in the request");
    	response.setStatusCode(StatusCode.VALIDATION_ERROR);
    	return response;
    }
    
    public MyResponse <List<BibleVerse>> systemError() {
    	MyResponse <List<BibleVerse>> response = new MyResponse();
    	response.setErrorMessage("Null Pointer Exception");
    	response.setStatusCode(StatusCode.DATABASE_EXCEPTION);
    	return response;
    }
    
    @Test
    public void testHappyPath() throws Exception {
		
    	BibleVerseRequest req = new BibleVerseRequest();
    	req.setBookId(1+"");
    	req.setChapter("1");
    	req.setVerseX("1");
    	req.setVerseY("12");

        String input = objectMapper.writeValueAsString(req);

        when(verseService.findBibleVerse(any(BibleVerseRequest.class))).thenReturn(createData());
        
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/verselookup").contentType(MediaType.APPLICATION_JSON)
                .content(input)).andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testValidationError() throws Exception {
		
    	BibleVerseRequest req = new BibleVerseRequest();
    	req.setBookId(1+"");
    	req.setChapter("1");
    	req.setVerseX("1");
    	req.setVerseY("12");

        String input = objectMapper.writeValueAsString(req);

        when(verseService.findBibleVerse(any(BibleVerseRequest.class))).thenReturn(validateError());
        
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/verselookup").contentType(MediaType.APPLICATION_JSON)
                .content(input)).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testSystemError() throws Exception {
		
    	BibleVerseRequest req = new BibleVerseRequest();
    	req.setBookId(1+"");
    	req.setChapter("1");
    	req.setVerseX("1");
    	req.setVerseY("12");

        String input = objectMapper.writeValueAsString(req);

        when(verseService.findBibleVerse(any(BibleVerseRequest.class))).thenReturn(systemError());
        
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/verselookup").contentType(MediaType.APPLICATION_JSON)
                .content(input)).andExpect(status().is5xxServerError());
    }
    

}
