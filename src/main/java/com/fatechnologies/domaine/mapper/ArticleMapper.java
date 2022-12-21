package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.entity.ArticleOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

	@Mappings({ @Mapping(target = "categoryId", source = "source.category.id"),
			    @Mapping(target = "categoryLabel", source = "source.category.label")
	})
	ArticleDto modelToDto(final ArticleEntity source);
	List<ArticleDto> modelToDto(final List<ArticleEntity> sources);
	
	@Mappings({ @Mapping(target = "category.id", source = "dto.categoryId"),
	    @Mapping(target = "category.label", source = "dto.categoryLabel")
})
    ArticleEntity dtoToModel(final ArticleDto dto);
	List<ArticleDto> dtoTomodeles(final List<ArticleOrder> dtos);
}
