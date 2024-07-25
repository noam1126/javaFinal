package main.test.com.hit.service;

import main.java.dm.DataModel;
import main.java.dao.IDao;
import main.java.dao.MyDMFileImpl;
import main.java.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyServiceTest {

    private IDao dao;
    private BookService service;

    @BeforeEach
    public void setUp() {
        dao = new MyDMFileImpl("test_data.txt");
        service = new BookService(dao);
    }

    @Test
    public void testSaveAndGetById() {
        DataModel dataModel = new DataModel(1, "Test num 1");
        service.saveDataModel(dataModel);

        DataModel retrievedModel = service.getDataModelById(1);
        assertNotNull(retrievedModel);
        assertEquals(dataModel.getId(), retrievedModel.getId());
        assertEquals(dataModel.getName(), retrievedModel.getName());
    }

    @Test
    public void testUpdate() {
        DataModel dataModel = new DataModel(2, "Test num 2");
        service.saveDataModel(dataModel);

        DataModel updatedModel = new DataModel(2, "Updated Test num 2");
        service.updateDataModel(updatedModel);

        DataModel retrievedModel = service.getDataModelById(2);
        assertEquals(updatedModel.getName(), retrievedModel.getName());
    }

    @Test
    public void testDelete() {
        DataModel dataModel = new DataModel(3, "Test num 3");
        service.saveDataModel(dataModel);

        service.deleteDataModel(3);

        DataModel retrievedModel = service.getDataModelById(3);
        assertNull(retrievedModel);

    }
}
