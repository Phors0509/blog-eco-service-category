package kh.com.eco.service.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = CategoryEntity.TABLE_NAME)
@FieldNameConstants
@Data
public class CategoryEntity {

	public static final String TABLE_NAME = "categories";

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String author;
	private Integer shares;
	private Long posts;

}
