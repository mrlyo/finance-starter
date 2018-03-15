package com.cgi.financeplanner.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String amount;
    private String subject;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getAmount() {
        return amount;
    }

    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setType(String type) {
        this.type = type;
    }
}
