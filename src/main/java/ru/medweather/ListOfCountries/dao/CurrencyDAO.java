package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Country;
import ru.medweather.ListOfCountries.model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CurrencyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Currency currency) {
        getCurrentSession().save(currency);
    }

    public void saveCountryId(Currency currency, Country country) {
        try {
            String sql = "update currency set country_id = " + country.getId() + " where id = " + currency.getId() + ";";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.executeUpdate();
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Currency> getListCurrencyByCountry(Country country) {
        return getCurrentSession().createQuery("from Currency c where c.countryId = " + country.getId(),
                Currency.class).list();
    }

    private Connection getConnection() {
        SessionImpl sessionImpl = (SessionImpl) getCurrentSession();
        return sessionImpl.connection();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
