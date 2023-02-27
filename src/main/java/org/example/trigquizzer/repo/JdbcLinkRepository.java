package org.example.trigquizzer.repo;

import org.example.trigquizzer.data.Database;
import org.example.trigquizzer.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcLinkRepository implements Repository<Link, Long> {
    @Override
    public void insert(Link link) {
        try (PreparedStatement statement = Database.INSTANCE.getConnection()
                .prepareStatement("INSERT INTO links VALUES(?, ?, ?)")) {

            statement.setLong(1, link.getId());
            statement.setString(2, link.getUrl());
            statement.setString(3, link.getDescription());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = Database.INSTANCE.getConnection()
                .prepareStatement("DELEtE FROM links WHERE id=?")) {

            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Link> findAll() {

        try (PreparedStatement statement = Database.INSTANCE.getConnection().prepareStatement("SELECT * FROM links;")) {
            ResultSet results = statement.executeQuery();

            List<Link> list = new ArrayList<>();
            while (results.next()) {
                list.add(new Link(
                        results.getLong(1),
                        results.getString(2),
                        results.getString(3)
                ));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
