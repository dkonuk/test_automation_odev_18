package models;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pet {
    private int id;
    private String name;
    private Category category;
    private String status;
}
