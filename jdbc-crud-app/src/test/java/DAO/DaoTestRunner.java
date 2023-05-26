package DAO;

import Hibernate.Dao.DaoLayer;
import Hibernate.Entity.BanksEntity;
import Hibernate.Entity.GuestEntity;
import Hibernate.Enums.BankNamesEnum;
import Hibernate.Enums.CountriesEnums;
import Utils.DataImporterTest;
import Utils.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.List;


public class DaoTestRunner {
  private final DaoLayer dao = new DaoLayer();
  private final SessionFactory sessionFactory =  HibernateUtil.buildSessionFactory();
  private final DataImporterTest importerTest = new DataImporterTest();

  @Test
  public void init () {
    importerTest.importData(sessionFactory);
  }

  @Test
    public void findAllGuest () {
      @Cleanup Session session = sessionFactory.openSession();
      session.beginTransaction();

      List<GuestEntity> list = dao.selectAll(session);
      list.stream().forEach(x-> System.out.println(x.toString()));

      session.getTransaction().commit();
  }
  @Test
    public void selectByGuestNameGuest () {
      @Cleanup Session session = sessionFactory.openSession();
      session.beginTransaction();

      List<GuestEntity> list = dao.selectByName(session,"Carl");
      list.stream().forEach(x-> System.out.println(x));

      session.getTransaction().commit();
  }

  @Test
    public void selectByGuestIdGuest () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();

    List<GuestEntity> list = dao.selectById(session,1);
    list.stream().forEach(x-> System.out.println(x));

    session.getTransaction().commit();
  }

  @Test
    public void selectAllGuestsByBankIdGuest () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();

    List<GuestEntity> list = dao.selectAllGuestsByBankId(session,1);
    list.stream().forEach(x-> System.out.println(x));

    session.getTransaction().commit();
  }

  @Test
  public void selectLastGuest (){
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GuestEntity> list = dao.selectLastGuest(session);

    list.stream().forEach(java -> System.out.println(java.toString()));
    session.getTransaction().commit();
  }

  @Test
  public void selectPassAndUsernameGuest () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GuestEntity> list = dao.selectPasswordAndIdByGuestUsername(session, 1);

    list.stream().forEach(java -> System.out.println(java.getUsername()
            + " " +  java.getPassword()));
    session.getTransaction().commit();
  }

  @Test
  public void selectAllBanks () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<BanksEntity> list = dao.selectAllBanks(session);

    list.stream().forEach(java -> System.out.println(java.toString()));
    session.getTransaction().commit();
  }

  @Test
  public void selectBankByName () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<BanksEntity> list = dao.selectBankByName(session, "IPKO");

    list.stream().forEach(java -> System.out.println(java.toString()));
    session.getTransaction().commit();
  }

  @Test
  public void addBank () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();

    dao.buildBankEntity(session, BankNamesEnum.IPKO, CountriesEnums.GERMANY);
    session.getTransaction().commit();
  }

  @Test
  public void addGuest () {
    @Cleanup Session session = sessionFactory.openSession();
    session.beginTransaction();

    dao.buildGuestEntity(session, "test", "test", 1);
    session.getTransaction().commit();
  }



}
