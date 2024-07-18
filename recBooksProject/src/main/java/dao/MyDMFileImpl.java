package dao;


import dm.DataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDMFileImpl implements dao.IDao {

    private String filePath;
    private Map<Integer, DataModel> dataModels;

    public MyDMFileImpl(String filePath) {
        this.filePath = filePath;
        this.dataModels = new HashMap<>();
        loadFromFile();
    }

    @Override
    public void save(DataModel dataModel) {
        dataModels.put(dataModel.getId(), dataModel);
        saveToFile();
    }

    @Override
    public DataModel getById(int id) {
        return dataModels.get(id);
    }

    @Override
    public List<DataModel> getAll() {
        return List.of();
    }

    @Override
    public void update(DataModel dataModel) {
        dataModels.put(dataModel.getId(), dataModel);
        saveToFile();
    }

    @Override
    public void delete(int id) {
        dataModels.remove(id);
        saveToFile();
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            dataModels = (Map<Integer, DataModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(dataModels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}