package com.ttn.worker.util.dto;

import java.util.Date;

public class QueueDTO {

    private String value;
    private Date dateCreated;

    public QueueDTO(){}
    public QueueDTO(String value) {
        this.value = value;
        this.dateCreated = new Date();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "QueueDTO{" +
                "value='" + value + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
