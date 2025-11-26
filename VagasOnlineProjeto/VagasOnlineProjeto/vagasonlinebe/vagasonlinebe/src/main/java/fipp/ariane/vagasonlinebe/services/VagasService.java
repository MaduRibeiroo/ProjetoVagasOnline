package fipp.ariane.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import fipp.ariane.vagasonlinebe.entities.Vaga;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class VagasService {

    public List<Vaga> getAll(){
        List<Vaga> vagaList = new ArrayList<>();
        try{
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");

            MongoCollection<Document> collection = database.getCollection("vagas");
            MongoCursor<Document> cursor = collection.find().iterator();

            while(cursor.hasNext())
                vagaList.add(new Gson().fromJson(cursor.next().toJson(), Vaga.class));
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
        return vagaList;
    }

    public Vaga getOne(String registro){
        List<Vaga> vagaList = new ArrayList<>();
        Vaga vaga=null;
        try{
            String filtro="(?i)"+registro;
            Bson filter = new Document("registro", Pattern.compile(filtro));
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");

            MongoCollection<Document> collection = database.getCollection("vagas");
            MongoCursor<Document> cursor = collection.find(filter).iterator();

            while(cursor.hasNext())
                vagaList.add(new Gson().fromJson(cursor.next().toJson(), Vaga.class));
            if(!vagaList.isEmpty())
                vaga=vagaList.get(0);
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
        return vaga;
    }

    public boolean delete(String registro){
        try{
            String filtro="(?i)"+registro;
            Bson filter = new Document("registro", Pattern.compile(filtro));
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");
            MongoCollection<Document> collection = database.getCollection("vagas");

            if(vagaPossuiInteresses(registro)){
                throw new IllegalArgumentException("Não é possível excluir a vaga! \nVaga com interessados!");
            }

            collection.deleteOne(filter);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }

    public Vaga add(Vaga vaga){
        try{
            if (getOne(vaga.getRegistro()) != null) {
                throw new IllegalArgumentException("Já existe uma vaga com esse registro!");
            }
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");
            MongoCollection<Document> collection = database.getCollection("vagas");
            collection.insertOne(Document.parse(new Gson().toJson(vaga)));
            return vaga;
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }

    private boolean vagaPossuiInteresses(String registro) {
        String filtro = "(?i)" + registro;
        String connectionString = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("vagas_online");

        MongoCollection<Document> interesses = database.getCollection("interesses");

        long count = interesses.countDocuments(
                new Document("vaga.registro", Pattern.compile(filtro))
        );
        return count > 0;
    }


    public Vaga alterar(String registro, Vaga vaga){
        try{
            String filtro="(?i)"+registro;
            Bson filter = new Document("registro", Pattern.compile(filtro));
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");
            MongoCollection<Document> collection = database.getCollection("vagas");


            Vaga vagaExistente = getOne(registro);
            if (vagaExistente == null) {
                throw new IllegalArgumentException("Vaga não encontrada!");
            }

            if(vagaPossuiInteresses(registro)){
                throw new IllegalArgumentException("Não é possível alterar a vaga! \nVaga com interessados!");
            }

            if (!vagaExistente.getRegistro().equalsIgnoreCase(vaga.getRegistro())) {
                throw new IllegalArgumentException("Não é permitido alterar o registro da vaga!");
            }

            Document docAtualizado = Document.parse(new Gson().toJson(vaga));

            collection.updateOne(filter, new Document("$set", docAtualizado));
            return vaga;
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
}
