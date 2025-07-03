package kh.com.eco.service.category.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kh.com.eco.service.category.controller.vo.request.BatchCreateRequestVO;
import kh.com.eco.service.category.controller.vo.request.CategoryCreateRequestVO;
import kh.com.eco.service.category.controller.vo.response.CategoryResponseVO;
import kh.com.eco.service.category.dto.request.BatchCreateRequestDTO;
import kh.com.eco.service.category.dto.request.CategoryCreateRequestDTO;
import kh.com.eco.service.category.dto.response.CategoryResponseDTO;
import kh.com.eco.service.category.dto.response.ResponseMessageBuilder;
import kh.com.eco.service.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService service;
	private final ObjectMapper mapper;

	@Operation(method = "POST", summary = "Create a new category", description = "Create a new category in the blog application.")
	@PostMapping
	public ResponseMessageBuilder.ResponseMessage<Void> createCategory(@RequestBody @Valid CategoryCreateRequestVO request) {
		this.service.createCategory(mapper.convertValue(request, CategoryCreateRequestDTO.class));
		return new ResponseMessageBuilder<Void>().success().build();
	}

	@Operation(method = "POST", summary = "Create a new batch category", description = "Create a new category in the " + "blog application.")
	@PostMapping("/batch")
	public ResponseMessageBuilder.ResponseMessage<Void> createBatchCategory(@RequestBody @Valid BatchCreateRequestVO request) {
		this.service.createBatchCategory(mapper.convertValue(request, BatchCreateRequestDTO.class));
		return new ResponseMessageBuilder<Void>().success().build();
	}

	@Operation(method = "GET", summary = "Get category details", description = "Retrieve details of a specific category by its ID.")
	@GetMapping("/{categoryId}")
	public ResponseMessageBuilder.ResponseMessage<CategoryResponseVO> getCategoryDetails(@PathVariable Long categoryId) {
		CategoryResponseDTO categoryResponseDTO = this.service.getCategoryById(categoryId);
		CategoryResponseVO categoryResponseVO = mapper.convertValue(categoryResponseDTO, CategoryResponseVO.class);
		return new ResponseMessageBuilder<CategoryResponseVO>().addData(categoryResponseVO).success().build();
	}

	@Operation(method = "update", summary = "Update a category", description = "Update an existing category by its ID.")
	@PostMapping("/{categoryId}")
	public ResponseMessageBuilder.ResponseMessage<Void> updateCategory(@PathVariable Long categoryId,
	                                                                   @RequestBody @Valid CategoryCreateRequestVO request) {
		this.service.updateCategory(categoryId, mapper.convertValue(request, CategoryCreateRequestDTO.class));
		return new ResponseMessageBuilder<Void>().success().build();
	}

	@Operation(method = "DELETE", summary = "Delete a category", description = "Delete a specific category by its ID.")
	@PostMapping("/{categoryId}/delete")
	public ResponseMessageBuilder.ResponseMessage<Void> deleteCategory(@PathVariable Long categoryId) {
		this.service.deleteCategory(categoryId);
		return new ResponseMessageBuilder<Void>().success().build();
	}

	@Operation(method = "GET", summary = "Get category list", description = "Retrieve a list of all categories in the blog application.")
	@GetMapping
	public ResponseMessageBuilder.ResponseMessage<Page<CategoryResponseVO>> getCategoryList(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
		Page<CategoryResponseDTO> categoryPage = this.service.getCategoryList(pageable);
		Page<CategoryResponseVO> categoryResponseVOPage = categoryPage.map(category -> mapper.convertValue(category, CategoryResponseVO.class));
		return new ResponseMessageBuilder<Page<CategoryResponseVO>>().addData(categoryResponseVOPage).success().build();
	}

}
