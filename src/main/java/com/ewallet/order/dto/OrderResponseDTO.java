package com.ewallet.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderResponseDTO {
    private String txnId;
    private String status;
    private String message;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
