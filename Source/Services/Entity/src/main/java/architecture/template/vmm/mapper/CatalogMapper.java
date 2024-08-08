package architecture.template.vmm.mapper;

import architecture.template.vmm.dto.catalog.CatalogDto;
import architecture.template.vmm.entity.catalog.CatalogEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CatalogMapper {

  //region Dependencies
  private final CatalogValueMapper catalogValueMapper;
  //endregion

  //region Ctor
  public CatalogMapper(
    CatalogValueMapper catalogValueMapper
  ) {
    this.catalogValueMapper = catalogValueMapper;
  }
  //endregion

  public CatalogDto build(CatalogEntity catalogEntity) {
    return new CatalogDto
      .EntityBuilder()
      .uuid(catalogEntity.getUuid())
      .name(catalogEntity.getName())
      .values(
        catalogEntity.getValues()
          .stream()
          .map(catalogValueMapper::build)
          .collect(Collectors.toSet())
      )
      .build();
  }

  public CatalogEntity build(CatalogDto dto) {
    return new CatalogEntity
      .EntityBuilder()
      .uuid(dto.getUuid())
      .name(dto.getName())
      .value(
        dto.getValues()
          .stream()
          .map(catalogValueMapper::build)
          .collect(Collectors.toSet())
      )
      .build();
  }

}
