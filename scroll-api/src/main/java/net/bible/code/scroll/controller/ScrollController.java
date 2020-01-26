package net.bible.code.scroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bible.code.servicelayer.pojo.MyResponse;
import net.bible.code.servicelayer.pojo.Scroll;
import net.bible.code.servicelayer.services.ScrollService;
import net.bible.code.servicelayer.utils.Utils;

import org.springframework.security.core.userdetails.User;

@RestController
public class ScrollController {

	@Autowired
	private ScrollService scrollService;
	
	@PostMapping(value = "/api/scroll")
    public ResponseEntity<?> saveScroll(@RequestBody Scroll scroll, OAuth2Authentication auth) {
		String username = auth != null ? ((User)auth.getPrincipal()).getUsername() : "unknown";
		MyResponse<String> result = scrollService.saveScroll(scroll, username);
    	return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }
	
	@GetMapping(value = "/api/scroll")
    public ResponseEntity<?> findAll() {
		MyResponse<List<Scroll>> result = scrollService.findAllScroll();
    	return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }
	
	@GetMapping(value = "/api/scroll/{id}")
    public ResponseEntity<?> findAll(@PathVariable("id") String id) {
		MyResponse<List<Scroll>> result = scrollService.findScroll(Utils.toInteger(id));
    	return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }

}
