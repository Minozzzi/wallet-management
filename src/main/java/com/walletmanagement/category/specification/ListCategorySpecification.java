package com.walletmanagement.category.specification;

import static org.springframework.data.jpa.domain.Specification.where;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.walletmanagement.category.dto.ListCategoryDto;
import com.walletmanagement.entities.CategoryEntity;
import com.walletmanagement.shared.specification.BaseSpecification;

@Component
public class ListCategorySpecification extends BaseSpecification<CategoryEntity, ListCategoryDto> {

  @Override
  public Specification<CategoryEntity> getFilter(ListCategoryDto filters) {
    return (root, query, cb) -> {
      query.distinct(true);
      return where(nameContains(filters.getName())).toPredicate(root, query, cb);
    };
  }

  private Specification<CategoryEntity> nameContains(String name) {
    return this.entityAttributeContains("name", name);
  }

}
