package biz.lci.stockfighter;

/**
 * Created by tlanders on 4/16/2016.
 */
public class HeartbeatResponse {
    private boolean ok;
    private String venue;
    private String error;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "HeartbeatResponse{" +
                "ok=" + ok +
                ", venue='" + venue + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
