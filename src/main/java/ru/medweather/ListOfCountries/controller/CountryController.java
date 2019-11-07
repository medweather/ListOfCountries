package ru.medweather.ListOfCountries.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.medweather.ListOfCountries.api.ResponseApi;
import ru.medweather.ListOfCountries.service.CountryService;

@RestController
@RequestMapping("country")
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
            @RequestParam String name,
            @RequestParam String domain
    ) {
        ResponseApi responseApi = countryService.searchCountry(name, domain);
        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    /**
     * удаление всех данных из таблицы
     *
     * @return
     */
    @DeleteMapping("")
    public ResponseEntity deleteAllData() {
        return null;
    }

    /**
     * созранение данных из удаленного сервера в бд
     *
     * @return
     */
    @GetMapping("/save")
    public ResponseEntity saveAllData() {
        return null;
    }
}
