package org.HardCore.model.objects.dto;

public class Unternehmen extends User{
    private String firmenname = "Hardcore GmbH";

    public Unternehmen(User user) {
        super(user);
    }


    public String getName() { return firmenname; }

    public void setName(String name) {
        this.firmenname = name;
    }
}
