package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Employer extends AbstractEntity {
    @Size(min = 3, max = 100, message = "Enter Location")
    public String location;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;


    public Employer() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
