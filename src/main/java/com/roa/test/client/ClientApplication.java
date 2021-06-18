package com.roa.test.client;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.core.GenericType;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println("hola mundo");
        String baseUrl = "https://feriados-cl-api.herokuapp.com/feriados";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl);
        List<Item> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Item>>() {
        });

        for (Item item : response) {
            System.out.println(item.getTitle());

        }
        sqlClient(response);

    }

    public static void sqlClient(List<Item> items) {
        String connectionUrl
                = "jdbc:sqlserver://localhost:1433;"
                + "database=ItemTest;"
                + "user=sa;"
                + "password=root308;";

        String insertSql =  "insert into item values (?,?,?)";

        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);) {

            for (Item item : items) {

                               
                try (PreparedStatement pstmt = connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS)) { 
                    
                    pstmt.setObject(1, item.getTitle());
                    pstmt.setObject(2, item.getExtra());
                    pstmt.setObject(3, item.getDate());
                    pstmt.execute();
                    // Retrieve the generated key from the insert.
                    resultSet = pstmt.getGeneratedKeys();

                    // Print the ID of the inserted row.
                    while (resultSet.next()) {
                        System.out.println("Generated: " + resultSet.getString(1));
                    }
                }

            }

        } // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
