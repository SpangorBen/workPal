package main.java.com.workPal.model;

public class SpaceType implements Entity<Integer> {
    private Integer Id;
    private String name;
    private String description;
    private Admin admin;

    public SpaceType() {
    }

    public SpaceType(String name, String description, Admin admin) {
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "SpaceType{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", admin=" + admin +
                '}';
    }
}
