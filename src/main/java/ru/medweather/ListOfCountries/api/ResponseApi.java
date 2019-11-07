package ru.medweather.ListOfCountries.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * API общего ответа на все запросы
 */
public class ResponseApi extends AbstractResponse{

    private long timestamp;
    private AbstractResponse data;

    public ResponseApi(long timestamp, AbstractResponse data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public ResponseApi() {
        this.timestamp = new Date().getTime();
    }

    @JsonProperty("data")
    public AbstractResponse getAbstractResponse() {
        return data;
    }

    public void setAbstractResponse(AbstractResponse data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
