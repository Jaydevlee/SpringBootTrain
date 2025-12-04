package com.example.demoQueryDsl.repository;
import com.example.demoQueryDsl.model.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demoQueryDsl.model.QProduct.product;
import static com.example.demoQueryDsl.repository.ProductQueryDslRepository.SearchType.DESCRIPTION;
import static com.example.demoQueryDsl.repository.ProductQueryDslRepository.SearchType.TITLE;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslRepositoryImpl implements ProductQueryDslRepository {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Product> queryByKeyword(String keyword, SearchType searchType, long offset, long limit) {
    var query = jpaQueryFactory.selectFrom(product);
    if (keyword != null && searchType != null) {
      if (searchType == TITLE) {
        query.where(product.title.contains(keyword));
      } else if (searchType == DESCRIPTION) {
        query.where(product.description.contains(keyword));
      } else {
        query.where(product.title.contains(keyword).or(product.description.contains(keyword)));
        // sample for 'AND' condition
        // query.where(product.title.contains(keyword).and(product.description.contains(keyword)));
        // query.where(product.title.contains(keyword), product.description.contains(keyword));
      }
    }
    return query.orderBy(product.id.desc())
            .offset(offset)
            .limit(limit)
            .fetch();
    // Stream<Product> needs persistence context so use @Transactional in tests
//        return query.orderBy(product.id.desc())
//                .offset(offset)
//                .limit(limit)
//                .stream();
  }
}
