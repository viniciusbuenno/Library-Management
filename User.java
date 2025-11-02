import java.util.Objects;

public final class User implements Comparable <User>{
    private final String id, name;

    public String getId(){ return this.id; }
    public String getName(){ return this.name; }

    public User(String id, String name){
        if (id != null && !id.isBlank()) this.id = id;
        else throw new IllegalArgumentException("User Id cannot be null or empty");

        if (name != null && !name.isBlank()) this.name = name;
        else throw new IllegalArgumentException("User Name cannot be null or empty");
    }

    @Override
    public String toString(){
        return "\nUser Id: " + this.id + "\nUser Name: " + this.name;
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || other.getClass() != this.getClass()) return false;

        User otherUser = (User) other;
        return this.id.equals(otherUser.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id);
    }

    public int compareTo(User otherUser){
        if (this.name.compareTo(otherUser.getName()) != 0) return this.name.compareTo(otherUser.getName());
        if (this.id.compareTo(otherUser.getId()) != 0) return this.id.compareTo(otherUser.getId());

        return 0;
    }

}