package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Language;

@Repository
@Transactional
public class LanguageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Language language) {
        getCurrentSession().save(language);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
