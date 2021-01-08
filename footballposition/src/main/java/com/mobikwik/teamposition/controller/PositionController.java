package com.mobikwik.teamposition.controller;

import com.mobikwik.teamposition.model.TeamPosition;
import com.mobikwik.teamposition.model.TeamPositionComparator;
import com.mobikwik.teamposition.service.TeamPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class PositionController {
    @Autowired
    TeamPositionService teamPositionService;
    @RequestMapping("/{teamName}/{leagueId}")
    public TeamPosition getTeamInfo(@PathVariable("teamName") String teamName, @PathVariable("leagueId") String leagueId){
        TeamPositionComparator teamPositionComparator = new TeamPositionComparator(teamName, leagueId);
        TeamPosition teamPosition = teamPositionService.getTeamByName(teamPositionComparator);

        return teamPosition;
    }
}
