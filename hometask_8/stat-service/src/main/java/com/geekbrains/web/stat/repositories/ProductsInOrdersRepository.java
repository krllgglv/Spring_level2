package com.geekbrains.web.stat.repositories;

import com.geekbrains.web.stat.model.ProductInCarts;
import com.geekbrains.web.stat.model.ProductInOrders;
import com.geekbrains.web.stat.model.projection.ProductsInCartsProjection;
import com.geekbrains.web.stat.model.projection.ProductsInOrdersProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductsInOrdersRepository extends JpaRepository<ProductInOrders, Long> {


    @Query(value = """
            SELECT s1.product_id AS productId,
                SUM (s1.q) AS quantity
                FROM
                    (SELECT product_id AS product_id,
                   SUM(p.quantity) AS q,
                   day_of_year
                    FROM products_in_orders AS p
                    GROUP BY  day_of_year, p.product_id
                    HAVING day_of_year <= :fromDate AND day_of_year >= :toDate
                ) AS s1
            GROUP BY s1.product_id
            ORDER BY quantity DESC
            """,
            nativeQuery = true
    )
    List<ProductsInOrdersProjection> findFiveMostPopularProductsInOrders(Integer fromDate, Integer toDate, Pageable pageable);

    ProductInOrders findByProductIdAndDayOfYearAndYear(Long productId, Integer dayOfYear, Integer year);
}
