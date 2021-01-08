package com.mobikwik.teamposition.service;

import com.mobikwik.teamposition.model.TeamPosition;
import com.mobikwik.teamposition.model.TeamPositionComparator;
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
public class TeamPositionService {
    public List<TeamPosition> getStandings(TeamPositionComparator teamPositionComparator){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers =  new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://apiv2.apifootball.com/?action=get_standings&league_id="+teamPositionComparator.getLeagueId()+"&").queryParam("APIkey","9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
        List<TeamPosition> positionList = Arrays.asList(restTemplate.exchange(builder.toUriString(), HttpMethod.POST, new HttpEntity<>(headers), TeamPosition[].class).getBody());
        return positionList;
    }

    public TeamPosition getTeamByName(TeamPositionComparator teamPositionComparator) {
        return getStandings(teamPositionComparator).stream().filter((teamPosition) -> teamPositionComparator.getTeamName().equalsIgnoreCase(teamPosition.getTeamName())).findAny().orElse(null);
        //return getStandings().get(0);
    }
}
