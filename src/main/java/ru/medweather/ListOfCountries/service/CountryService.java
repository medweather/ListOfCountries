package ru.medweather.ListOfCountries.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.medweather.ListOfCountries.api.CountryApi;
import ru.medweather.ListOfCountries.api.CountryListApi;
import ru.medweather.ListOfCountries.api.InfoAfterEventOverDataApi;
import ru.medweather.ListOfCountries.api.ResponseApi;
import ru.medweather.ListOfCountries.component.DeserializeComponent;
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

    private final DeserializeComponent deserializeComponent;

    public CountryService(CountryDAO countryDAO, CurrencyDAO currencyDAO, LanguageDAO languageDAO, RegionalBlocsDAO regionalBlocsDAO,
                          TranslationsDAO translationsDAO, ModelMapper modelMapper, DeserializeComponent deserializeComponent) {
        this.countryDAO = countryDAO;
        this.currencyDAO = currencyDAO;
        this.languageDAO = languageDAO;
        this.regionalBlocsDAO = regionalBlocsDAO;
        this.translationsDAO = translationsDAO;
        this.modelMapper = modelMapper;
        this.deserializeComponent = deserializeComponent;
    }

    public ResponseApi searchCountry(String name, String domain) {

        List<Country> countries = countryDAO.searchCountries(name, domain);
        CountryListApi countryListApi = new CountryListApi();
        countryListApi.setData(countries.stream().map(this::fillCountryApi).
                collect(Collectors.toList()));
        countryListApi.setSuccess(true);

        return countryListApi;
    }

    public ResponseApi deleteData() {

        countryDAO.delete();
        currencyDAO.delete();
        languageDAO.delete();
        regionalBlocsDAO.delete();
        translationsDAO.delete();
        InfoAfterEventOverDataApi infoAfterEventOverDataApi = new InfoAfterEventOverDataApi();
        infoAfterEventOverDataApi.setInfo("успешно удалены все данные из бд");
        return infoAfterEventOverDataApi;
    }

    public ResponseApi saveData() {

        deserializeComponent.saveData();
        InfoAfterEventOverDataApi infoAfterEventOverDataApi = new InfoAfterEventOverDataApi();
        infoAfterEventOverDataApi.setInfo("успешно сохранены все данные с удаленного сервера в бд");
        return infoAfterEventOverDataApi;
    }

    private CountryApi fillCountryApi(Country country) {

        CountryApi countryApi = modelMapper.map(country, CountryApi.class);
        countryApi.setCurrencies(currencyDAO.getListCurrencyByCountry(country));
        countryApi.setLanguages(languageDAO.getLanguageByCountry(country));
        countryApi.setRegionalBlocs(regionalBlocsDAO.getRegionalBlocsByCountry(country));
        countryApi.setTranslations(translationsDAO.getTranslationsByCountry(country));

        return countryApi;
    }
}
