package net.bible.code.scroll.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import net.bible.code.servicelayer.contants.StatusCode;
import net.bible.code.servicelayer.pojo.Book;
import net.bible.code.servicelayer.pojo.MyResponse;
import net.bible.code.servicelayer.pojo.Scroll;
import net.bible.code.servicelayer.pojo.ScrollImage;
import net.bible.code.servicelayer.pojo.ScrollLookup;
import net.bible.code.servicelayer.services.ScrollService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ScrollControllerTest {
	

    ObjectMapper objectMapper = new ObjectMapper();
	
    private MockMvc mockMvc;

    @InjectMocks
	private ScrollController scrollController;
    @Mock
	private ScrollService scrollService;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(scrollController).build();
    }
    
    public Scroll createRequest() {
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
		return x;
    }
    
    public MyResponse<List<Scroll>> scrollResult() {
    	MyResponse<List<Scroll>> response = new MyResponse();
    	List<Scroll> scrollList = new ArrayList<Scroll>();
    	scrollList.add(createRequest());
    	response.setErrorMessage("ok");
    	response.setPayload(scrollList);
    	response.setStatusCode(StatusCode.OK);
    	return response;
    }
    
    public MyResponse<List<Scroll>> scrollSystemError() {
    	MyResponse<List<Scroll>> response = new MyResponse();
    	response.setErrorMessage("Null Pointer Exception");
    	response.setStatusCode(StatusCode.DATABASE_EXCEPTION);
    	return response;
    }
    
    public MyResponse <String> successOk() {
    	MyResponse <String> response = new MyResponse();
    	response.setErrorMessage("ok");
    	response.setPayload("1");
    	response.setStatusCode(StatusCode.OK);
    	return response;
    }
    
    public MyResponse <String> validateError() {
    	MyResponse <String> response = new MyResponse();
    	response.setErrorMessage("missing fields in the request");
    	response.setStatusCode(StatusCode.VALIDATION_ERROR);
    	return response;
    }
    
    public MyResponse <String> systemError() {
    	MyResponse <String> response = new MyResponse();
    	response.setErrorMessage("Null Pointer Exception");
    	response.setStatusCode(StatusCode.DATABASE_EXCEPTION);
    	return response;
    }
	
    @Test
    public void testSave() throws Exception {
    	Scroll req =  createRequest();
        String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.saveScroll(any(Scroll.class),any(String.class))).thenReturn(successOk());
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/scroll").contentType(MediaType.APPLICATION_JSON)
                .content(input)).andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testSaveValidate() throws Exception {
    	Scroll req =  createRequest();
        String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.saveScroll(any(Scroll.class),any(String.class))).thenReturn(validateError());
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/scroll").contentType(MediaType.APPLICATION_JSON)
                .content(input)).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testSaveError() throws Exception {
    	Scroll req =  createRequest();
        String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.saveScroll(any(Scroll.class),any(String.class))).thenReturn(systemError());
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/scroll").contentType(MediaType.APPLICATION_JSON)
                .content(input)).andExpect(status().is5xxServerError());
    }
    
    @Test
    public void testFindById() throws Exception {
    	//Scroll req =  createRequest();
        //String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.findScroll(any(Integer.class))).thenReturn(scrollResult());
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/scroll/2").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testFindByIdError() throws Exception {
    	//Scroll req =  createRequest();
        //String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.findScroll(any(Integer.class))).thenReturn(scrollSystemError());
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/scroll/2").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is5xxServerError());
    }
    
    @Test
    public void testFindAll() throws Exception {
    	//Scroll req =  createRequest();
        //String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.findAllScroll()).thenReturn(scrollResult());
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/scroll").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void testFindAllError() throws Exception {
    	//Scroll req =  createRequest();
        //String input = objectMapper.writeValueAsString(req);
        
        when(scrollService.findAllScroll()).thenReturn(scrollSystemError());
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/api/scroll").contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is5xxServerError());
    }

}
