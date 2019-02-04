package blue.springframework.api.v1.mapper;

import blue.springframework.api.v1.model.CategoryDTO;
import blue.springframework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // @Mapping(source = "getId", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
