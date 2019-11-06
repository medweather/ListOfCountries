package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Country;
import ru.medweather.ListOfCountries.model.RegionalBlocs;
import ru.medweather.ListOfCountries.model.Translations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Transactional
public class TranslationsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Translations translations) {
        getCurrentSession().save(translations);
    }

    public void saveCountryId(Translations translations, Country country) {
        try {
            String sql = "update translations set country_id = " + country.getId() + " where id = " + translations.getId() + ";";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.executeUpdate();
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        SessionImpl sessionImpl = (SessionImpl) getCurrentSession();
        return sessionImpl.connection();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
