package main.java.com.workPal.model;

public interface Entity<ID> {
    ID getId();
    void setId(ID id);
}
