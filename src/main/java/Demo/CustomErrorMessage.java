package Demo;

import Helpers.JSON;

public class CustomErrorMessage {
    private String error;

    public CustomErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
