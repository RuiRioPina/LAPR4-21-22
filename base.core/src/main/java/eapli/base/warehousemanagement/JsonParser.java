package eapli.base.warehousemanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.Warehouse;

public class JsonParser {
    public static void main(String args[]) {

        JsonParser tester = new JsonParser();
        try {
            Warehouse warehouse = tester.readJSON();
            System.out.println(warehouse);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*private Warehouse readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("warehouse/warehouse1.json"));
        return gson.fromJson(bufferedReader, Warehouse.class);
    }*/

    private Warehouse readJSON() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("warehouse/warehouse1.json"));
        return gson.fromJson(bufferedReader, Warehouse.class);
    }
}
