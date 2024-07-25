package services;

import dm.DataModel;
import dao.IDao;
import main.java.BasedRecommender;
import java.util.List;

public class BookService {

    private final IDao dao;
    private BasedRecommender algo = null;

    public BookService(IDao dao) {
        this.dao = dao;
        this.algo = algo;
    }

    public void saveDataModel(DataModel dataModel) {
        dao.save(dataModel);
    }

    public DataModel getDataModelById(int id) {
        return dao.getById(id);
    }

    public void updateDataModel(DataModel dataModel) {
        dao.update(dataModel);
    }

    public void deleteDataModel(int id) {
        dao.delete(id);
    }

    public List<String> recommendBooks(int userId, int numRecommendations) {
        return algo.recommend(userId, numRecommendations);
    }
}
