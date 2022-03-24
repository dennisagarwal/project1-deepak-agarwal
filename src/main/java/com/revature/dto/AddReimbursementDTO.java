package com.revature.dto;

import com.revature.model.User;

import java.util.Objects;

public class AddReimbursementDTO {

    private int amount;
    private String submitDate;
    private int status;
    private int type;

    public AddReimbursementDTO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddReimbursementDTO that = (AddReimbursementDTO) o;
        return amount == that.amount && status == that.status && type == that.type && Objects.equals(submitDate, that.submitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, submitDate, status, type);
    }

    @Override
    public String  toString() {
        return "AddReimbursementDTO{" +
                "amount=" + amount +
                ", submitDate='" + submitDate + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
