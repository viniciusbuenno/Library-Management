import java.util.Objects;

public final class Book implements Comparable<Book>{
    private final String id, author, title;
    private boolean available;

    public String getId(){ return this.id; }
    public String getAuthor(){ return this.author; }
    public String getTitle(){ return this.title; }
    public boolean isAvailable(){ return this.available; }

    public void setAvailable(boolean available){this.available = available;}

    public Book(String id, String author, String title, boolean available){
        
        if(id != null && !id.isBlank()){this.id = id;} else{
            throw new IllegalArgumentException("Book id cannot be null or empty");
        }

        if(author != null && !author.isBlank()){this.author = author;} else{
            throw new IllegalArgumentException("Book author cannot be null or empty");
        }

        if(title != null && !title.isBlank()){this.title = title;} else{
            throw new IllegalArgumentException("Book title cannot be null or empty");
        }

        this.available = available;  
    }

    @Override
    public String toString(){
        return "\nBook ID: " + this.id + "\nBook Title: " + this.title + "\nBook Author: " + this.author + "\nIs Book Available: " + (this.isAvailable() ? "Yes" : "No");
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        
        if (other == null || this.getClass() != other.getClass()) return false;

        Book otherBook = (Book) other;

        return (this.id.equals(otherBook.getId()) && this.id.equals(otherBook.getId()));
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id);
    }

    @Override
    public int compareTo(Book otherBook){
        if(otherBook == null) throw new IllegalArgumentException("\nComparable object cannot be null!");
        
        if (this.title.compareTo(otherBook.getTitle()) != 0 ) return this.title.compareTo(otherBook.getTitle());
        
        if (this.author.compareTo(otherBook.getAuthor()) != 0 ) return this.author.compareTo(otherBook.getAuthor());
        
        if (this.id.compareTo(otherBook.getId()) != 0 ) return this.id.compareTo(otherBook.getId());

        return 0;
        
    }

}