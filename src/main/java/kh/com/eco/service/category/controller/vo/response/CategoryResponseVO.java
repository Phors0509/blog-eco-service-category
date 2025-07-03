package kh.com.eco.service.category.controller.vo.response;

import lombok.Data;

@Data
public class CategoryResponseVO {
	private Long id;
	private String name;
	private String author;
	private Integer shares;
	private Long posts;
}
