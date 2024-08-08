package architecture.template.vmm.mapper;

import architecture.template.vmm.dto.catalog.CatalogValueDto;
import architecture.template.vmm.entity.catalog.CatalogValueEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CatalogValueMapper {

  public CatalogValueEntity build(CatalogValueDto dto) {
    return new CatalogValueEntity
      .EntityBuilder()
      .uuid(dto.getUuid())
      .value(dto.getValue())
      .description(dto.getDescription())
      .build();
  }

  public CatalogValueDto build(CatalogValueEntity entity) {
    return new CatalogValueDto
      .EntityBuilder()
      .setValue(entity.getValue())
      .setDescription(entity.getDescription())
      .setUuid(entity.getUuid())
      .build();
  }

  public Set<CatalogValueDto> buildEntitySet(Set<CatalogValueEntity> entity) {
    return entity.stream().map(this::build).collect(Collectors.toSet());
  }

  public Set<CatalogValueEntity> buildDtoSet(Set<CatalogValueDto> dto) {
    return dto.stream().map(this::build).collect(Collectors.toSet());
  }

}
