

@lombok.Data
@lombok.EqualsAndHashCode(of="id")
public final class Book implements Comparable<Book>{

    private final String id;
    private final String author;
    private final String title;

    private boolean available;

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
    public int compareTo(Book otherBook){
        if(otherBook == null) throw new IllegalArgumentException("\nComparable object cannot be null!");
        
        if (this.title.compareTo(otherBook.getTitle()) != 0 ) return this.title.compareTo(otherBook.getTitle());
        
        if (this.author.compareTo(otherBook.getAuthor()) != 0 ) return this.author.compareTo(otherBook.getAuthor());
        
        if (this.id.compareTo(otherBook.getId()) != 0 ) return this.id.compareTo(otherBook.getId());

        return 0;
        
    }

}