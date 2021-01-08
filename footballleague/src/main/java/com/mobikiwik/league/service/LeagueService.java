package com.mobikiwik.league.service;

import com.mobikiwik.league.model.League;
import com.mobikiwik.league.model.LeagueComparator;
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
public class LeagueService {
    @Value("${league.api}")
    private String api;
    @Value("${league.url}")
    private String url;
    public List<League> getLeagues(LeagueComparator leagueComparator){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers =  new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+leagueComparator.getCountryId()+"&").queryParam("APIkey",api);
        List<League> leagueList = Arrays.asList(restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(headers), League[].class).getBody());
        return leagueList;
    }

    public League getLeagueByName(LeagueComparator leagueComparator) {
        return getLeagues(leagueComparator).stream().filter((league) -> leagueComparator.getLeagueName().equalsIgnoreCase(league.getLeagueName())).findAny().orElse(null);
        //return getLeagues().get(0);
    }
}
