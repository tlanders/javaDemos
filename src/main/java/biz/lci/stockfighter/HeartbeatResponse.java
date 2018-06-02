package biz.lci.stockfighter;

/**
 * Created by tlanders on 4/16/2016.
 */
public class HeartbeatResponse extends BaseResponse {
    private String venue;

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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
