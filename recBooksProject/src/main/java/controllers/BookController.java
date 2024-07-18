package controllers;

import services.BookService;
import networking.Request;
import networking.Response;
import dm.DataModel;

public class BookController implements Controller {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response handleRequest(Request request) {
        String action = request.getAction();
        DataModel dataModel = request.getData();

        switch (action) {
            case "save":
                bookService.saveDataModel(dataModel);
                return new Response("success", "DataModel saved successfully.");
            case "get":
                DataModel retrieved = bookService.getDataModelById(dataModel.getId());
                return new Response("success", "DataModel retrieved: " + retrieved);
            case "update":
                bookService.updateDataModel(dataModel);
                return new Response("success", "DataModel updated successfully.");
            case "delete":
                bookService.deleteDataModel(dataModel.getId());
                return new Response("success", "DataModel deleted successfully.");
            default:
                return new Response("error", "Invalid action.");
        }
    }
}
