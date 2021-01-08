package com.mobikwik.country.controller;

import com.mobikwik.country.model.Country;
import com.mobikwik.country.model.CountryComparator;
import com.mobikwik.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @RequestMapping("{countryName}")
    public Country getCountryInfo(@PathVariable("countryName") String countryName)  {
        CountryComparator countryComparator = new CountryComparator(countryName);
        Country country = countryService.getCountryByName(countryName,countryComparator);
        return country;
    }
}