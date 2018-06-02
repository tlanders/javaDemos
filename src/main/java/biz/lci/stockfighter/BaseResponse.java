package biz.lci.stockfighter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    protected final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static Date convertToDate(String time) throws ParseException {
        return dateFormat.parse(time.replaceFirst("\\d\\d\\d(\\d\\d\\d)", "$1"));
    }
}
