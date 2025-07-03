package kh.com.eco.service.category.dto.request;

import lombok.Data;

@Data
public class CategoryCreateRequestDTO {
	private String name;
	private String author;
	private Integer shares;
	private Long posts;
}
