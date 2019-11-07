package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.medweather.ListOfCountries.model.Country;
import ru.medweather.ListOfCountries.model.RegionalBlocs;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Transactional
public class RegionalBlocsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(RegionalBlocs regionalBlocs, Country country) {
        try {
            Array arrayOtherAcronyms = getConnection().createArrayOf("text", regionalBlocs.getOtherAcronyms());
            Array arrayOtherNames = getConnection().createArrayOf("text", regionalBlocs.getOtherNames());

            String sql1 = "insert into regional_blocs (acronym, name, other_acronyms, other_names, country_id) values (?, ?, ?, ?, ?);";
            PreparedStatement ps1 = getConnection().prepareStatement(sql1);
            ps1.setString(1, regionalBlocs.getAcronym());
            ps1.setString(2, regionalBlocs.getName());
            ps1.setArray(3, arrayOtherAcronyms);
            ps1.setArray(4, arrayOtherNames);
            ps1.setInt(5, country.getId());
            ps1.executeUpdate();

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
