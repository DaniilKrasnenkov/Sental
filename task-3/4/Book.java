package dz2_task4;

public class Book {
    private String name;
    private String author;
    private boolean status;

    Book(String name, boolean status){
        this.name=name;
        this.status=status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
