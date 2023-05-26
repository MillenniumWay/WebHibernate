package Utils;

import Hibernate.Entity.BanksEntity;
import Hibernate.Entity.GuestEntity;
import Hibernate.Enums.BankNamesEnum;
import Hibernate.Enums.CountriesEnums;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class DataImporterTest {

    public void importData(SessionFactory sessionFactory){
        @Cleanup Session session = sessionFactory.openSession();

        saveBank(session, BankNamesEnum.BNP, CountriesEnums.POLAND);
        saveBank(session, BankNamesEnum.BOSS, CountriesEnums.POLAND);
        saveBank(session, BankNamesEnum.ING, CountriesEnums.POLAND);
        saveBank(session, BankNamesEnum.MILLENNIUM, CountriesEnums.POLAND);
        saveBank(session, BankNamesEnum.ALIOR, CountriesEnums.POLAND);

        saveGuest(session, "Mark","Jordan-A21",1);
        saveGuest(session, "Anna","Wilson1997",1);
        saveGuest(session, "Paul","McCarter-A1A1",2);
        saveGuest(session, "Maks","Flore-MOF",2);
        saveGuest(session, "Carl","Fishman_L@AS",3);
        saveGuest(session, "Alan","Tokyo",3);

    }

    private GuestEntity saveGuest (Session session,
                                   String username,
                                   String password,
                                   int id_bank) {
        GuestEntity  guest = GuestEntity.builder()
                .username(username)
                .password(password)
                .bankId(id_bank)
                .build();

        session.save(guest);
        return guest;
    }
    private BanksEntity saveBank (Session session,
                                  BankNamesEnum bankNamesEnum,
                                  CountriesEnums countriesEnums) {
        BanksEntity bank = BanksEntity.builder()
                .name(bankNamesEnum.toString())
                .country(countriesEnums.toString())
                .build();
        session.save(bank);

        return bank;
    }
}
