package com.kushals.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.kushals.exception.InvalidWordNumberCountException;
import com.kushals.utils.AppConfiguration;

public class CounterService {

	private Map<String, Integer> wordsCountMap;

	@Autowired
	private AppConfiguration appConfig;

	/**
	 * Returns requested top word frequency words with their count
	 * 
	 * @param number
	 * @return
	 * @throws InvalidWordNumberCountException
	 */
	public Map<String, Integer> getTopWordsCount(int number) throws InvalidWordNumberCountException {
		if (wordsCountMap == null) {
			System.out.println("Requesting to generate words count map from file for the first time...");
			wordsCountMap = getWordsCountMap();
		}
		if (number > wordsCountMap.size())
			throw new InvalidWordNumberCountException(
					"Requested number of words can't be returned, number exceeds total number of words");
		Map<String, Integer> targetMap = new LinkedHashMap<>();
		for (Entry<String, Integer> entry : wordsCountMap.entrySet()) {
			if (number == 0) {
				break;
			}
			targetMap.put(entry.getKey().toString(), Integer.valueOf(entry.getValue().toString()));
			number--;
		}
		targetMap.forEach((word, count) -> System.out.println("Word: " + word + " Count: " + count));
		return targetMap;
	}

	/**
	 * Returns map with word-count pair
	 * 
	 * @return
	 */
	private Map<String, Integer> getWordsCountMap() {
		ResponseEntity<String> responseEntity = getFile();
		List<String> parsedFile = parseFileContents(responseEntity.getBody());
		Map<String, Integer> wordsCountMap = getWordsFrequencyCount(parsedFile);
		return sortMapByValues(wordsCountMap);
	}

	/**
	 * Returns response for file URL
	 * 
	 * @return
	 */
	public ResponseEntity<String> getFile() {
		RestTemplate template = new RestTemplate();
		URI url;
		ResponseEntity<String> responseEntity = null;
		try {
			url = new URI(appConfig.getFileUrl());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_PLAIN);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			responseEntity = template.exchange(url, HttpMethod.GET, entity, String.class);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	/**
	 * Returns parsed words from file as list
	 * 
	 * @param contents
	 * @return
	 */
	public List<String> parseFileContents(String contents) {
		// split string by word boundary and store it in array
		String[] words = contents.split("\\b");
		// remove punctuation from spited words
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w[0-9]+]", "");
		}
		List<String> wordsList = new ArrayList<>(Arrays.asList(words));
		wordsList.removeIf(item -> item.isEmpty());
		System.out.println(wordsList);
		return wordsList;
	}

	/**
	 * Returns words frequency count map
	 * 
	 * @param wordsList
	 * @return
	 */
	private Map<String, Integer> getWordsFrequencyCount(List<String> wordsList) {
		Map<String, Integer> wordsCount = new TreeMap<>();
		for (String word : wordsList) {
			int count = Collections.frequency(wordsList, word);
			if (!wordsCount.containsKey(word)) {
				wordsCount.put(word, count);
			}
		}
		return wordsCount;
	}

	/**
	 * Returns sorted map in descending order of the frequency count
	 * 
	 * @param wordsCount
	 * @return
	 */
	private Map<String, Integer> sortMapByValues(Map<String, Integer> wordsCount) {
		// get keys and values in list
		List<String> mapKeys = new ArrayList<>(wordsCount.keySet());
		List<Integer> mapValues = new ArrayList<>(wordsCount.values());
		// sort both the lists to get in order
		Collections.sort(mapKeys);
		// sort values array list in descending order
		Collections.sort(mapValues, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		// create LinkedHashMap to store sorted map entries by value
		Map<String, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIterator = mapValues.iterator();

		while (valueIterator.hasNext()) {
			Integer value = valueIterator.next();
			Iterator<String> keysIterator = mapKeys.iterator();
			while (keysIterator.hasNext()) {
				String key = keysIterator.next();
				Integer orgnlMapValue = wordsCount.get(key);
				if (value == orgnlMapValue) {
					keysIterator.remove();
					sortedMap.put(key, value);
					break;
				}
			}
		}
		return sortedMap;
	}

}
