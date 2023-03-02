package org.example.trigquizzer.repo;

import org.example.trigquizzer.data.Database;
import org.example.trigquizzer.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcQuestionRepository implements Repository<Question, Long>{
    @Override
    public void insert(Question question) {
        try (PreparedStatement statement = Database.INSTANCE.getConnection()
                .prepareStatement("INSERT INTO questions VALUES(?, ?, ?)")) {

            statement.setLong(1, question.getId());
            statement.setString(2, question.getText());
            statement.setString(3, question.getAnswer());
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
    public List<Question> findAll() {
        try (PreparedStatement statement = Database.INSTANCE.getConnection().prepareStatement("SELECT * FROM links;")) {
            ResultSet results = statement.executeQuery();

            List<Question> list = new ArrayList<>();
            while (results.next()) {
                list.add(new Question(
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
