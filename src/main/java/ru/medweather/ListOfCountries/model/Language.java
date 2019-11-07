package ru.medweather.ListOfCountries.model;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String iso639_1;
    private String iso639_2;
    private String name;

    @Column(name = "native_name")
    private String nativeName;

    @Column(name = "country_id")
    private int countryId;

    public Language() {
    }

    public Language(String iso639_1, String iso639_2, String name, String nativeName, int countryId) {
        this.iso639_1 = iso639_1;
        this.iso639_2 = iso639_2;
        this.name = name;
        this.nativeName = nativeName;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public String getIso639_2() {
        return iso639_2;
    }

    public void setIso639_2(String iso639_2) {
        this.iso639_2 = iso639_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
