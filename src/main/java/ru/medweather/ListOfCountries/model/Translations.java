package ru.medweather.ListOfCountries.model;

import javax.persistence.*;

@Entity
@Table(name = "translations")
public class Translations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String de;
    private String es;
    private String fr;
    private String ja;
    private String it;
    private String br;
    private String pt;
    private String nl;
    private String hr;
    private String fa;

    @Column(name = "country_id")
    private int countryId;

    public Translations() {
    }

    public Translations(String de, String es, String fr, String ja, String it, String br,
                        String pt, String nl, String hr, String fa, int countryId) {
        this.de = de;
        this.es = es;
        this.fr = fr;
        this.ja = ja;
        this.it = it;
        this.br = br;
        this.pt = pt;
        this.nl = nl;
        this.hr = hr;
        this.fa = fa;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}

