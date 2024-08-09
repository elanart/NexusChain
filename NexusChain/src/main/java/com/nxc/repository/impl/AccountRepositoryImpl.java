package com.nxc.repository.impl;

import com.nxc.pojo.Account;
import com.nxc.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public Account findByUsername(String username) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
        Root<Account> root = criteria.from(Account.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("username"), username));

        Query query = session.createQuery(criteria);

        return (Account) query.getSingleResult();
    }

    @Override
    public void saveOrUpdate(Account account) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(account);
    }
}
