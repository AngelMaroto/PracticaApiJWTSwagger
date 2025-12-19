package es.softtek.jwtDemo.dto;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String user;
    @Column(name = "password")
    private String pwd;

    //Le decimos a hibernate que solo se usa en java no hay que mapearlo con la base de datos
    @Transient
    private String token;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
