package networking;

import dm.DataModel;

public class Request {
    private String action;
    private DataModel data;

    // Constructors, getters, and setters
    public Request() {}

    public Request(String action, DataModel data) {
        this.action = action;
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }
}
