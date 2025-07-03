package kh.com.eco.service.category.controller.vo.request;

import lombok.Data;

@Data
public class CategoryUpdateRequestVO {
	private String name;
	private String author;
	private Integer shares;
	private Long posts;
}
