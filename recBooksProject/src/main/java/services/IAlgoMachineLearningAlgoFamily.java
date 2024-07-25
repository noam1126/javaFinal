package services;

import java.util.List;

public interface IAlgoMachineLearningAlgoFamily {
    void add(Object data);
    Object get(int id);
    List<String> compute();
    List<String> recommend(int userId, int numRecommendations);
}
