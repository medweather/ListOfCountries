package ru.medweather.ListOfCountries.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.medweather.ListOfCountries.api.CountryApi;
import ru.medweather.ListOfCountries.api.CountryListApi;
import ru.medweather.ListOfCountries.api.ResponseApi;
import ru.medweather.ListOfCountries.dao.*;
import ru.medweather.ListOfCountries.model.Country;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryDAO countryDAO;

    private final CurrencyDAO currencyDAO;

    private final LanguageDAO languageDAO;

    private final RegionalBlocsDAO regionalBlocsDAO;

    private final TranslationsDAO translationsDAO;

    private final ModelMapper modelMapper;

    public CountryService(CountryDAO countryDAO, CurrencyDAO currencyDAO, LanguageDAO languageDAO, RegionalBlocsDAO regionalBlocsDAO,
                          TranslationsDAO translationsDAO, ModelMapper modelMapper) {
        this.countryDAO = countryDAO;
        this.currencyDAO = currencyDAO;
        this.languageDAO = languageDAO;
        this.regionalBlocsDAO = regionalBlocsDAO;
        this.translationsDAO = translationsDAO;
        this.modelMapper = modelMapper;
    }

    public ResponseApi searchCountry(String name, String domain) {

        List<Country> countries = countryDAO.searchCountries(name, domain);
        CountryListApi countryListApi = new CountryListApi();
        countryListApi.setData(countries.stream().map(this::fillCountryApi).
                collect(Collectors.toList()));
        countryListApi.setSuccess(true);

        return countryListApi;
    }

    private CountryApi fillCountryApi(Country country) {
        return modelMapper.map(country, CountryApi.class);
    }
}
