package fipp.ariane.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import fipp.ariane.vagasonlinebe.entities.Cargo;
import fipp.ariane.vagasonlinebe.entities.Empresa;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {

    public List<Cargo> getAll(){
        List<Cargo> cargoList = new ArrayList<>();
        try{
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("vagas_online");

            MongoCollection<Document> collection = database.getCollection("cargos");
            MongoCursor<Document> cursor = collection.find().iterator();

            while(cursor.hasNext())
                cargoList.add(new Gson().fromJson(cursor.next().toJson(), Cargo.class));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return cargoList;
    }
}
