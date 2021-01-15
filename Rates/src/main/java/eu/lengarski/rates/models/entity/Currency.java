package eu.lengarski.rates.models.entity;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "currency_name")
    private String currencyName;

    public Currency() {

    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
