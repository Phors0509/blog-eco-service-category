package kh.com.eco.service.category.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BatchCreateRequestDTO {
	private List<CategoryCreateRequestDTO> categories;
}
