package eu.lengarski.rates.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "rate_id")
    private Long rateId;

    @Column(name = "currancy")
    private String currancy;

    @Column(name = "base")
    private String base;

    @Column(name = "amount")
    private Double amount;

    @Column(name ="date")
    private Date date;

    @Column(name="timestamp")
    private Long timestamp;

    public Rate() {

    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public String getCurrancy() {
        return currancy;
    }

    public void setCurrancy(String currancy) {
        this.currancy = currancy;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
