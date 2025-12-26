import lombok.NonNull;

@lombok.Data
@lombok.EqualsAndHashCode(of="id")
public final class User{
    @NonNull
    private final String id;
    @NonNull
    private final String name;

    public User(String id, String name){
        if (id != null && !id.isBlank()) this.id = id;
        else throw new IllegalArgumentException("User Id cannot be null or empty");

        if (name != null && !name.isBlank()) this.name = name;
        else throw new IllegalArgumentException("User Name cannot be null or empty");
    }


}