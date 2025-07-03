package kh.com.eco.service.category.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kh.com.eco.service.category.dto.request.BatchCreateRequestDTO;
import kh.com.eco.service.category.dto.request.CategoryCreateRequestDTO;
import kh.com.eco.service.category.dto.response.CategoryResponseDTO;
import kh.com.eco.service.category.entity.CategoryEntity;
import kh.com.eco.service.category.exception.BusinessException;
import kh.com.eco.service.category.repository.CategoryRepository;
import kh.com.eco.service.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;
	private final ObjectMapper mapper;

	@Override
	public void createCategory(CategoryCreateRequestDTO request) {
		try {
			CategoryEntity entity = mapper.convertValue(request, CategoryEntity.class);
			if (ObjectUtils.isEmpty(entity)) {
				throw new BusinessException("Category name cannot be empty");
			}
			repository.save(entity);
		} catch (RuntimeException e) {
			log.error("Failed to create category", e);
			throw new BusinessException("Failed to create category: " + e.getMessage());
		}
	}

	@Override
	public void deleteCategory(Long id) {
		try {
			CategoryEntity entity = repository.findById(id).orElseThrow(() -> new BusinessException("Category not found with ID: " + id));
			repository.delete(entity);
		} catch (RuntimeException e) {
			log.error("Failed to delete category with ID: {}", id, e);
			throw new BusinessException("Failed to delete category with ID: " + id, e.getMessage());
		}
	}

	@Override
	public void updateCategory(Long id, CategoryCreateRequestDTO request) {
		try {
			CategoryEntity entity = repository.findById(id).orElseThrow(() -> new BusinessException("Category not found with ID: " + id));
			mapper.updateValue(entity, request);
			repository.save(entity);
		} catch (Exception e) {
			log.error("Failed to update category with ID: {}", id, e);
			throw new BusinessException("Failed to update category with ID: " + id, e.getMessage());
		}
	}

	@Override
	public CategoryResponseDTO getCategoryById(Long id) {
		try {
			CategoryEntity entity = repository.findById(id).orElse(null);
			if (ObjectUtils.isEmpty(entity)) {
				throw new BusinessException("Category not found with ID: " + id);
			}
			return mapper.convertValue(entity, CategoryResponseDTO.class);
		} catch (Exception e) {
			log.error("Failed to get category by ID: {}", id, e);
			throw new BusinessException("Failed to get category by ID: " + id);
		}
	}

	@Override
	public void createBatchCategory(BatchCreateRequestDTO request) {
		try {
			List<CategoryEntity> entities = request.getCategories().stream().map(dto -> mapper.convertValue(dto, CategoryEntity.class)).toList();
			if (entities.isEmpty()) {
				throw new BusinessException("Category list cannot be empty");
			}
			repository.saveAll(entities);
		} catch (Exception e) {
			log.error("Failed to create batch category", e);
			throw new BusinessException("Failed to create batch category");
		}
	}

	@Override
	public Page<CategoryResponseDTO> getCategoryList(Pageable pageable) {
		try {
			Page<CategoryEntity> entities = repository.findAll(pageable);
			if (entities.isEmpty()) {
				throw new BusinessException("No categories found");
			}
			return entities.map(entity -> mapper.convertValue(entity, CategoryResponseDTO.class));
		} catch (Exception e) {
			log.error("Failed to get category list", e);
			throw new BusinessException("Failed to get category list");
		}
	}

}
