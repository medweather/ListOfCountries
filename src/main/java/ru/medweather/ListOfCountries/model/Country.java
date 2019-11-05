package ru.medweather.ListOfCountries.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(name = "top_level_domain")
    private String[] topLevelDomain;
    private long population;

    @Column(name = "lat_ing")
    private int[] latIng;
    private String flag;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<RegionalBlocs> regionalBlocsList;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Currency> currencies;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Language> languages;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Translations> translationsList;

    public Country() {
    }

    public Country(String name, String[] topLevelDomain, long population, int[] latIng, String flag) {
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.population = population;
        this.latIng = latIng;
        this.flag = flag;
    }

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

    public int[] getLatIng() {
        return latIng;
    }

    public void setLatIng(int[] latIng) {
        this.latIng = latIng;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RegionalBlocs> getRegionalBlocsList() {
        return regionalBlocsList;
    }

    public void setRegionalBlocsList(List<RegionalBlocs> regionalBlocsList) {
        this.regionalBlocsList = regionalBlocsList;
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

    public List<Translations> getTranslationsList() {
        return translationsList;
    }

    public void setTranslationsList(List<Translations> translationsList) {
        this.translationsList = translationsList;
    }
}
