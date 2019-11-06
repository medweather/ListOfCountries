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
    private String[] latlng;
    private String flag;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<RegionalBlocs> regionalBlocs;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Currency> currencies;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Language> languages;

    @OneToOne(mappedBy = "country")
    private Translations translations;

    public Country() {
    }

    public Country(String name, String[] topLevelDomain, long population, String[] latlng, String flag) {
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.population = population;
        this.latlng = latlng;
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

    public List<RegionalBlocs> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBlocs> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
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

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }
}
