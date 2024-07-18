import dao.IDao;
import dm.DataModel;
import services.BookService;
import algo.IAlgoRecommender;
import algo.ContentBasedRecommender;
import algo.UserBasedCollaborativeFilteringRecommender;

public class Main {
    public static void main(String[] args) {
        // Create your IDao implementation
        IDao dao = new MyDMFileImpl("path/to/your/file.txt");

        // Create your IAlgoRecommender implementation
        IAlgoRecommender algo = new ContentBasedRecommender();

        // Create the BookService instance
        BookService bookService = new BookService(dao, algo);

        // Example usage
        DataModel dataModel = new DataModel(1, "example data");
        bookService.saveDataModel(dataModel);

        DataModel retrievedModel = bookService.getDataModelById(1);
        System.out.println("Retrieved Model: " + retrievedModel);

        // Recommendations
        List<String> recommendations = bookService.recommendBooks(1, 5);
        recommendations.forEach(System.out::println);
    }
}
