package fipp.ariane.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fipp.ariane.vagasonlinebe.entities.Interesse;
import fipp.ariane.vagasonlinebe.entities.Vaga;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class  InteresseService {

    @Autowired
    private VagasService vagasService;

    public Interesse add(Interesse interesse){
        try{
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");
            MongoCollection<Document> collection = database.getCollection("interesses");

            Vaga vagaExistente = vagasService.getOne(interesse.getVaga().getRegistro());
            if (vagaExistente == null) {
                throw new IllegalArgumentException("A vaga informada não existe!");
            }

            // 3. (Opcional) impedir interesse duplicado
            if (jaPossuiInteresse(interesse)) {
                throw new IllegalArgumentException("O usuário já demonstrou interesse nessa vaga.");
            }

            collection.insertOne(Document.parse(new Gson().toJson(interesse)));
            return interesse;
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }

    private boolean jaPossuiInteresse(Interesse interesse) {
        String registroVaga = "(?i)" + interesse.getVaga().getRegistro();

        String connectionString = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("vagas_online");

        MongoCollection<Document> collection = database.getCollection("interesses");

        long count = collection.countDocuments(
                new Document("vaga.registro", Pattern.compile(registroVaga))
        );

        return count > 0;
    }

}
