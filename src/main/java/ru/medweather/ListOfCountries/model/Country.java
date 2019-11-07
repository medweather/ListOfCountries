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
}
