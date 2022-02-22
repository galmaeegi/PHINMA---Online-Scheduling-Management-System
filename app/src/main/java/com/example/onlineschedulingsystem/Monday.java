package com.example.onlineschedulingsystem;

public class Monday {

    String student_;
    String department_;
    String purpose_;

    public Monday(String student_, String department_, String purpose_) {
        this.student_ = student_;
        this.department_ = department_;
        this.purpose_ = purpose_;
    }

    public String getStudent_() {
        return student_;
    }

    public String getDepartment_() {
        return department_;
    }

    public String getPurpose_() {
        return purpose_;
    }
}
