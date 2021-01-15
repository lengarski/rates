package eu.lengarski.rates.models.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RequestInformation {

    @Id
    @Column(name = "request_id")
    private String requestId;

    @Column(name = "client")
    private String client;

    @Column(name = "timestamp")
    private Long timestamp;

    public RequestInformation(){

    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
