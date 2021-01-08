package com.mobikiwik.league.controller;

import com.mobikiwik.league.model.League;
import com.mobikiwik.league.model.LeagueComparator;
import com.mobikiwik.league.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
public class LeagueController {
    @Autowired
    LeagueService leagueService;
    @RequestMapping("/{leagueName}/{countryId}")
    public League getLeagueInfo(@PathVariable("leagueName") String leagueName, @PathVariable("countryId") String countryId){
        LeagueComparator leagueComparator = new LeagueComparator(leagueName, countryId);
        League league = leagueService.getLeagueByName(leagueComparator);
        return league;
    }
}
