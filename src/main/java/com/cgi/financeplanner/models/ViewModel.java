package com.cgi.financeplanner.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewModel {

private String amount;
private String subject;
private String type;

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
