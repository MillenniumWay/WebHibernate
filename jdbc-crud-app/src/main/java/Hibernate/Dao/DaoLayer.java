package Hibernate.Dao;

import Hibernate.Entity.BanksEntity;
import Hibernate.Entity.GuestEntity;
import Hibernate.Enums.BankNamesEnum;
import Hibernate.Enums.CountriesEnums;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DaoLayer {

    public List<GuestEntity> selectAll (Session session) {
    return session.createQuery("FROM GuestEntity", GuestEntity.class).list();
    }

    public List<GuestEntity> selectByName(Session session, String username) {
    return session.createQuery("SELECT g FROM GuestEntity g WHERE g.username= :username", GuestEntity.class)
            .setParameter("username",username).list();
    }

    public List <GuestEntity> selectById (Session session, int id) {
        return session.createQuery("SELECT g FROM GuestEntity g WHERE g.id= :id",GuestEntity.class)
                .setParameter("id", id).list();
    }

    public List<GuestEntity> selectAllGuestsByBankId (Session session, int id) {
        return session.createQuery("SELECT g FROM GuestEntity g WHERE g.bankId= : id", GuestEntity.class)
                .setParameter("id", id).list();
    }

    public List<GuestEntity> selectLastGuest(Session session) {
        return session.createQuery("SELECT g FROM GuestEntity g ORDER BY g.id DESC", GuestEntity.class)
                .setMaxResults(1).list();
    }

    public List<GuestEntity> selectPasswordAndIdByGuestUsername(Session session, int id) {
        return session.createQuery("SELECT g FROM GuestEntity g WHERE g.id= :id", GuestEntity.class)
                .setParameter("id", id).setMaxResults(1).list();
    }

    public List<BanksEntity> selectAllBanks (Session session) {
        return session.createQuery("FROM BanksEntity", BanksEntity.class).list();
    }

    public List<BanksEntity> selectBankByName (Session sessions, String name) {
        return sessions.createQuery("SELECT bank FROM BanksEntity bank WHERE bank.name= :name",
                        BanksEntity.class).setParameter("name", name).list();
    }

    public List<BanksEntity> selectBankById (Session session, int id) {
        return session.createQuery("SELECT bank FROM BanksEntity  bank WHERE bank.id= :id", BanksEntity.class)
                .setParameter("id", id).list();
    }

    // Основые билдеры для сервлетов
    public GuestEntity buildGuestEntityServlet(String username,
                                        String password,
                                        int id_bank) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession();) {
             session.beginTransaction();

            GuestEntity guest = GuestEntity.builder()
                    .username(username)
                    .password(password)
                    .bankId(id_bank)
                    .build();

            session.save(guest);
            session.getTransaction().commit();
            return guest;
        }
    }


    public BanksEntity buildBankEntityServlet (BankNamesEnum bankNamesEnum,
                                               CountriesEnums countriesEnums) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            BanksEntity banks = BanksEntity.builder()
                    .name(bankNamesEnum.toString())
                    .country(countriesEnums.toString())
                    .build();
            session.save(banks);
            return banks;
        }
    }

    // Тестовые билдеры для тестового класса
    public BanksEntity buildBankEntity (Session session,
                                        BankNamesEnum bankNamesEnum,
                                        CountriesEnums countriesEnums) {
        BanksEntity banks = BanksEntity.builder()
                .name(bankNamesEnum.toString())
                .country(countriesEnums.toString())
                .build();
        session.save(banks);
        return banks;
    }

    public GuestEntity buildGuestEntity (Session session,
                                         String username,
                                         String password,
                                         int id_bank) {
        GuestEntity guest = GuestEntity.builder()
                .username(username)
                .password(password)
                .bankId(id_bank)
                .build();
        session.save(guest);
        return guest;
    }
}


