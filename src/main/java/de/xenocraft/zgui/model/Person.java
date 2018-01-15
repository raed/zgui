package de.xenocraft.zgui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Min;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements IdAble {

	private int		id;
	private String	name;
	private int		age;

	public Person() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Min(0)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("Person(id=%d,name=%s,alter=%d)", id, name, age);
	}
}
