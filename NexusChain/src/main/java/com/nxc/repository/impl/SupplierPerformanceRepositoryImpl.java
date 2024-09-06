package com.nxc.repository.impl;

import com.nxc.dto.supplier.request.SupplierPerformanceDTO;
import com.nxc.enums.CriteriaEnum;
import com.nxc.pojo.Supplier;
import com.nxc.pojo.SupplierRating;
import com.nxc.pojo.User;
import com.nxc.repository.SupplierPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class SupplierPerformanceRepositoryImpl implements SupplierPerformanceRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public List<SupplierPerformanceDTO> getSupplierPerformance() {
        Session session = this.getCurrentSession();
        String hql = "SELECT new com.nxc.dto.supplier.request.SupplierPerformanceDTO( "
                + "s.id, s.user.fullName, "
                + "AVG(CASE WHEN sr.criterion = 'Quality' THEN sr.rating ELSE NULL END), "
                + "AVG(CASE WHEN sr.criterion = 'Timely_Delivery' THEN sr.rating ELSE NULL END), "
                + "AVG(CASE WHEN sr.criterion = 'Cost' THEN sr.rating ELSE NULL END)) "
                + "FROM SupplierRating sr JOIN sr.supplier s "
                + "GROUP BY s.id, s.user.fullName";
        Query query = session.createQuery(hql, SupplierPerformanceDTO.class);
        return query.getResultList();
    }
}
