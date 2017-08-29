/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Project Bibliotheca (Leo Ackerman)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package za.co.scrinium.ecommerce.persistence.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.scrinium.ecommerce.persistence.domain.Persistable;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDAOImpl<K extends Serializable, V extends Persistable> implements BaseDAO<K, V> {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public V get(Class<V> aClazz, K aKey) {
    return (V) getSessionFactory().getCurrentSession().get(aClazz, aKey);
  }

  @Override
  public List<V> getAll(Class<V> aClazz) {
    return (List<V>) getSessionFactory().getCurrentSession().createQuery("from " + aClazz.getSimpleName()).list();
  }

  @Override
  public void create(V aPersistable) {
    getSessionFactory().getCurrentSession().save(aPersistable);
    getSessionFactory().getCurrentSession().flush();
  }

  @Override
  public void update(V aPersistable) {
    getSessionFactory().getCurrentSession().update(aPersistable);
    getSessionFactory().getCurrentSession().flush();
  }

  @Override
  public void delete(V aPersistable) {
    getSessionFactory().getCurrentSession().delete(aPersistable);
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}
