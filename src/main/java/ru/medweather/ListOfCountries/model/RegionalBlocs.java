package ru.medweather.ListOfCountries.model;

import javax.persistence.*;

@Entity
@Table(name = "regional_blocs")
public class RegionalBlocs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String acronym;
    private String name;

    @Column(name = "other_acronyms")
    private String[] otherAcronyms;

    @Column(name = "other_names")
    private String[] otherNames;

    @Column(name = "country_id")
    private int countryId;

    public RegionalBlocs() {
    }

    public RegionalBlocs(String acronym, String name, String[] otherAcronyms, String[] otherNames, int countryId) {
        this.acronym = acronym;
        this.name = name;
        this.otherAcronyms = otherAcronyms;
        this.otherNames = otherNames;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(String[] otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public String[] getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String[] otherNames) {
        this.otherNames = otherNames;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
