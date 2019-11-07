package ru.medweather.ListOfCountries.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoAfterEventOverDataApi extends ResponseApi {

    private String info;

    @JsonProperty("data")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
