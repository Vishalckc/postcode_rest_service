package com.example.restservice.model;

/*@Table(name="address")*/
public class Address {
    private int id;
    private String postcode;
    private String type;
    private Person person;

    private static final long serialVersionUID = 1L;

    public Address() {
        super();
    }

    public Address(String postcode, int id) {
        this.postcode = postcode;
        this.id = id;
    }

    /*  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id")*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*  @ManyToOne
      @Fetch(FetchMode.JOIN)
      @JoinColumn (name="personId")
      @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Person.class)*/
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
