package ru.medweather.ListOfCountries.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.medweather.ListOfCountries.api.ResponseApi;
import ru.medweather.ListOfCountries.service.CountryService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * поиск страны
     *
     * @param name
     * @param domain
     * @return
     */
    @GetMapping("")
    public ResponseEntity searchCountry(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String domain
    ) {
        ResponseApi responseApi = countryService.searchCountry(name, domain);
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * удаление всех данных из таблицы
     *
     * @return
     */
    @DeleteMapping("")
    public ResponseEntity deleteAllData() {
        ResponseApi responseApi = countryService.deleteData();
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * созранение данных из удаленного сервера в бд
     *
     * @return
     */
    @GetMapping("/save")
    public ResponseEntity saveAllData() {

        ResponseApi responseApi = countryService.saveData();
        return responseApi == null ? badRequestResponse() : new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    private ResponseEntity<Object> badRequestResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "invalid_request");
        response.put("error_description", "not_found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
