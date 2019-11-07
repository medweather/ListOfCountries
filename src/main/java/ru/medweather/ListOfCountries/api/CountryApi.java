package ru.medweather.ListOfCountries.api;

import ru.medweather.ListOfCountries.model.Currency;
import ru.medweather.ListOfCountries.model.Language;
import ru.medweather.ListOfCountries.model.RegionalBlocs;
import ru.medweather.ListOfCountries.model.Translations;

import java.util.List;

public class CountryApi extends AbstractResponse {

    private int id;
    private String name;
    private String[] topLevelDomain;
    private long population;
    private String[] latlng;
    private String flag;
    private List<Currency> currencies;
    private List<Language> languages;
    private List<RegionalBlocs> regionalBlocs;
    private Translations translations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String[] topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String[] getLatlng() {
        return latlng;
    }

    public void setLatlng(String[] latlng) {
        this.latlng = latlng;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<RegionalBlocs> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBlocs> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }
}
