package org.example.trigquizzer.repo;

import org.example.trigquizzer.data.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.*;

public class JdbcRepository<T> implements Repository<T, Long> {

    private Field[] fields;
    private Class<T> tClass;

    public JdbcRepository(Class<T> tClass) {
        this.tClass = tClass;
        fields = tClass.getDeclaredFields();

    }

    private int convertToSqlType(Class<?> type) {
        if (type.equals(String.class)) {
            return Types.LONGVARCHAR;
        } else if (type.equals(Number.class))
            return Types.BIGINT;

        return 0;
    }


    @Override
    public void insert(T t) {
        String query = "INSERT INTO " + tClass.getSimpleName().toLowerCase() + "_table VALUES(" + "?, ".repeat(fields.length-1) + "?)";
        try (PreparedStatement preparedStatement = Database.INSTANCE.getConnection()
                .prepareStatement(query)) {

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                preparedStatement.setObject(i+1, field.get(t), convertToSqlType(field.getType()));
                field.setAccessible(false);
            }

            System.out.println(preparedStatement);

            preparedStatement.execute();


        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
