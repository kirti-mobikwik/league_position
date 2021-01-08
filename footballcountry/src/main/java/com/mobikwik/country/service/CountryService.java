package com.mobikwik.country.service;


import com.mobikwik.country.model.Country;
import com.mobikwik.country.model.CountryComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {
    @Value("${country.api}")
    private String api;
    @Value("${country.url}")
    private String url;

    public List<Country> getCountries(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers =  new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("APIkey",api);
        List<Country> countryList = Arrays.asList(restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(headers), Country[].class).getBody());
        return countryList;
    }
    public Country getCountryByName(String countryName, CountryComparator countryComparator) {
        return getCountries().stream().filter(country -> countryComparator.getCountryName().equalsIgnoreCase(country.getCountryName())).findAny().orElse(null);
    }
}
