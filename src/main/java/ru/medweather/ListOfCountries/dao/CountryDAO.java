package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Country;

import java.sql.*;
import java.util.ArrayList;
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
        try {

            Array arrayTopLevelDomain = getConnection().createArrayOf("text", country.getTopLevelDomain());
            Array arrayLatlng = getConnection().createArrayOf("text", country.getLatlng());

            String sql = "insert into country (name, top_level_domain, population, latlng, flag) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, country.getName());
            ps.setArray(2, arrayTopLevelDomain);
            ps.setLong(3, country.getPopulation());
            ps.setArray(4, arrayLatlng);
            ps.setString(5, country.getFlag());
            ps.executeUpdate();

            getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Country> searchCountries(String name, String domain) {

        Country country;
        List<Country> countries = new ArrayList<>();

        try {

            String sql = "select * from country where name like '%" + name + "%' " +
                    "and array_to_string(top_level_domain, ', ') like '%" + domain + "%';";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                country = new Country();
                country.setId(rs.getInt(1));
                country.setName(rs.getString(2));
                country.setTopLevelDomain((String[]) rs.getArray(3).getArray());
                country.setPopulation(rs.getLong(4));
                country.setLatlng((String[]) rs.getArray(5).getArray());
                country.setFlag(rs.getString(6));
                countries.add(country);
            }
        }
            catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public void delete() {

        try {
            String sql = "delete from country;";
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
