package kh.com.eco.service.category.controller.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class BatchCreateRequestVO {
	@NotBlank(message = "categories is mandatory")
	private List<CategoryCreateRequestVO> categories;
}
