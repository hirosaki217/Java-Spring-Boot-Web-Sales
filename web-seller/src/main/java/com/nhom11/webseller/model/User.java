package com.nhom11.webseller.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	@Id
	@Column(columnDefinition = "varchar(50)", nullable = false)
	private String username;

	@Column(columnDefinition = "char(68)", nullable = false)
	private String password;

	@Column(columnDefinition = "tinyint")
	private boolean enabled;

	@Column(name = "first_name",columnDefinition = "nvarchar(100)")
	private String firstName;
	@Column(name = "last_name",columnDefinition = "nvarchar(100)")
	private String lastName;

	@Column(columnDefinition = "nvarchar(255)")
	private String address;

	@Column(columnDefinition = "varchar(50)")
	private String email;

	@Column(columnDefinition = "varchar(15)")
	private String mobile;

	@Column(name = "registered_at")
	private Date registeredAt;

	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String image;


	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	// @EqualsAndHashCode.Exclude
	// @ToString.Exclude
	// private List<Authority> authorities;

    @Transient
    private String passwordConfirm;
	
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
    @Column(name = "authority")
	private Set<Authority> authorities;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Order> orders;
}
