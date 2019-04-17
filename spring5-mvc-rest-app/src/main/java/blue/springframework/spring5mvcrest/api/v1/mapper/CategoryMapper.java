package blue.springframework.spring5mvcrest.api.v1.mapper;

import blue.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import blue.springframework.spring5mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
