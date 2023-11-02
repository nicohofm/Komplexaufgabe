package Classes;

import java.sql.Timestamp;

public class Felony {
    private final char[][] picture = new char[16][8];
    private Timestamp timestamp;

    public char[][] getPicture() {
        return picture;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
