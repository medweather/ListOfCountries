package ru.medweather.ListOfCountries.component;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.medweather.ListOfCountries.dao.*;
import ru.medweather.ListOfCountries.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Component
public class DeserializeComponent implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(DeserializeComponent.class);

    private final CountryDAO countryDAO;

    private final CurrencyDAO currencyDAO;

    private final LanguageDAO languageDAO;

    private final RegionalBlocsDAO regionalBlocsDAO;

    private final TranslationsDAO translationsDAO;

    public DeserializeComponent(CountryDAO countryDAO, CurrencyDAO currencyDAO, LanguageDAO languageDAO, RegionalBlocsDAO regionalBlocsDAO, TranslationsDAO translationsDAO) {
        this.countryDAO = countryDAO;
        this.currencyDAO = currencyDAO;
        this.languageDAO = languageDAO;
        this.regionalBlocsDAO = regionalBlocsDAO;
        this.translationsDAO = translationsDAO;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        log.info("Start jsonData save to database");

        String url = "https://restcountries.eu/rest/v2/all";
        InputStream inputStream = null;
        String result = null;

        if(!countryDAO.getNameOfCountry().contains("Afghanistan")) {
            try {

                URLConnection connection = new URL(url).openConnection();
                connection.setRequestProperty("Content-type", "application/json");
                inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {

                    sb.append(line).append("\n");
                }

                result = sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null)
                        inputStream.close();

                } catch (Exception ignored) { }
            }

            JSONArray jsonArray = new JSONArray(result);
            Gson gson = new Gson();


            for (int i = 0; i < jsonArray.length(); i++) {

                if (jsonArray.get(i) instanceof JSONObject) {

                    JSONObject jsonObj = (JSONObject) jsonArray.get(i);
                    Country country = gson.fromJson(String.valueOf(jsonObj), Country.class);
                    country.setId(i + 1);
                    countryDAO.save(country);

                    Currency currency;
                    Language language;
                    RegionalBlocs regionalBlocs;

                    JSONArray currenciesJson = jsonObj.getJSONArray("currencies");

                    for (int c = 0; c < currenciesJson.length(); c++) {
                        if (currenciesJson.get(c) instanceof JSONObject) {
                            JSONObject currencyJson = (JSONObject) currenciesJson.get(c);
                            currency = gson.fromJson(String.valueOf(currencyJson), Currency.class);
                            currencyDAO.save(currency);
                            currencyDAO.saveCountryId(currency, country);
                        }
                    }

                    JSONArray languagesJson = jsonObj.getJSONArray("languages");

                    for (int l = 0; l < languagesJson.length(); l++) {
                        if (languagesJson.get(l) instanceof JSONObject) {
                            JSONObject languageJson = (JSONObject) languagesJson.get(l);
                            language = gson.fromJson(String.valueOf(languageJson), Language.class);
                            languageDAO.save(language);
                            languageDAO.saveCountryId(language, country);
                        }
                    }

                    JSONArray regionalBlocsJson = jsonObj.getJSONArray("regionalBlocs");

                    for (int r = 0; r < regionalBlocsJson.length(); r++) {
                        if (regionalBlocsJson.get(r) instanceof JSONObject) {
                            JSONObject regionalJson = (JSONObject) regionalBlocsJson.get(r);
                            regionalBlocs = gson.fromJson(String.valueOf(regionalJson), RegionalBlocs.class);
                            regionalBlocsDAO.save(regionalBlocs, country);
                        }
                    }

                    JSONObject translationsJson = jsonObj.getJSONObject("translations");
                    Translations translations = gson.fromJson(String.valueOf(translationsJson), Translations.class);
                    translationsDAO.save(translations);
                    translationsDAO.saveCountryId(translations, country);
                }
            }
        }
        else {
            log.info("The data is already in the database");
        }

        log.info("End jsonData save to database");
    }
}
