package com.kushals.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kushals.exception.InvalidWordNumberCountException;
import com.kushals.pojo.ErrorLog;
import com.kushals.services.CounterService;

@CrossOrigin
@RestController
@RequestMapping("/counter")
public class EntryController {

	@Autowired
	private CounterService counterService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testController() {
		return "test succeed";
	}

	@RequestMapping(value = "/getfile", method = RequestMethod.GET)
	public String getFile() {
		ResponseEntity<String> responseEntity = counterService.getFile();
		return responseEntity.getBody();
	}

	@RequestMapping(value = "/getwords", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Integer>> getTopFrequencyCountWords(@RequestParam("number") int number)
			throws InvalidWordNumberCountException {
		Map<String, Integer> topWordsCount;
		topWordsCount = counterService.getTopWordsCount(number);
		return new ResponseEntity<>(topWordsCount, HttpStatus.OK);
	}

	@ExceptionHandler(InvalidWordNumberCountException.class)
	public ResponseEntity<ErrorLog> handleInvalidWordNumberRequest(InvalidWordNumberCountException e) {
		ErrorLog errorLog = new ErrorLog(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
		return new ResponseEntity<ErrorLog>(errorLog, HttpStatus.BAD_REQUEST);
	}
}
