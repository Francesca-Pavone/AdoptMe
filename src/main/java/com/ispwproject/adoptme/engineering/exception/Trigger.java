package com.ispwproject.adoptme.engineering.exception;

public class Trigger {

    public void imageNotFound() throws ImageNotFoundException {
        throw new ImageNotFoundException();
    }

    public void pastDate(String date) throws PastDateException {
        throw new PastDateException(date);
    }

}
