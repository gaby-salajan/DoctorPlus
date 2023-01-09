package com.ns.doctorplus.model;

import java.util.Date;

public class Folder {
    private String folderID;
    private Date creationDate;

    public Folder(String folderID, Date creationDate) {
        this.folderID = folderID;
        this.creationDate = creationDate;
    }

    public String getfolderID() {
        return folderID;
    }

    public void setfolderID(String folderID) {
        this.folderID = folderID;
    }

    public Date getcreationDate() {
        return creationDate;
    }

    public void setcreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
