package com.hikaku.coordination;

import java.io.Serializable;

public class Job implements Serializable{
    public Integer ID;
    public Double latitude, longitude;
    public String description, jobName;
    public Boolean completed, assigned;




}
