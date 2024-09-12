package main.java.com.workPal.dto;

public interface Dto<ID> {
    ID getId();
    String[] getAttributes();
}
