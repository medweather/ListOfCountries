package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Country;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CountryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<String> getNameOfCountry() {
        return getCurrentSession().createSQLQuery("select name from country;").list();
    }

    public void save(Country country) {
        SessionImpl sessionImpl = (SessionImpl) getCurrentSession();
        Connection connection = sessionImpl.connection();
        try {

            Array arrayTopLevelDomain = connection.createArrayOf("text", country.getTopLevelDomain());
            Array arrayLatlng = connection.createArrayOf("text", country.getLatlng());

            String sql = "insert into country (name, top_level_domain, population, latlng, flag) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, country.getName());
            ps.setArray(2, arrayTopLevelDomain);
            ps.setLong(3, country.getPopulation());
            ps.setArray(4, arrayLatlng);
            ps.setString(5, country.getFlag());
            ps.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
