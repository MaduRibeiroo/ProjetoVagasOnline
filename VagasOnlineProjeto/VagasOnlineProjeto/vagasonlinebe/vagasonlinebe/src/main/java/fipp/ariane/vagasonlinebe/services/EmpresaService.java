package fipp.ariane.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import fipp.ariane.vagasonlinebe.entities.Empresa;
import fipp.ariane.vagasonlinebe.entities.Vaga;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    public List<Empresa> getAll(){
        List<Empresa> empresaList = new ArrayList<>();
        try{
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");

            MongoCollection<Document> collection = database.getCollection("empresas");
            MongoCursor<Document> cursor = collection.find().iterator();

            while(cursor.hasNext())
                empresaList.add(new Gson().fromJson(cursor.next().toJson(), Empresa.class));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return empresaList;
    }
}
