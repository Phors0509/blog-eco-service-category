package kh.com.eco.service.category.controller.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreateRequestVO {
	@NotBlank(message = "name is mandatory")
	private String name;
	@NotBlank(message = "author is mandatory")
	private String author;
	@NotBlank(message = "shares is mandatory")
	private Integer shares;
	@NotBlank(message = "posts is mandatory")
	private Long posts;
}
