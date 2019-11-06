package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Currency;

@Repository
@Transactional
public class CurrencyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Currency currency) {
        getCurrentSession().save(currency);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
