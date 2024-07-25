import dao.IDao;
import dm.DataModel;
import services.BookService;
import algo.IAlgoRecommender;
import algo.ContentBasedRecommender;
import algo.UserBasedCollaborativeFilteringRecommender;

public class Main {
    public static void main(String[] args) {

        Path path = Paths.get("recBooksProject", "src", "main", "resources", "datafile.txt");
        IDao dao = new MyDMFileImpl(path.toString());


        IAlgoRecommender algo = new ContentBasedRecommender();

        BookService bookService = new BookService(dao, algo);

        DataModel dataModel = new DataModel(1, "check");
        bookService.saveDataModel(dataModel);

        DataModel retrievedModel = bookService.getDataModelById(1);
        System.out.println("Retrieved Model: " + retrievedModel);

        List<String> recommendations = bookService.recommendBooks(1, 5);
        recommendations.forEach(System.out::println);
    }
}
