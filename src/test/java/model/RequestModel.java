package model;

import lombok.Data;

@Data
public class RequestModel {

    private Integer amount;
    private String currency;
    private String distributionChannel;
    private String endDate;
    private String name;
    private String password;
    private String startDate;
    private Type type;
    private String username;
}
