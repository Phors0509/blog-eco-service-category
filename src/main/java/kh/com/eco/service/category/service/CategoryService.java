package kh.com.eco.service.category.service;

import kh.com.eco.service.category.dto.request.BatchCreateRequestDTO;
import kh.com.eco.service.category.dto.request.CategoryCreateRequestDTO;
import kh.com.eco.service.category.dto.response.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
	void createCategory(CategoryCreateRequestDTO request);

	void deleteCategory(Long id);

	void updateCategory(Long id, CategoryCreateRequestDTO request);

	CategoryResponseDTO getCategoryById(Long id);

	void createBatchCategory(BatchCreateRequestDTO request);

	Page<CategoryResponseDTO> getCategoryList(Pageable pageable);

}
