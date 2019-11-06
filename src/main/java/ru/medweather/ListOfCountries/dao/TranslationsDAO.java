package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Translations;

@Repository
@Transactional
public class TranslationsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Translations translations) {
        getCurrentSession().save(translations);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
