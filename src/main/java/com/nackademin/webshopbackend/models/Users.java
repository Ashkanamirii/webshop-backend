package com.nackademin.webshopbackend.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 15:32 <br>
 * Project: webshop-back-end <br>
 */
@Entity(name = "Users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", unique = true)
	@NotNull
	private String email;
    private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String number;
	private String role;//ROLE_USER{ read, edit }, ROLE_ADMIN {delete}
	private String[] authorities;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

    private boolean isActive;
    private boolean isNotLocked;

	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@UpdateTimestamp
	@Column(name = "modify_date")
	private LocalDateTime modifyDate;


//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<GrantedAuthority> setAuths = new HashSet<>();
//		for (Role userRole : roles) {
//			setAuths.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
//		}
//
//		return Collections.unmodifiableSet(setAuths);
//	}

}
