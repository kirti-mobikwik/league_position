package com.mobikiwik.league.service;

import com.mobikiwik.league.model.League;
import com.mobikiwik.league.model.LeagueComparator;
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
    public List<League> getLeagues(LeagueComparator leagueComparator){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers =  new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://apiv2.apifootball.com/?action=get_leagues&country_id="+leagueComparator.getCountryId()+"&").queryParam("APIkey","9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
        List<League> leagueList = Arrays.asList(restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(headers), League[].class).getBody());
        return leagueList;
    }

    public League getLeagueByName(LeagueComparator leagueComparator) {
        return getLeagues(leagueComparator).stream().filter((league) -> leagueComparator.getLeagueName().equalsIgnoreCase(league.getLeagueName())).findAny().orElse(null);
        //return getLeagues().get(0);
    }
}
