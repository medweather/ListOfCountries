package ru.medweather.ListOfCountries.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractResponse {

    @JsonIgnore
    private boolean isSuccess;

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
