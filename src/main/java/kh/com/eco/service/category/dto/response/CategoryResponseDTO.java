package kh.com.eco.service.category.dto.response;

import lombok.Data;

@Data
public class CategoryResponseDTO {
	private Long id;
	private String name;
	private String author;
	private Integer shares;
	private Long posts;
}
