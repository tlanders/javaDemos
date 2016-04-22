package biz.lci.stockfighter;

/**
 * Created by tlanders on 4/18/2016.
 */
public class BaseResponse {
    protected boolean ok;
    protected String error;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
