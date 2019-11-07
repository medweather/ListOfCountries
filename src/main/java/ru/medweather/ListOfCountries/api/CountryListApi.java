package ru.medweather.ListOfCountries.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CountryListApi extends ResponseApi{

    private List<CountryApi> data = new ArrayList<>();

    @JsonProperty("data")
    public List<CountryApi> getData() {
        return data;
    }

    public void setData(List<CountryApi> data) {
        this.data = data;
    }
}
