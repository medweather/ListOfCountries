package ru.medweather.ListOfCountries.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    public void save(RegionalBlocs regionalBlocs) {
        SessionImpl sessionImpl = (SessionImpl) getCurrentSession();
        Connection connection = sessionImpl.connection();
        try {

            Array arrayOtherAcronyms = connection.createArrayOf("text", regionalBlocs.getOtherAcronyms());
            Array arrayOtherNames = connection.createArrayOf("text", regionalBlocs.getOtherNames());

            String sql = "insert into regional_blocs (acronym, name, other_acronyms, other_names) values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, regionalBlocs.getAcronym());
            ps.setString(2, regionalBlocs.getName());
            ps.setArray(3, arrayOtherAcronyms);
            ps.setArray(4, arrayOtherNames);
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
